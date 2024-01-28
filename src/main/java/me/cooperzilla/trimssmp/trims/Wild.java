package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Random;

public class Wild extends ItemClass {

    public Wild() {
        super(NumUtils.getNum(6), NumUtils.minutes(5), "wild_cooldown");
    }

    @Override
    protected void run(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Player nearestPlayer = null;
        double nearestDistance = 10;
        for (Player other : Bukkit.getOnlinePlayers()) {
            if (other != player) {
                double distance = player.getLocation().distance(other.getLocation());
                if (distance <= nearestDistance) {
                    nearestPlayer = other;
                }
            }
        }
        if (nearestPlayer != null) {
            switch (new Random().nextInt(3)) {
                case 0:
                    player.getInventory().getHelmet().setDurability(
                            (short) (player.getInventory().getHelmet().getDurability() - (player.getInventory().getHelmet().getDurability()/4))
                    );
                    break;
                case 1:
                    player.getInventory().getLeggings().setDurability(
                            (short) (player.getInventory().getLeggings().getDurability() - (player.getInventory().getLeggings().getDurability()/4))
                    );
                    break;
                case 2:
                    player.getInventory().getChestplate().setDurability(
                            (short) (player.getInventory().getChestplate().getDurability() - (player.getInventory().getChestplate().getDurability()/4))
                    );
                    break;
                case 3:
                    player.getInventory().getBoots().setDurability(
                            (short) (player.getInventory().getBoots().getDurability() - (player.getInventory().getBoots().getDurability()/4))
                    );
                    break;
            }
        } else {
            player.sendMessage(ChatColor.RED + "No player found within use range");
        }
    }
}