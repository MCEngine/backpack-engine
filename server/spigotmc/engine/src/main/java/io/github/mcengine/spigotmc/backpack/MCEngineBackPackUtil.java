package io.github.mcengine.spigotmc.backpack;

import java.io.File;
import org.bukkit.plugin.Plugin;

public class MCEngineBackPackUtil {

    private Pluing plugin;

    public MCEngineBackPackUtil(Plugin plugin) {
        this.plugin = plugin;
    }

    public void saveResourceIfNotExists(String resourcePath) {
        File resourceFile = new File(plugin.getDataFolder(), resourcePath);
        if (!resourceFile.exists()) {
            plugin.saveResource(resourcePath, false); // The 'false' prevents overwriting
            plugin.getLogger().info("Default resource '" + resourcePath + "' has been saved.");
        } else {
            plugin.getLogger().info("Resource '" + resourcePath + "' already exists. Skipping save.");
        }
    }
}
