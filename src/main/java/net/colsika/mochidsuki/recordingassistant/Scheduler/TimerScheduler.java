package net.colsika.mochidsuki.recordingassistant.Scheduler;

import net.colsika.mochidsuki.recordingassistant.BukkitRunnableList;
import net.colsika.mochidsuki.recordingassistant.Clock;
import net.colsika.mochidsuki.recordingassistant.RecordingAssistant;
import net.colsika.mochidsuki.recordingassistant.v;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TimerScheduler extends BukkitRunnable {
    Clock.DisplayType displayType;
    int time;
    double fullTime;
    String title;
    Sound sound;
    Location location;
    public TimerScheduler(@Nonnull Clock.DisplayType displayType, int time, @Nullable String tittle, @Nullable Sound sound, @Nullable Location location){
        this.displayType = displayType;
        this.time = time;
        this.title = tittle;
        this.sound = sound;
        this.location = location;
        fullTime = time;
    }
    @Override
    public void run() {
        if(time > 0){
            if(BukkitRunnableList.timerEnable) {
                time = time - 1;
            }
            switch (displayType){
                case BOSSBAR:
                    for (Player player : RecordingAssistant.getPlugin().getServer().getOnlinePlayers()) {
                        v.bossBar.addPlayer(player);
                    }
                    v.bossBar.setVisible(true);
                    v.bossBar.setTitle("残り" + time/60 + ":" + time%60);
                    v.bossBar.setProgress(time / fullTime);
                    break;
                case ACTIONBAR:
                    for (Player player : RecordingAssistant.getPlugin().getServer().getOnlinePlayers()) {
                        TextComponent component = new TextComponent();
                        component.setText("残り" + time/60 + ":" + time%60);
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,component);
                    }
                    break;
                case CHAT:
                    if(time > 60){
                        if(time % 60 == 0){
                            for (Player player : RecordingAssistant.getPlugin().getServer().getOnlinePlayers()) {
                            player.sendMessage("残り" + time/60 + ":" + time%60);
                            }
                        }
                    }else if (time == 30){
                        for (Player player : RecordingAssistant.getPlugin().getServer().getOnlinePlayers()) {
                            player.sendMessage("たぶん残り30秒!!!");
                        }
                    }else if (time == 20){
                        for (Player player : RecordingAssistant.getPlugin().getServer().getOnlinePlayers()) {
                            player.sendMessage("のこりにじゅーびょうー!!");
                        }
                    }else if(time <= 10){
                        if(time <= 5){
                            for (Player player : RecordingAssistant.getPlugin().getServer().getOnlinePlayers()) {
                                player.sendMessage(ChatColor.RED + "" + time%60);
                            }
                        }else {
                            for (Player player : RecordingAssistant.getPlugin().getServer().getOnlinePlayers()) {
                                player.sendMessage(ChatColor.YELLOW + "" + time%60);
                            }
                        }
                    }
                case INVISIBLE:
                    break;
            }
        }else {
            for (Player player : RecordingAssistant.getPlugin().getServer().getOnlinePlayers()) {
                if(title != null){
                    player.sendTitle(title,"",10,70,20);
                }
                if(sound != null){
                    player.playSound(player,sound,1,1);
                }
                if(location != null) {
                    for(Player player1 : RecordingAssistant.getPlugin().getServer().getOnlinePlayers()){
                        player1.teleport(location);
                    }
                }
            }
            Clock.resetTimer();
        }

    }

    public void timeReseter(){
    time = (int) fullTime;
    }

    public int getTime(){
        return time;
    }
}
