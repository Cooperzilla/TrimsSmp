package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.CheaksUtils;
import me.cooperzilla.trimssmp.utils.ColorUtils;
import me.cooperzilla.trimssmp.utils.CooldownUtils;
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

public class Ward implements Listener {

    private final int num = 19;
    private final long COOLDOWN_DURATION = 60 * 20;

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        ItemStack result = event.getRecipe().getResult();
        if (CheaksUtils.hasCorrectIngredients(event, num)) {
            Color color = ColorUtils.getColorFromAdjacentOre(event);
            if (color != null) {
                ColorUtils.applyColor(result, color, num);
            }
        } else {
            event.setCancelled(true);
        }
    }

   @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
       ItemStack item = event.getItem();

       if (item == null) {
           return;
       }

        if (CheaksUtils.hasTrim(item, num)) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (!(player.hasMetadata("ward_cooldown"))) {
                        applySonicBoom(player);
                        CooldownUtils.setCooldown(player, "ward_cooldown", COOLDOWN_DURATION);
                    } else {
                        player.sendMessage("Ability on cooldown!");
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