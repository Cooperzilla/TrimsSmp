package me.cooperzilla.trimssmp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Wayfinder implements Listener {

    private Integer num = 10;
    private final long COOLDOWN_DURATION = 60 * 20;;

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        ItemStack result = event.getRecipe().getResult();
        if (Utils.hasCorrectIngredients(event, num)) {
                Color color = Utils.getColorFromAdjacentOre(event);
                if (color != null) {
                    Utils.applyColor(result, color, num);
                }
            } else {
                event.setCancelled(true);
            }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (Utils.hasTrim(item, num)) {
            if (event.getAction().name().contains("RIGHT_CLICK")) {
                if (!(player.hasMetadata("wayfinder_cooldown"))) {
                    revealNearestPlayer(player);
                    Utils.setCooldown(player, "wayfinder_cooldown", COOLDOWN_DURATION);
                } else {
                    player.sendMessage("Ability on cooldown!");
                }
            }
        }
    }

    private void revealNearestPlayer(Player player) {
        Player nearestPlayer = null;
        double nearestDistance = 25;
        for (Player other : Bukkit.getOnlinePlayers()) {
            if (other != player) {
                double distance = player.getLocation().distance(other.getLocation());
                if (distance <= nearestDistance) {
                    nearestPlayer = other;
                    nearestDistance = distance;
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