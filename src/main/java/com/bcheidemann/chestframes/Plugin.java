package com.bcheidemann.chestframes;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Hello world!
 */
public final class Plugin extends JavaPlugin {

    private Logger logger;

    public Plugin() {
        super();
        logger = getLogger();
    }

    @Override
    public void onEnable() {
        logger.info("ChestFrames enabled.");

        PluginManager pluginManager = getServer().getPluginManager();
		
		pluginManager.registerEvents(new MainEventHandler(logger), this);
    }
    @Override
    public void onDisable() {
        getLogger().info("ChestFrames disabled.");
    }
}
