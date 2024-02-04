package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Vex extends ItemClass {
    public Vex(JavaPlugin pl) {
        super(NumUtils.getNum(16), NumUtils.seconds(45), "tide_cooldown", pl);
    }

    @Override
    protected void run(Player player, ItemStack item) {
        player.addPotionEffect(new PotionEffect(
                PotionEffectType.LEVITATION,
                NumUtils.seconds(10),
                0,
                false
        ));
    }
}
