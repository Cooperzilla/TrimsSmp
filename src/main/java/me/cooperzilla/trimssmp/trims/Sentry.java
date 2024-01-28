package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.misc.CustomArrow;
import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public class Sentry extends ItemClass {

    public Sentry() {
        super(NumUtils.getNum(9), NumUtils.seconds(45), "sentry_cooldown");
    }

    @Override
    protected void run(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        player.launchProjectile(CustomArrow.class, player.getLocation().getDirection());
    }
}
