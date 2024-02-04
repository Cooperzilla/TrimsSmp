package me.cooperzilla.trimssmp.utils;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CooldownUtils {
    public static void setCooldown(Player player, String str, Integer cooldown, JavaPlugin pl) {
        player.setMetadata(str, new FixedMetadataValue(pl, true));

        if (!player.hasMetadata("cooldown_multiplier")) {
            player.setMetadata("cooldown_multiplier", new FixedMetadataValue(pl, 1));
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                player.removeMetadata(str, pl);
            }
        }.runTaskLater(pl, (long) cooldown * player.getMetadata("cooldown_multiplier").get(0).asInt());


    }
}
