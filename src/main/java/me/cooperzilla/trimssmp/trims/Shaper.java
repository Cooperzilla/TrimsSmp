package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Shaper extends ItemClass {
    public Shaper(JavaPlugin pl) {
        super(NumUtils.getNum(12), NumUtils.seconds(20), "shaper_cooldown", pl);
    }

    @Override
    protected void run(Player player, ItemStack item) {
        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof Player && entity != player) {
                ((Player) entity).setCooldown(Material.SHIELD, NumUtils.seconds(10));
            }
        }
    }
}
