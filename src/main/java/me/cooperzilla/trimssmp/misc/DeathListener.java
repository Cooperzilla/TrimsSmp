package me.cooperzilla.trimssmp.misc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Date;

public class DeathListener implements Listener {

    private final JavaPlugin pl;

    public DeathListener(JavaPlugin pl) {
        this.pl = pl;
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() == null) {
            return;
        }

        Player p = event.getEntity();

        if (p.hasMetadata("lives")) {
                p.setMetadata("lives", new FixedMetadataValue(pl, p.getMetadata("lives").get(0).asInt() + 1));
        } else {
            p.setMetadata("lives", new FixedMetadataValue(pl, 2));
        }

        Player pp = p.getKiller();

        if (pp.hasMetadata("lives")) {
            pp.setMetadata("lives", new FixedMetadataValue(pl, pp.getMetadata("lives").get(0).asInt() + 1));
        } else {
            pp.setMetadata("lives", new FixedMetadataValue(pl, 4));
        }

        if (p.getMetadata("lives").get(0).asInt() == 0) {
            p.setMetadata("lives", new FixedMetadataValue(pl, 2));
            p.ban("Out of Lives", (Date) null, "trimsspm", true);
        }
    }
}
