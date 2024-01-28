package me.cooperzilla.trimssmp.crafting;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.inventory.*;

public class Smithing {

    public static Recipe run() {
        NamespacedKey ns = new NamespacedKey("trimssmp", "sword_smith");

        SmithingRecipe recipe = new SmithingTransformRecipe(
               ns,
               new ItemStack(Material.DIAMOND_SWORD),
               new RecipeChoice.MaterialChoice(Tag.ITEMS_TRIM_TEMPLATES),
               new RecipeChoice.MaterialChoice(Tag.ITEMS_SWORDS),
               new RecipeChoice.MaterialChoice(Tag.ITEMS_TRIM_MATERIALS));

        return null;
    }
}