package me.cooperzilla.trimssmp.utils;

import org.bukkit.Color;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class ColorUtils {
    public static Color getColorFromAdjacentOre(InventoryClickEvent event) {
        ItemStack[] matrix = ((CraftingInventory) event.getInventory()).getMatrix();
        for (ItemStack item : matrix) {
            if (item != null && CheaksUtils.isOre(item.getType())) {
                switch (item.getType()) {
                    case AMETHYST_SHARD: return Color.PURPLE;
                    case EMERALD: return Color.GREEN;
                    case COPPER_INGOT: return Color.ORANGE;
                    case DIAMOND: return Color.TEAL;
                    case GOLD_INGOT: return Color.YELLOW;
                    case LAPIS_LAZULI: return Color.BLUE;
                    case NETHERITE_INGOT: return Color.BLACK;
                    case REDSTONE: return Color.RED;
                    case IRON_INGOT: return Color.WHITE;
                    case QUARTZ: return Color.GRAY;

                }
            }
        }
        return null;
    }

    public static void applyColor(ItemStack item, Color color, Integer num) {
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
        } else if (color.equals(Color.GRAY)) {
            offset = 9;
        }

        item.getItemMeta().setCustomModelData(num + offset);
    }
}
