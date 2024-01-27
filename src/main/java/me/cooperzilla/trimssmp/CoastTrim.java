package me.cooperzilla.trimssmp;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;

public class CoastTrim implements Listener {

    private final int DASH_DISTANCE = 12;
    private final long COOLDOWN_DURATION = 8 * 20; // 8 seconds in ticks


    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        ItemStack result = event.getRecipe().getResult();
        if (hasCoastTrim(result)) {
            // Check if the crafted item has the correct ingredients
            if (hasCorrectIngredients(event)) {
                ItemMeta meta = result.getItemMeta();
                meta.setCustomModelData(123); // Set custom model data for Coast Trim
                result.setItemMeta(meta);
                // Apply color based on adjacent ore
                Color color = getColorFromAdjacentOre(event);
                if (color != null) {
                    applyColor(result, color);
                }
            } else {
                // Cancel crafting if the ingredients are incorrect
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.SMITHING) {
            Player player = (Player) event.getWhoClicked();
            ItemStack result = event.getCurrentItem();
            if (isCustomSword(result)) {
                // Apply color based on adjacent ore
                Color color = getColorFromAdjacentOre(event);
                if (color != null) {
                    applyColor(result, color);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (hasCoastTrim(item) && (player.isSwimming() || player.getWorld().hasStorm() || player.getWorld().isThundering())) {
            if (event.getAction().name().contains("RIGHT_CLICK")) {
                if (!isOnCooldown(player)) {
                    dash(player);
                    startCooldown(player);
                } else {
                    player.sendMessage("You must wait before dashing again.");
                }
            }
        }
    }

    private boolean hasCoastTrim(ItemStack item) {
        return item != null && item.getType() == Material.DIAMOND_SWORD &&
                item.hasItemMeta() &&
                item.getItemMeta().hasCustomModelData() &&
                Arrays.asList(1, 2, 3, 4, 5, 6).contains(item.getItemMeta().getCustomModelData());
    }

    private void dash(Player player) {
        player.setVelocity(player.getLocation().getDirection().multiply(DASH_DISTANCE));
    }

    private boolean isOnCooldown(Player player) {
        // Check if player has cooldown metadata
        return player.hasMetadata("coast_dash_cooldown");
    }

    private void startCooldown(Player player) {
        // Apply cooldown metadata
        player.setMetadata("coast_dash_cooldown", new FixedMetadataValue(new TrimsSmp(), true));

        // Schedule removal of cooldown metadata after cooldown duration
        new BukkitRunnable() {
            @Override
            public void run() {
                player.removeMetadata("coast_dash_cooldown", new TrimsSmp());
            }
        }.runTaskLater(new TrimsSmp(), COOLDOWN_DURATION);
    }

    private boolean isCustomSword(ItemStack item) {
        return item != null && (
                item.getType() == Material.DIAMOND_SWORD ||
                        item.getType() == Material.NETHERITE_SWORD ||
                        item.getType() == Material.GOLDEN_SWORD ||
                        item.getType() == Material.IRON_SWORD
        );
    }

    private boolean hasCorrectIngredients(CraftItemEvent event) {
        // Check if the crafting ingredients include the Coast Trim, one of the four types of swords, and an ore for coloring
        ItemStack[] matrix = event.getInventory().getMatrix();
        boolean hasCoastTrim = false;
        boolean hasSword = false;
        boolean hasOre = false;
        for (ItemStack item : matrix) {
            if (hasCoastTrim(item)) {
                hasCoastTrim = true;
            } else if (isCustomSword(item)) {
                hasSword = true;
            } else if (isOre(item.getType())) {
                hasOre = true;
            }
        }
        return hasCoastTrim && hasSword && hasOre;
    }

    private boolean isOre(Material material) {
        return material == Material.AMETHYST_SHARD || // Adjust ore materials as needed
                material == Material.COPPER_INGOT ||
                material == Material.QUARTZ ||
                material == Material.EMERALD ||
                material == Material.LAPIS_LAZULI ||
                material == Material.REDSTONE;
    }

    private Color getColorFromAdjacentOre(InventoryClickEvent event) {
        ItemStack[] matrix = ((CraftingInventory) event.getInventory()).getMatrix();
        for (ItemStack item : matrix) {
            if (item != null && isOre(item.getType())) {
                // Implement color detection based on adjacent ores here
                return Color.PURPLE; // Placeholder color for amethyst shard
            }
        }
        return null;
    }

    private void applyColor(ItemStack item, Color color) {

    }
}

