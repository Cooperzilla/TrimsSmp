package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Raiser extends ItemClass {

    public Raiser() {
        super(NumUtils.getNum(4), NumUtils.seconds(8), "raiser_cooldown");
    }


    @Override
    protected void run(Player player, ItemStack item) {
        player.setVelocity(player.getLocation().getDirection().multiply(1.5));
    }
}

