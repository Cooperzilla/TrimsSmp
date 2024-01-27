package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.CheaksUtils;
import me.cooperzilla.trimssmp.utils.ColorUtils;
import me.cooperzilla.trimssmp.utils.CooldownUtils;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Spire implements Listener {

    private final int num = NumUtils.getNum(7);
    private final long COOLDOWN_DURATION = NumUtils.minutes(10);

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

        if (CheaksUtils.hasTrim(item, num) && (player.isSwimming() || player.getWorld().hasStorm() || player.getWorld().isThundering())) {
            if (event.getAction().name().contains("RIGHT_CLICK")) {
                if (!(player.hasMetadata("spire_cooldown"))) {

                    player.setVelocity(
                            new Vector(
                                    player.getLocation().getDirection().getX(),
                                    player.getLocation().getDirection().getY() * 2,
                                    player.getLocation().getDirection().getZ()
                            )
                    );

                    player.setVelocity(
                            new Vector(
                                    player.getLocation().getDirection().getX(),
                                    player.getLocation().getDirection().getY() * -2,
                                    player.getLocation().getDirection().getZ()
                            )
                    );

                    CooldownUtils.setCooldown(player, "spire_cooldown", COOLDOWN_DURATION);

                } else {
                    player.sendMessage("Ability on cooldown!");
                }
            }
        }
    }
}
