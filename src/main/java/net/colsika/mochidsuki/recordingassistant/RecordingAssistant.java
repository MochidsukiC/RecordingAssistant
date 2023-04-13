package net.colsika.mochidsuki.recordingassistant;

import org.bukkit.plugin.java.JavaPlugin;

public final class RecordingAssistant extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Recording Assistant Pluginが覚醒!!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Recording Assistant Pluginが封印された...");
    }
}
