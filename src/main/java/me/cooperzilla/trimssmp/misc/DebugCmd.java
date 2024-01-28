package me.cooperzilla.trimssmp.misc;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DebugCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player;
        if (sender instanceof Player) {
            player = (Player) sender;
        } else {
            return true;
        }

        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        item.getItemMeta().setCustomModelData(Integer.valueOf(args[0]));

        player.getInventory().addItem(item);

        return true;
    }
}
