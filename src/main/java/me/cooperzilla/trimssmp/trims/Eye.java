package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.*;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public class Eye extends ItemClass {

    public Eye() {
        super(NumUtils.getNum(8), NumUtils.seconds(30), "eye_cooldown");
    }

    @Override
    protected void run(PlayerInteractEvent event) {
        Player player = event.getPlayer();;
        player.launchProjectile(EnderPearl.class, player.getLocation().getDirection());
    }
}
