package me.cooperzilla.trimssmp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Wayfinder implements Listener {

    private Map<Player, Long> wayfinderCooldowns = new HashMap<>();
    private final long wayfinderCooldownDuration = 60 * 1000; // 1 minute in milliseconds

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        ItemStack result = event.getRecipe().getResult();
        if (Utils.isCustomSword(result)) {
            // Check if the crafted item has the correct ingredients
            if (hasCorrectIngredients(event)) {
                ItemMeta meta = result.getItemMeta();
                meta.setCustomModelData(789); // Set custom model data for Wayfinder Trim
                result.setItemMeta(meta);
                // Apply color based on adjacent ore
                Color color = Utils.getColorFromAdjacentOre(event);
                if (color != null) {
                    applyColor(result, color);
                }
            } else {
                // Cancel crafting if the ingredients are incorrect
                event.setCancelled(true);
            }
        }
    }

    private boolean hasCorrectIngredients(CraftItemEvent event) {
        ItemStack[] matrix = event.getInventory().getMatrix();
        boolean hasSword = false;
        boolean hasTrim = false;
        boolean hasOre = false;

        for (ItemStack item : matrix) {
            if (item != null) {
                if (Utils.isCustomSword(item)) {
                    hasSword = true;
                } else if (hasWayfinderTrim(item)) {
                    hasTrim = true;
                } else if (Utils.isOre(item.getType())) {
                    hasOre = true;
                }
            }
        }

        return hasSword && hasTrim && hasOre;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.SMITHING) {
            Player player = (Player) event.getWhoClicked();
            ItemStack result = event.getCurrentItem();
            if (Utils.isCustomSword(result)) {
                // Apply color based on adjacent ore
                Color color = Utils.getColorFromAdjacentOre(event);
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

        if (hasWayfinderTrim(item)) {
            if (event.getAction().name().contains("RIGHT_CLICK")) {
                // Revealing the nearest player within 500 blocks
                if (!wayfinderCooldowns.containsKey(player) || System.currentTimeMillis() - wayfinderCooldowns.get(player) >= wayfinderCooldownDuration) {
                    revealNearestPlayer(player);
                    wayfinderCooldowns.put(player, System.currentTimeMillis());
                } else {
                    player.sendMessage(ChatColor.RED + "Wayfinder Trim: " + ChatColor.WHITE + "You must wait before tracking again.");
                }
            }
        }
    }

    private void revealNearestPlayer(Player player) {
        Player nearestPlayer = null;
        double nearestDistance = 25;
        for (Player other : Bukkit.getOnlinePlayers()) {
            if (other != player) {
                double distance = player.getLocation().distance(other.getLocation());
                if (distance < nearestDistance) {
                    nearestPlayer = other;
                    nearestDistance = distance;
                }
            }
        }
        if (nearestPlayer != null) {
            player.sendMessage(ChatColor.GREEN + "Nearest player found at: " + ChatColor.WHITE + nearestPlayer.getLocation().getBlockX() + ", " + nearestPlayer.getLocation().getBlockY() + ", " + nearestPlayer.getLocation().getBlockZ());
        } else {
            player.sendMessage(ChatColor.RED + "No player found within tracking range.");
        }
    }

    private boolean hasWayfinderTrim(ItemStack item) {
        return item != null && item.getType() == Material.DIAMOND_SWORD &&
                item.hasItemMeta() &&
                item.getItemMeta().hasCustomModelData() &&
                Arrays.asList(1, 2, 3, 4, 5, 6).contains(item.getItemMeta().getCustomModelData());
    }

    private void applyColor(ItemStack item, Color color) {

    }
}