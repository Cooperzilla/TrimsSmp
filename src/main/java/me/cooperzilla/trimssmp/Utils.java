package me.cooperzilla.trimssmp;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class Utils {
    static boolean isOre(Material material) {
        return material == Material.AMETHYST_SHARD || // Adjust ore materials as needed
                material == Material.COPPER_INGOT ||
                material == Material.QUARTZ ||
                material == Material.EMERALD ||
                material == Material.LAPIS_LAZULI ||
                material == Material.REDSTONE;
    }

    static Color getColorFromAdjacentOre(InventoryClickEvent event) {
        ItemStack[] matrix = ((CraftingInventory) event.getInventory()).getMatrix();
        for (ItemStack item : matrix) {
            if (item != null && isOre(item.getType())) {
                // Implement color detection based on adjacent ores here
                return Color.PURPLE; // Placeholder color for amethyst shard
            }
        }
        return null;
    }


    static boolean isCustomSword(ItemStack item) {
        return item != null && (
                item.getType() == Material.DIAMOND_SWORD ||
                        item.getType() == Material.NETHERITE_SWORD ||
                        item.getType() == Material.GOLDEN_SWORD ||
                        item.getType() == Material.IRON_SWORD
        );
    }
}
