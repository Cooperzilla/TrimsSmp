package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.TrimsSmp;
import me.cooperzilla.trimssmp.utils.CheaksUtils;
import me.cooperzilla.trimssmp.utils.ColorUtils;
import me.cooperzilla.trimssmp.utils.CooldownUtils;
import me.cooperzilla.trimssmp.utils.NumUtils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Rib implements Listener {

    private final int num = NumUtils.getNum(5);
    private final long COOLDOWN_DURATION = NumUtils.minutes(1);

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
                if (!(player.hasMetadata("rib_cooldown"))) {
                    summonWitherSkeletons(player);
                    CooldownUtils.setCooldown(player, "rib_cooldown", COOLDOWN_DURATION);
                } else {
                    player.sendMessage("Ability on cooldown!");
                }
            }
        }
    }

    private void summonWitherSkeletons(Player player) {
        World world = new TrimsSmp().getServer().getWorld("world");
        for (int i = 0; i < 3; i++) {
            Location loc = player.getLocation();
            loc.setX(loc.getX() + new Random().nextInt(10) - 5);
            loc.setZ(loc.getZ() + new Random().nextInt(10) - 5);
            WitherSkeleton w = (WitherSkeleton) world.spawnEntity(loc, EntityType.WITHER_SKELETON);
        }

    }
}