package me.cooperzilla.trimssmp.utils;

import org.bukkit.Material;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class CheaksUtils {
    public static boolean isOre(Material material) {
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

    public static boolean hasTrim(ItemStack item, Integer num) {
        return  item.hasItemMeta() &&
                item.getItemMeta().hasCustomModelData() &&
                Arrays.asList(num, num+1, num+2, num+3, num+4, num+5, num+6, num+7, num+8, num+9).contains(item.getItemMeta().getCustomModelData());
    }

    public static boolean hasCorrectIngredients(CraftItemEvent event, Integer num) {
        ItemStack[] matrix = event.getInventory().getMatrix();
        boolean hasSword = false;
        boolean hasOre = false;

        for (ItemStack item : matrix) {
            if (item != null) {
                if (CheaksUtils.isSword(item.getType())) {
                    hasSword = true;
                } else if (CheaksUtils.isOre(item.getType())) {
                    hasOre = true;
                }
            }
        }

        return hasSword && hasOre;
    }

    public static boolean isSword(Material material) {
        return (
                material == Material.DIAMOND_SWORD || // Adjust ore materials as needed
                material == Material.GOLDEN_SWORD ||
                material == Material.NETHERITE_SWORD ||
                material == Material.IRON_SWORD
        );
    }

    public static boolean isCorrectTrim(ItemStack trim, String str) {
        return trim.getType().toString().toLowerCase() == str.split("_")[0];
    }

}
