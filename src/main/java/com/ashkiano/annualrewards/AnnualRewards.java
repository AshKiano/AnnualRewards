package com.ashkiano.annualrewards;

import org.bukkit.plugin.java.JavaPlugin;

public final class AnnualRewards extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Metrics metrics = new Metrics(this, 21096);
        getCommand("annual").setExecutor(new AnnualCommandExecutor(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
