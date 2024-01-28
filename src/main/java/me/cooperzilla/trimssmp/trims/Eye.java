package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Eye extends ItemClass {

    public Eye() {
        super(NumUtils.getNum(8), NumUtils.seconds(30), "eye_cooldown");
    }

    @Override
    protected void run(Player player, ItemStack item) {
        player.launchProjectile(EnderPearl.class, player.getLocation().getDirection());
    }
}
