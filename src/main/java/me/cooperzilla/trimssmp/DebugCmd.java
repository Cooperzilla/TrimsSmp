package me.cooperzilla.trimssmp;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DebugCmd implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ReadWriteNBT nbt = NBT.itemStackToNBT(item);
        nbt.setString("TrimPower", args[0]);
        player.getInventory().addItem(item);
        return true;
    }


}
