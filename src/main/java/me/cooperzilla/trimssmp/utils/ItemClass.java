package me.cooperzilla.trimssmp.utils;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.SmithItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public abstract class ItemClass implements Listener {

    private final Integer num;
    private final Integer COOLDOWN_DURATION;
    private final String str;
    protected JavaPlugin pl;

    public ItemClass(Integer num, Integer cooldownDuration, String str, JavaPlugin pl) {
        this.num = num;
        this.COOLDOWN_DURATION = cooldownDuration;
        this.str = str;
        this.pl = pl;
    }

    @EventHandler
    public void onCraftItem(SmithItemEvent event) {
        ItemStack[] matrix = event.getInventory().getStorageContents();
        Player player = (Player) event.getWhoClicked();

        Color color = ColorUtils.getColorFromAdjacentOre(matrix[2]);

        if (
                !CheaksUtils.hasCorrectIngredients(event, num) ||
                color == null ||
                !CheaksUtils.isCorrectTrim(matrix[0], str, pl)
        ) {
            if (Objects.equals(str.split("_")[0], "coast")) {
                pl.getLogger().info("f's in chat");
            }
            return;
        };

        pl.getLogger().info(str);

        if (event.isCancelled()) {
            pl.getLogger().info("f's in chat 2.0");
            return;
        }

        pl.getLogger().info(str + "could run");

        new BukkitRunnable() {

            @Override
            public void run() {
                pl.getLogger().info(str + "run");

                while (true) {
                    for (ItemStack i : player.getInventory().getStorageContents()) {
                        if (i != null) {
                            if (CheaksUtils.isSword(i.getType())) {
                                if (! Objects.requireNonNull(i.getItemMeta()).hasCustomModelData()) {
                                    ColorUtils.applyColor(i, color, num, pl);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskLaterAsynchronously(pl, 60);
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
                if (!player.hasMetadata(str) && !player.hasMetadata("canusetrim")) {
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

