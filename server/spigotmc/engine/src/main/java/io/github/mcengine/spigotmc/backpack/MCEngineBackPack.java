package io.github.mcengine.spigotmc.backpack;

import io.github.mcengine.api.backpack.MCEngineBackPackApi;
import io.github.mcengine.common.backpack.command.MCEngineBackPackCommonCommand;
import io.github.mcengine.common.backpack.listener.MCEngineBackPackCommonListener;
import io.github.mcengine.common.backpack.tabcompleter.MCEngineBackPackCommonTabCompleter;

import org.bukkit.plugin.java.JavaPlugin;

public class MCEngineBackPack extends JavaPlugin {
    private MCEngineBackPackUtil backPackUtil;

    @Override
    public void onEnable() {
        backPackUtil = new MCEngineBackPackUtil(this);
        backPackUtil.saveResourceIfNotExists("heads/default.yml");
        getLogger().info("MCEngineBackPack has been enabled.");
        // Register Command
        getCommand("backpack").setExecutor(new MCEngineBackPackCommonCommand(new MCEngineBackPackApi(this)));
        getCommand("backpack").setTabCompleter(new MCEngineBackPackCommonTabCompleter());
        // Register Listener
        getServer().getPluginManager().registerEvents(new MCEngineBackPackCommonListener(this), this);

        MCEngineApi.checkUpdate(this, getLogger(), "github", "MCEngine", "backpack-original", getConfig().getString("github.token", "null"));
    }

    @Override
    public void onDisable() {
        getLogger().info("MCEngineBackPack has been disabled.");
    }
}
