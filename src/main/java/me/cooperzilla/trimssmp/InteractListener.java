package me.cooperzilla.trimssmp;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener implements Listener {
    @EventHandler
    void onPlayerInteract(PlayerInteractEvent event) {

        Action action = event.getAction();
        if (
                action == Action.LEFT_CLICK_AIR ||
                action == Action.LEFT_CLICK_BLOCK
        ) {
            return;
        }

        event.getPlayer().sendMessage("ttttt");

        ItemStack item = event.getItem();
        ReadWriteNBT nbt = NBT.itemStackToNBT(item);
        String str = nbt.getString("TrimPower");

        switch (str) {
            case "raiser": {
                Abilities.raiser(event);
            }
        }
    }
}
