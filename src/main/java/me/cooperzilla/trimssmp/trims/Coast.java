package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Coast extends ItemClass {

    public Coast(JavaPlugin pl) {
        super(NumUtils.getNum(1), NumUtils.seconds(6), "coast_cooldown", pl);
    }

    @Override
    protected void run(Player player, ItemStack item) {
        if (player.isSwimming() || player.getWorld().hasStorm() || player.getWorld().isThundering() || player.isInWater()) {
            player.setVelocity(player.getLocation().getDirection().multiply(2));
        } else {
            player.sendMessage("not in water cannot riptide");
        }

    }
}
