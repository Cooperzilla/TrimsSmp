package me.cooperzilla.trimssmp;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static me.cooperzilla.trimssmp.Utils.applyColor;

public class Ward implements Listener {

    private final int num = 19;
    private Map<Player, Long> wardCooldowns = new HashMap<>();
    private final long wardCooldownDuration = 60 * 1000; // 1 minute in milliseconds

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
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (!wardCooldowns.containsKey(player) || System.currentTimeMillis() - wardCooldowns.get(player) >= wardCooldownDuration) {
                        applySonicBoom(player);
                        wardCooldowns.put(player, System.currentTimeMillis());
                        player.sendMessage(ChatColor.GREEN + "Ward Trim: " + ChatColor.WHITE + "Sonic boom unleashed!");
                    } else {
                        player.sendMessage(ChatColor.RED + "Ward Trim: " + ChatColor.WHITE + "You must wait before using sonic boom again.");
                }
            }
        }
    }

    private void applySonicBoom(Player player) {
        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            if (entity instanceof LivingEntity && entity != player) {
                ((LivingEntity) entity).damage(8);
            }
        }
    }
}