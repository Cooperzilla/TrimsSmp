package me.cooperzilla.trimssmp.utils;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

class RunnableClass extends BukkitRunnable {
    Color color;
    ItemStack[] inv;
    Integer num;
    JavaPlugin pl;

    public RunnableClass(Color color, ItemStack[] inv, Integer num, JavaPlugin pl) {
        this.color = color;
        this.inv = inv;
        this.num = num;
        this.pl = pl;
    }
    @Override
    public void run() {
        for (ItemStack i : inv) {
            if (i != null) {
                pl.getLogger().info(i.toString());
                if (CheaksUtils.isSword(i.getType())) {
                    pl.getLogger().info("DEBUG isSword");
                    if (!Objects.requireNonNull(i.getItemMeta()).hasCustomModelData()) {
                        pl.getLogger().info("DEBUG hasCustomTexture");
                        ColorUtils.applyColor(i, color, num);
                    }
                }
            }
        }
    }

}