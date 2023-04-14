package net.colsika.mochidsuki.recordingassistant;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class RecordingAssistant extends JavaPlugin {
    private ProtocolManager protocolManager;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Recording Assistant Pluginが覚醒!!");
        getServer().getPluginManager().registerEvents(new Listener(), this);

        protocolManager = ProtocolLibrary.getProtocolManager();




        Config config = new Config(this);
        config.load();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Recording Assistant Pluginが封印された...");
    }
}

class v{
    static HashMap<Player, Location> pin = new HashMap<>();
    static HashMap<Player, Location> pinRed = new HashMap<>();
}
