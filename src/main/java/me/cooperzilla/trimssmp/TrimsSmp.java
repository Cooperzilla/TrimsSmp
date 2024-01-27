package me.cooperzilla.trimssmp;

import me.cooperzilla.trimssmp.crafting.Smithing;
import me.cooperzilla.trimssmp.trims.Coast;
import me.cooperzilla.trimssmp.trims.Raiser;
import me.cooperzilla.trimssmp.trims.Ward;
import me.cooperzilla.trimssmp.trims.Wayfinder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class TrimsSmp extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("plugin loaded!!!");

        getServer().getPluginManager().registerEvents(new Coast(), this);
        getServer().getPluginManager().registerEvents(new Wayfinder(), this);
        getServer().getPluginManager().registerEvents(new Ward(), this);
        getServer().getPluginManager().registerEvents(new Raiser(), this);

        getLogger().info("Listeners Registered!!!");

        Bukkit.addRecipe(Smithing.main());

        getLogger().info("Recipes Registered!!!");
    }
}
