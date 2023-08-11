package net.colsika.mochidsuki.recordingassistant;

import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class v {
    static public HashMap<Player, Location> pin = new HashMap<>();
    static public HashMap<Player, Location> pinRed = new HashMap<>();
    static public BossBar bossBar = RecordingAssistant.getPlugin().getServer().createBossBar("RecordingAssistant's Boss Bar", BarColor.YELLOW, BarStyle.SEGMENTED_10);
    static public HashMap<Player, List<Integer>> timerStamp = new HashMap<>();

}
