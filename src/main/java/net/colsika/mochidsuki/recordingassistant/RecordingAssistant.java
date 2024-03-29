package net.colsika.mochidsuki.recordingassistant;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public final class RecordingAssistant extends JavaPlugin {
    private ProtocolManager protocolManager;
    static private Plugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Recording Assistant Pluginが覚醒!!");
        getServer().getPluginManager().registerEvents(new Listener(), this);
        getCommand("ra").setExecutor(new CommandListener()); //ra
        getCommand("ra").setTabCompleter(new TabCompleter());
        getCommand("timer").setExecutor(new CommandListener()); //timer
        getCommand("Timer").setTabCompleter(new TabCompleter());
        protocolManager = ProtocolLibrary.getProtocolManager();

        plugin = this;

        Config config = new Config(this);
        config.load();

        v.bossBar = getServer().createBossBar("RecordingAssistant's Boss Bar", BarColor.YELLOW, BarStyle.SEGMENTED_10);
        v.bossBar.setVisible(false);

        new EveryTicks().runTaskTimer(this,0L,1);
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

