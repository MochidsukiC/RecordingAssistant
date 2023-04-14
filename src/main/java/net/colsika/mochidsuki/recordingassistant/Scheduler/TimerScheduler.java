package net.colsika.mochidsuki.recordingassistant.Scheduler;

import net.colsika.mochidsuki.recordingassistant.Clock;
import net.colsika.mochidsuki.recordingassistant.RecordingAssistant;
import net.colsika.mochidsuki.recordingassistant.v;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TimerScheduler extends BukkitRunnable {
    Clock.DisplayType displayType;
    int time;
    String title;
    Sound sound;
    public TimerScheduler(@Nonnull Clock.DisplayType displayType, int time, @Nullable String tittle, @Nullable Sound sound){
        this.displayType = displayType;
        this.time = time;
        this.title = tittle;
        this.sound = sound;
    }
    @Override
    public void run() {
        if(time > 0){
            time = time -1;
            switch (displayType){
                case BOSS_BAR:
                    for (Player player : RecordingAssistant.getPlugin().getServer().getOnlinePlayers()) {
                        v.bossBar.addPlayer(player);
                    }
                    v.bossBar.setVisible(true);
                    break;
                case ACTION_BAR:
                    break;
                case INVISIBLE:
                    break;
            }
        }else {
            cancel();
        }

    }
}
