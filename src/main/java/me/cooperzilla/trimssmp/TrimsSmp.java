package me.cooperzilla.trimssmp;

import org.bukkit.plugin.java.JavaPlugin;

public final class TrimsSmp extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("plugin loaded !!!");
        getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getCommand("debug").setExecutor(new DebugCmd());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
