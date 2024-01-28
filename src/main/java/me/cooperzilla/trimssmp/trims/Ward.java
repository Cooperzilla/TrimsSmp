package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Ward extends ItemClass {

    public Ward(JavaPlugin pl) {
        super(NumUtils.getNum(3), NumUtils.minutes(1), "ward_cooldown", pl);
    }

    @Override
    protected void run(Player player, ItemStack item) {

        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof LivingEntity && entity != player) {
                ((LivingEntity) entity).damage(8);
            }
        }
    }
}