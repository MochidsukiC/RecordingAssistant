package net.colsika.mochidsuki.recordingassistant;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Clock {
    public void startTimer(@Nonnull DisplayType displayType,@Nonnull int time, @Nullable String tittle, @Nullable Sound sound){
        for (Player player : RecordingAssistant.getPlugin().getServer().getOnlinePlayers()) {
            v.bossBar.addPlayer(player);
        }
        v.bossBar.setVisible(true);
        switch (displayType){
            case BOSS_BAR:
                break;
            case ACTION_BAR:
                break;
            case INVISIBLE:
                break;
        }
    }

    public enum DisplayType {
        BOSS_BAR,
        ACTION_BAR,
        INVISIBLE
    }
}
