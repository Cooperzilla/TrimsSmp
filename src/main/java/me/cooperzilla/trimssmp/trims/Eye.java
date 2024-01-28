package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Eye extends ItemClass {

    public Eye(JavaPlugin pl) {
        super(NumUtils.getNum(8), NumUtils.seconds(30), "eye_cooldown", pl);
    }

    @Override
    protected void run(Player player, ItemStack item) {
        player.launchProjectile(EnderPearl.class, player.getLocation().getDirection());
    }
}
