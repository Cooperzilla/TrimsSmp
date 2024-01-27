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

import java.util.HashMap;
import java.util.Map;

import static me.cooperzilla.trimssmp.Utils.applyColor;

public class Wayfinder implements Listener {

    private Integer num = 10;
    private Map<Player, Long> wayfinderCooldowns = new HashMap<>();
    private final long wayfinderCooldownDuration = 60000;

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        ItemStack result = event.getRecipe().getResult();
        if (Utils.hasCorrectIngredients(event, num)) {
                Color color = Utils.getColorFromAdjacentOre(event);
                if (color != null) {
                    applyColor(result, color, num);
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
                if (!wayfinderCooldowns.containsKey(player) || System.currentTimeMillis() - wayfinderCooldowns.get(player) >= wayfinderCooldownDuration) {
                    revealNearestPlayer(player);
                    wayfinderCooldowns.put(player, System.currentTimeMillis());
                } else {
                    player.sendMessage(ChatColor.RED + "Wayfinder Trim: " + ChatColor.WHITE + "You must wait before tracking again.");
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
            player.sendMessage(ChatColor.RED + "No player found within tracking range.");
        }
    }
}