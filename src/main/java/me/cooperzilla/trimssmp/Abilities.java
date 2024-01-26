package me.cooperzilla.trimssmp;

import org.bukkit.event.player.PlayerInteractEvent;

public class Abilities {
    static void raiser(PlayerInteractEvent event) {
        event.getPlayer().sendMessage("yo");
    }
}
