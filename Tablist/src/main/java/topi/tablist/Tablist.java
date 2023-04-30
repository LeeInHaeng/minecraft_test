package topi.tablist;

import org.bukkit.plugin.java.JavaPlugin;

public final class Tablist extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("name").setExecutor(new Command());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
