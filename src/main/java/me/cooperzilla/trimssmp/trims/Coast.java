package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Coast extends ItemClass {

    public Coast() {
        super(NumUtils.getNum(1), NumUtils.seconds(6), "coast_cooldown");
    }

    @Override
    protected void run(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (player.isSwimming() || player.getWorld().hasStorm() || player.getWorld().isThundering()) {
            player.setVelocity(player.getLocation().getDirection().multiply(2));
        }

    }
}
