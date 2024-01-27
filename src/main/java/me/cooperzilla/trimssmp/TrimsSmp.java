package me.cooperzilla.trimssmp;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;

public class TrimsSmp extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("plugin loaded !!!");
        getServer().getPluginManager().registerEvents(new CoastTrim(), this);
        //getServer().getPluginManager().registerEvents(this, this);
        //getServer().getPluginManager().registerEvents(this, this);
        //getServer().getPluginManager().registerEvents(this, this);

    }
}
