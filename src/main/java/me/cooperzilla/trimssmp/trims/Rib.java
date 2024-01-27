package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.TrimsSmp;
import me.cooperzilla.trimssmp.utils.Utils;
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

import static me.cooperzilla.trimssmp.utils.Utils.applyColor;

public class Rib implements Listener {

    private final int num = 37;
    private final long COOLDOWN_DURATION = 60 * 20;

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
                if (!(player.hasMetadata("rib_cooldown"))) {
                    summonWitherSkeletons(player);
                    Utils.setCooldown(player, "rib_cooldown", COOLDOWN_DURATION);
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