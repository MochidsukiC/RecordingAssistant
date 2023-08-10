package net.colsika.mochidsuki.recordingassistant;

import net.colsika.mochidsuki.recordingassistant.Scheduler.TimerScheduler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Clock {
     static public void startTimer(@Nonnull DisplayType displayType, int time, @Nullable String tittle, @Nullable Sound sound, @Nullable Location location){
        try {
            BukkitRunnableList.timer.cancel();
            v.bossBar.setVisible(false);
        }catch (Exception e){
            e.printStackTrace();
        }
        BukkitRunnableList.timer.cancel();
        BukkitRunnableList.timerEnable = true;
        BukkitRunnable runnable = new TimerScheduler(displayType,time,tittle,sound,location);
        runnable.runTaskTimer(RecordingAssistant.getPlugin(),0,1);
        BukkitRunnableList.timer = runnable;
    }
    static public void stopTimer(){
        BukkitRunnableList.timerEnable = false;
    }
    static public void resetTimer(){
        v.bossBar.setVisible(false);
        BukkitRunnableList.timerEnable = false;
        BukkitRunnableList.timer.cancel();
        for (Player player : Bukkit.getOnlinePlayers()){
            List<Integer> l = v.timerStamp.get(player);
            player.sendMessage(ChatColor.GREEN + "タイムスタンプ表");
            for(int i = 0;i < l.size();i++ ){
                String message = "|" + ChatColor.AQUA + String.format("%2d",i) + ChatColor.RESET + " | " + ChatColor.YELLOW + String.format("%3d",l.get(i)/60) + ":" + String.format("%02d",l.get(i)%60) + ChatColor.RESET + " |";
                player.sendMessage(message);
            }
        }
        Bukkit.getServer().getLogger().info(ChatColor.GREEN + "タイムスタンプ表");
        int max = 0;
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        for(int i = 0;i < players.size();i++){
            max = Math.max(max,v.timerStamp.get(players.get(i)).size());
        }

        StringBuilder s = new StringBuilder("|");
        for(Player player : players){
            s.append(player.getName()).append("|");
        }
        Bukkit.getServer().getLogger().info(s.toString());
        for(int i = 0; i < max; i++){
            String string = "|" + String.format("%2d",i+1) + "|";
            for(Player player : players){
                List<Integer> l = v.timerStamp.get(player);
                string = string + String.format("%" + player.getName().length() + "d",String.format("%3d",l.get(i)/60) + ":" + String.format("%02d",l.get(i)%60) + "|");
            }
            Bukkit.getServer().getLogger().info(string);
        }
     }
    static public void restartTimer(){
        ((TimerScheduler)BukkitRunnableList.timer).timeReseter();
    }

    static public void stampTimer(Player player){
        List<Integer> l = v.timerStamp.get(player);
        l.add(((TimerScheduler)BukkitRunnableList.timer).getTime());
        v.timerStamp.put(player,l);
    }

    public enum DisplayType {
        BOSSBAR,
        ACTION_BAR,
        CHAT,
        INVISIBLE
    }
}
