package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.ItemClass;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Wayfinder extends ItemClass {

    public Wayfinder() {
        super(NumUtils.getNum(2), NumUtils.minutes(1), "wayfinder_cooldown");
    }

    @Override
    protected void run(Player player, ItemStack item) {
        Player nearestPlayer = null;
        double nearestDistance = 25;
        for (Player other : Bukkit.getOnlinePlayers()) {
            if (other != player) {
                double distance = player.getLocation().distance(other.getLocation());
                if (distance <= nearestDistance) {
                    nearestPlayer = other;
                }
            }
        }
        if (nearestPlayer != null) {
            player.sendMessage(ChatColor.GREEN + "Nearest player found at: " + ChatColor.WHITE + nearestPlayer.getLocation().getBlockX() + ", " + nearestPlayer.getLocation().getBlockY() + ", " + nearestPlayer.getLocation().getBlockZ());
        } else {
            player.sendMessage(ChatColor.RED + "No player found within tracking range");
        }
    }
}