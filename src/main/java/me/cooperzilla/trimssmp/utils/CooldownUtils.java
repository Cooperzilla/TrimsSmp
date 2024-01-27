package me.cooperzilla.trimssmp.utils;

import me.cooperzilla.trimssmp.TrimsSmp;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class CooldownUtils {
    public static void setCooldown(Player player, String str, Long cooldown) {
        player.setMetadata(str, new FixedMetadataValue(new TrimsSmp(), true));

        new BukkitRunnable() {
            @Override
            public void run() {
                player.removeMetadata(str, new TrimsSmp());
            }
        }.runTaskLater(new TrimsSmp(), cooldown * player.getMetadata("cooldown_multiplier").get(0).asLong());
    }
}
