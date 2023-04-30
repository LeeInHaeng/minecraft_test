package topi.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class HelloWorld extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("플러그인이 활성회 되었습니다!");
        getCommand("test").setExecutor(new Command());
    }

    @Override
    public void onDisable() {
        getLogger().info("플러그인이 비활성화 되었습니다!");
    }
}
