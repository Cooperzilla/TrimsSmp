package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.misc.CustomArrow;
import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Sentry extends ItemClass {

    public Sentry(JavaPlugin pl) {
        super(NumUtils.getNum(9), NumUtils.seconds(45), "sentry_cooldown", pl);
    }

    @Override
    protected void run(Player player, ItemStack item) {
        player.launchProjectile(CustomArrow.class, player.getLocation().getDirection());
    }
}
