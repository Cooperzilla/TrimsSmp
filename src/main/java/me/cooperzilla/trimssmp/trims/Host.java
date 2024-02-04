package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Host extends ItemClass {
    public Host(JavaPlugin pl) {
        super(NumUtils.getNum(13), NumUtils.minutes(2), "host_cooldown", pl);
    }

    @Override
    protected void run(Player player, ItemStack item) {
        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof Player && entity != player) {
                entity.setMetadata("canusetrim", new FixedMetadataValue(pl, true));

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.removeMetadata("canusetrim", pl);
                    }
                }.runTaskLater(pl, NumUtils.seconds(30));

            }
        }
    }
}
