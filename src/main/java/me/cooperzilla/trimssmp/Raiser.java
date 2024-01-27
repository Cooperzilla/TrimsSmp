package me.cooperzilla.trimssmp;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static me.cooperzilla.trimssmp.Utils.applyColor;

public class Raiser implements Listener {

    private final int num = 28;

    private final int DASH_DISTANCE = 8;
    private final long COOLDOWN_DURATION = 8 * 20; // 8 seconds in ticks


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
                if (!(player.hasMetadata("raiser_cooldown"))) {
                    player.setVelocity(player.getLocation().getDirection().multiply(DASH_DISTANCE));
                    Utils.setCooldown(player, "raiser_cooldown", COOLDOWN_DURATION);;
                } else {
                    player.sendMessage("You must wait before dashing again.");
                }
            }
        }
    }
}

