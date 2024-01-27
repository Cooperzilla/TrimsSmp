package me.cooperzilla.trimssmp.trims;

import me.cooperzilla.trimssmp.utils.CheaksUtils;
import me.cooperzilla.trimssmp.utils.ColorUtils;
import me.cooperzilla.trimssmp.utils.CooldownUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Wild implements Listener {

    private final int num = 46;
    private final long COOLDOWN_DURATION = 5 * 60 * 20;

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
                if (!(player.hasMetadata("wild_cooldown"))) {
                    armorBreak(player);
                    CooldownUtils.setCooldown(player, "wild_cooldown", COOLDOWN_DURATION);
                } else {
                    player.sendMessage("Ability on cooldown!");
                }
            }
        }
    }

    private void armorBreak(Player player) {
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