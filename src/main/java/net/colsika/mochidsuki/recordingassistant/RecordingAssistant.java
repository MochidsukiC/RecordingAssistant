package net.colsika.mochidsuki.recordingassistant;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class RecordingAssistant extends JavaPlugin {
    private ProtocolManager protocolManager;
    static private Plugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Recording Assistant Pluginが覚醒!!");
        getServer().getPluginManager().registerEvents(new Listener(), this);

        protocolManager = ProtocolLibrary.getProtocolManager();

        plugin = this;

        Config config = new Config(this);
        config.load();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Recording Assistant Pluginが封印された...");
    }

    static public Plugin getPlugin(){
        return plugin;
    }
}

class v{
    static HashMap<Player, Location> pin = new HashMap<>();
    static HashMap<Player, Location> pinRed = new HashMap<>();
}
