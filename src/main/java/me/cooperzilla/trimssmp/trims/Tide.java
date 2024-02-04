package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Tide extends ItemClass {

    public Tide(JavaPlugin pl) {
        super(NumUtils.getNum(15), NumUtils.seconds(45), "tide_cooldown", pl);
    }

    @Override
    protected void run(Player player, ItemStack item) {
        TNTPrimed w = (TNTPrimed) player.getWorld().spawnEntity(player.getLocation(), EntityType.PRIMED_TNT);
        w.setVelocity(player.getLocation().getDirection().multiply(1.7));
    }
}
