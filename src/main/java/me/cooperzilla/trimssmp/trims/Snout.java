package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.*;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Snout extends ItemClass {

    public Snout() {
        super(NumUtils.getNum(11), NumUtils.minutes(1), "snout_cooldown");
    }

    @Override
    protected void run(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        player.launchProjectile(WitherSkull.class, new Vector(
                player.getLocation().getDirection().getX(),
                player.getLocation().getDirection().getY(),
                player.getLocation().getDirection().getZ() + 15
        ));

        player.launchProjectile(WitherSkull.class, player.getLocation().getDirection());

        player.launchProjectile(WitherSkull.class, new Vector(
                player.getLocation().getDirection().getX(),
                player.getLocation().getDirection().getY(),
                player.getLocation().getDirection().getZ() - 15
        ));
    }
}
