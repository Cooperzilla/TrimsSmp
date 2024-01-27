package me.cooperzilla.trimssmp;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Utils {
    static boolean isOre(Material material) {
        return (
                material == Material.AMETHYST_SHARD || // Adjust ore materials as needed
                material == Material.COPPER_INGOT ||
                material == Material.QUARTZ ||
                material == Material.EMERALD ||
                material == Material.LAPIS_LAZULI ||
                material == Material.REDSTONE ||
                material == Material.DIAMOND ||
                material == Material.GOLD_INGOT ||
                material == Material.NETHERITE_INGOT ||
                material == Material.IRON_INGOT
        );
    }

    static Color getColorFromAdjacentOre(InventoryClickEvent event) {
        ItemStack[] matrix = ((CraftingInventory) event.getInventory()).getMatrix();
        for (ItemStack item : matrix) {
            if (item != null && isOre(item.getType())) {
                switch (item.getType()) {
                    case AMETHYST_SHARD: return Color.PURPLE;
                    case EMERALD: return Color.GREEN;
                    case COPPER_INGOT: return Color.ORANGE;
                    case DIAMOND: return Color.TEAL;
                    case GOLD_INGOT: return Color.YELLOW;
                    case LAPIS_LAZULI: return Color.BLUE;
                    case NETHERITE_INGOT: return Color.BLACK;
                    case REDSTONE: return Color.RED;
                    case IRON_INGOT:
                    case QUARTZ:
                        return Color.WHITE;
                }
            }
        }
        return null;
    }

    static void applyColor(ItemStack item, Color color, Integer num) {
        Integer offset = 0;

        if (color.equals(Color.PURPLE)) {
            offset = 0;
        } else if (color.equals(Color.GREEN)) {
            offset = 1;
        } else if (color.equals(Color.ORANGE)) {
            offset = 2;
        } else if (color.equals(Color.TEAL)) {
            offset = 3;
        } else if (color.equals(Color.YELLOW)) {
            offset = 4;
        } else if (color.equals(Color.BLUE)) {
            offset = 5;
        } else if (color.equals(Color.BLACK)) {
            offset = 6;
        } else if (color.equals(Color.RED)) {
            offset = 7;
        } else if (color.equals(Color.WHITE)) {
            offset = 8;
        }

        item.getItemMeta().setCustomModelData(num + offset);
    }

    static boolean hasTrim(ItemStack item, Integer num) {
        return  item.hasItemMeta() &&
                item.getItemMeta().hasCustomModelData() &&
                Arrays.asList(num, num+1, num+2, num+3, num+4, num+5, num+6, num+7, num+8).contains(item.getItemMeta().getCustomModelData());
    }

    static boolean hasCorrectIngredients(CraftItemEvent event, Integer num) {
        ItemStack[] matrix = event.getInventory().getMatrix();
        boolean hasTrim = false;
        boolean hasOre = false;

        for (ItemStack item : matrix) {
            if (item != null) {
                if (Utils.hasTrim(item, num)) {
                    hasTrim = true;
                } else if (Utils.isOre(item.getType())) {
                    hasOre = true;
                }
            }
        }

        return hasTrim && hasOre;
    }
}
