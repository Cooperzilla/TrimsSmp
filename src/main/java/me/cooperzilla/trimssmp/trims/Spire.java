package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Spire extends ItemClass {

    public Spire() {
        super(NumUtils.getNum(7), NumUtils.minutes(10), "spire_cooldown");
    }

    @Override
    protected void run(Player player, ItemStack item) {

        player.setVelocity(
                new Vector(
                        player.getLocation().getDirection().getX(),
                        player.getLocation().getDirection().getY() * 2,
                        player.getLocation().getDirection().getZ()
                )
        );

        player.setVelocity(
                new Vector(
                        player.getLocation().getDirection().getX(),
                        player.getLocation().getDirection().getY() * -2,
                        player.getLocation().getDirection().getZ()
                )
        );

    }
}


