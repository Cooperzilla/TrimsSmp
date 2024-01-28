package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class Rib extends ItemClass {

    public Rib(JavaPlugin pl) {
        super(NumUtils.getNum(5), NumUtils.minutes(1), "rib_cooldown", pl);
    }

    @Override
    protected void run(Player player, ItemStack item) {
        World world = player.getWorld();

        for (int i = 0; i < 6; i++) {
            Location loc = player.getLocation();
            loc.setX(loc.getX() + new Random().nextInt(10) - 5);
            loc.setZ(loc.getZ() + new Random().nextInt(10) - 5);
            WitherSkeleton w = (WitherSkeleton) world.spawnEntity(loc, EntityType.WITHER_SKELETON);
        }
    }
}