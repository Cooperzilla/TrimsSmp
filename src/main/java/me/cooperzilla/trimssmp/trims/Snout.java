package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Snout extends ItemClass {

    public Snout() {
        super(NumUtils.getNum(11), NumUtils.minutes(1), "snout_cooldown");
    }

    @Override
    protected void run(Player player, ItemStack item) {

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
