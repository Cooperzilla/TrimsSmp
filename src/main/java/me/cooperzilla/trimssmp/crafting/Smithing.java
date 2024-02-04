package me.cooperzilla.trimssmp.crafting;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.inventory.*;

public class Smithing {
    public static Recipe dia() {
        return new SmithingTransformRecipe(
               new NamespacedKey("trimssmp", "dia_sword_smith"),
               new ItemStack(Material.DIAMOND_SWORD),
               new RecipeChoice.MaterialChoice(Tag.ITEMS_TRIM_TEMPLATES),
               new RecipeChoice.MaterialChoice(Tag.ITEMS_SWORDS),
               new RecipeChoice.MaterialChoice(Tag.ITEMS_TRIM_MATERIALS));
    }

    public static Recipe neth() {
        return new SmithingTransformRecipe(
                new NamespacedKey("trimssmp", "neth_sword_smith"),
                new ItemStack(Material.NETHERITE_SWORD),
                new RecipeChoice.MaterialChoice(Tag.ITEMS_TRIM_TEMPLATES),
                new RecipeChoice.MaterialChoice(Material.NETHERITE_SWORD),
                new RecipeChoice.MaterialChoice(Tag.ITEMS_TRIM_MATERIALS));
    }

    public static Recipe gold() {
        return new SmithingTransformRecipe(
                new NamespacedKey("trimssmp", "gold_sword_smith"),
                new ItemStack(Material.GOLDEN_SWORD),
                new RecipeChoice.MaterialChoice(Tag.ITEMS_TRIM_TEMPLATES),
                new RecipeChoice.MaterialChoice(Material.GOLDEN_SWORD),
                new RecipeChoice.MaterialChoice(Tag.ITEMS_TRIM_MATERIALS));
    }

    public static Recipe iron() {
        return new SmithingTransformRecipe(
                new NamespacedKey("trimssmp", "iron_sword_smith"),
                new ItemStack(Material.IRON_SWORD),
                new RecipeChoice.MaterialChoice(Tag.ITEMS_TRIM_TEMPLATES),
                new RecipeChoice.MaterialChoice(Material.IRON_SWORD),
                new RecipeChoice.MaterialChoice(Tag.ITEMS_TRIM_MATERIALS));
    }
}