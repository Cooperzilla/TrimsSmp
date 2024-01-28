package me.cooperzilla.trimssmp.utils;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class ItemClass implements Listener {

    private final Integer num;
    private final Long COOLDOWN_DURATION;
    private final String str;
    private final JavaPlugin pl;

    public ItemClass(Integer num, Long cooldownDuration, String str, JavaPlugin pl) {
        this.num = num;
        this.COOLDOWN_DURATION = cooldownDuration;
        this.str = str;
        this.pl = pl;
    }

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
            if (event.getAction().name().contains("RIGHT_CLICK")) {
                if (!player.hasMetadata(str)) {
                    run(player, item);
                    CooldownUtils.setCooldown(player, str, COOLDOWN_DURATION, pl);
                } else {
                    player.sendMessage("Ability on cooldown!");
                }
            }
        }
    }

    protected abstract void run(Player player, ItemStack item);
}