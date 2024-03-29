package me.cooperzilla.trimssmp;

import me.cooperzilla.trimssmp.crafting.Smithing;
import me.cooperzilla.trimssmp.misc.DeathListener;
import me.cooperzilla.trimssmp.misc.DebugCmd;
import me.cooperzilla.trimssmp.trims.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class TrimsSmp extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("plugin loaded!!!");

        getServer().getPluginManager().registerEvents(new Coast(this), this);
        getServer().getPluginManager().registerEvents(new Wayfinder(this), this);
        getServer().getPluginManager().registerEvents(new Ward(this), this);
        getServer().getPluginManager().registerEvents(new Raiser(this), this);
        getServer().getPluginManager().registerEvents(new Rib(this), this);
        getServer().getPluginManager().registerEvents(new Wild(this), this);
        getServer().getPluginManager().registerEvents(new Spire(this), this);
        getServer().getPluginManager().registerEvents(new Eye(this), this);
        getServer().getPluginManager().registerEvents(new Sentry(this), this);
        getServer().getPluginManager().registerEvents(new Dune(this), this);
        getServer().getPluginManager().registerEvents(new Snout(this), this);
        getServer().getPluginManager().registerEvents(new Shaper(this), this);
        getServer().getPluginManager().registerEvents(new Host(this), this);
        getServer().getPluginManager().registerEvents(new Tide(this), this);
        getServer().getPluginManager().registerEvents(new Tide(this), this);

        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        //getServer().getPluginManager().registerEvents(new LimitCheaks(this), this);

        getLogger().info("Listeners Registered!!!");


        Bukkit.addRecipe(Smithing.dia());
        //Bukkit.addRecipe(Smithing.neth());
        //Bukkit.addRecipe(Smithing.gold());
        //Bukkit.addRecipe(Smithing.iron());

        getLogger().info("Recipes Registered!!!");


        Objects.requireNonNull(getServer().getPluginCommand("debug")).setExecutor(new DebugCmd());

        getLogger().info("Commands Registered!!!");

    }
}
