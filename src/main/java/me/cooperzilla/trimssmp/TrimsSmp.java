package me.cooperzilla.trimssmp;

import me.cooperzilla.trimssmp.crafting.Smithing;
import me.cooperzilla.trimssmp.trims.*;
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
        getServer().getPluginManager().registerEvents(new Rib(), this);
        getServer().getPluginManager().registerEvents(new Wild(), this);
        getServer().getPluginManager().registerEvents(new Spire(), this);
        getServer().getPluginManager().registerEvents(new Eye(), this);
        getServer().getPluginManager().registerEvents(new Sentry(), this);

        getLogger().info("Listeners Registered!!!");

        Bukkit.addRecipe(Smithing.main());

        getLogger().info("Recipes Registered!!!");
    }
}
