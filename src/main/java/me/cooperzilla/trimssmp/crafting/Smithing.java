package me.cooperzilla.trimssmp.crafting;

import me.cooperzilla.trimssmp.TrimsSmp;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.inventory.*;

public class Smithing {
    static Recipe main() {
        NamespacedKey ns = new NamespacedKey(new TrimsSmp(), "sword_smith");

        SmithingRecipe recipe = new SmithingTransformRecipe(
                ns,
                new ItemStack(Material.DIAMOND_SWORD),
                new RecipeChoice.MaterialChoice(Tag.ITEMS_TRIM_TEMPLATES),
                new RecipeChoice.MaterialChoice(Tag.ITEMS_SWORDS),
                new RecipeChoice.MaterialChoice(Tag.ITEMS_TRIM_MATERIALS));

        return recipe;
    }
}