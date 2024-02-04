package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Silence extends ItemClass {
    public Silence(JavaPlugin pl) {
        super(NumUtils.getNum(14), NumUtils.minutes(1), "silence_cooldown", pl);
    }

    @Override
    protected void run(Player player, ItemStack item) {
        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof Player && entity != player) {
                entity.setMetadata("canheal", new FixedMetadataValue(pl, true));

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.removeMetadata("canheal", pl);
                    }
                }.runTaskLater(pl, NumUtils.seconds(10));

            }
        }
    }

    @EventHandler
    public void onHeal(EntityRegainHealthEvent event) {
        Player player;

        if (event.getEntity() instanceof Player) {
            player = (Player) event.getEntity();
        } else {
            return;
        }

        if (player.hasMetadata("canheal")) {
            event.setCancelled(true);
        }
    }
}
