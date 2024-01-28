package me.cooperzilla.trimssmp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.SmithItemEvent;
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
    public void onCraftItem(SmithItemEvent event) throws InterruptedException {
        ItemStack[] matrix = event.getInventory().getStorageContents();
        Event.Result result = event.getResult();

        Color color = ColorUtils.getColorFromAdjacentOre(matrix[2]);

        event.setCancelled(
                !CheaksUtils.hasCorrectIngredients(event, num) ||
                color == null ||
                CheaksUtils.isCorrectTrim(matrix[1], str)
        );

        wait(100, 0);

        if (!event.isCancelled()) {

            Bukkit.getServer().getScheduler().runTaskLater(pl, new RunnableClass(
                    color,
                    event.getWhoClicked().getInventory().getStorageContents(),
                    num,
                    pl
            ), 5L);

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