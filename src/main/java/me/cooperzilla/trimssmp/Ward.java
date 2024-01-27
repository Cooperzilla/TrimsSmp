package me.cooperzilla.trimssmp;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Ward implements Listener {

    private Map<Player, Long> wardCooldowns = new HashMap<>();
    private final long wardCooldownDuration = 60 * 1000; // 1 minute in milliseconds

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
                } else if (hasWardTrim(item)) {
                    hasTrim = true;
                } else if (Utils.isOre(item.getType())) {
                    hasOre = true;
                }
            }
        }

        return hasSword && hasTrim && hasOre;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (hasWardTrim(item)) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (player.hasPermission("wardsword.use")) {
                    if (!wardCooldowns.containsKey(player) || System.currentTimeMillis() - wardCooldowns.get(player) >= wardCooldownDuration) {
                        // Apply the sonic boom effect
                        applySonicBoom(player);
                        // Set cooldown
                        wardCooldowns.put(player, System.currentTimeMillis());
                        // Inform the player
                        player.sendMessage(ChatColor.GREEN + "Ward Trim: " + ChatColor.WHITE + "Sonic boom unleashed!");
                    } else {
                        // Inform the player about cooldown
                        player.sendMessage(ChatColor.RED + "Ward Trim: " + ChatColor.WHITE + "You must wait before using sonic boom again.");
                    }
                } else {
                    // Inform the player about lacking permission
                    player.sendMessage(ChatColor.RED + "Ward Trim: " + ChatColor.WHITE + "You don't have permission to use sonic boom.");
                }
            }
        }
    }

    private void applySonicBoom(Player player) {
        // Apply sonic boom effect here
        // For example, apply damage to nearby entities
        // You can customize this based on your desired effect
        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof LivingEntity && entity != player) {
                ((LivingEntity) entity).damage(8); // 4 hearts of damage
            }
        }
    }

    private boolean hasWardTrim(ItemStack item) {
        return item != null && item.getType() == Material.DIAMOND_SWORD &&
                item.hasItemMeta() &&
                item.getItemMeta().hasCustomModelData() &&
                Arrays.asList(13, 14, 15, 16, 17, 18).contains(item.getItemMeta().getCustomModelData());
    }

    private void applyColor(ItemStack item, Color color) {

    }
}