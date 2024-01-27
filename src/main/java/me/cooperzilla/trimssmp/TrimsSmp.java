package me.cooperzilla.trimssmp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class TrimsSmp extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("plugin loaded!!!");

        getServer().getPluginManager().registerEvents(new CoastTrim(), this);
        getServer().getPluginManager().registerEvents(new Wayfinder(), this);
        getServer().getPluginManager().registerEvents(new Ward(), this);
        getServer().getPluginManager().registerEvents(new Raiser(), this);

        getLogger().info("Listeners Registered!!!");

        Bukkit.addRecipe(Smithing.main());

        getLogger().info("Recipes Registered!!!");
    }
}
