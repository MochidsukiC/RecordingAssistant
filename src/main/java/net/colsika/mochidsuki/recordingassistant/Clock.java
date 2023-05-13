package net.colsika.mochidsuki.recordingassistant;

import net.colsika.mochidsuki.recordingassistant.Scheduler.TimerScheduler;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Clock {
    public void startTimer(@Nonnull DisplayType displayType, int time, @Nullable String tittle, @Nullable Sound sound, @Nullable Location location){
        try {
            BukkitRunnableList.timer.cancel();
            v.bossBar.setVisible(false);
        }catch (Exception e){
            e.printStackTrace();
        }
        BukkitRunnableList.timer.cancel();
        BukkitRunnable runnable = new TimerScheduler(displayType,time,tittle,sound,location);
        runnable.runTaskTimer(RecordingAssistant.getPlugin(),0,1);
        BukkitRunnableList.timer = runnable;
    }

    public enum DisplayType {
        BOSSBAR,
        ACTION_BAR,
        CHAT,
        INVISIBLE
    }
}
