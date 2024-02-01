package me.cooperzilla.trimssmp.misc;

import me.cooperzilla.trimssmp.utils.CheaksUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.SmithItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public class LimitCheaks implements Listener {

    private final JavaPlugin pl;

    public LimitCheaks(JavaPlugin pl) {
        this.pl = pl;
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void limitCraft(SmithItemEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (!CheaksUtils.isSword(event.getInventory().getStorageContents()[1].getType())) {
            return;
        }

        if (!player.hasMetadata("trimedSwordsLimit")) {
            player.setMetadata("trimedSwordsLimit", new FixedMetadataValue(pl, 1));
            player.setMetadata("trimedSwords", new FixedMetadataValue(pl, 0));
        }

        if (
                player.getMetadata("trimedSwords").get(0) ==
                        player.getMetadata("trimedSwordsLimit").get(0)
        ) {
            event.setCancelled(true);
        }

        if (!event.isCancelled()) {

            player.setMetadata("trimedSwords", new FixedMetadataValue(
                    pl, player.getMetadata("trimedSwords").get(0).asInt() + 1)
            );


        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (!player.hasMetadata("trimedSwordsLimit")) {
            player.setMetadata("trimedSwordsLimit", new FixedMetadataValue(pl, 1));
            player.setMetadata("trimedSwords", new FixedMetadataValue(pl, 0));
        }

        if (player.getMetadata("trimedSwords").get(0).asInt() > 0) {
            player.setMetadata("trimedSwords", new FixedMetadataValue(pl, 0));
        }
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {
        Player player;

        if (event.getEntity() instanceof Player) {
            player = (Player) event.getEntity();
        } else {
            return;
        }

        ItemStack item = event.getItem().getItemStack();
        ItemMeta meta = item.getItemMeta();

        if (meta.hasCustomModelData()) {

            if (
                    player.getMetadata("trimedSwords").get(0) ==
                            player.getMetadata("trimedSwordsLimit").get(0)
            ) {
                event.setCancelled(true);
            }

            if (!player.hasMetadata("trimedSwordsLimit")) {
                player.setMetadata("trimedSwordsLimit", new FixedMetadataValue(pl, 1));
                player.setMetadata("trimedSwords", new FixedMetadataValue(pl, 0));
            }

            player.setMetadata("trimedSwords", new FixedMetadataValue(
                    pl, player.getMetadata("trimedSwords").get(0).asInt() + 1)
            );
        }

    }
}
