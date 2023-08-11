package net.colsika.mochidsuki.recordingassistant;

import net.colsika.mochidsuki.recordingassistant.Scheduler.StopWatchScheduler;
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
        BukkitRunnableList.timerEnable = true;
        BukkitRunnable runnable = new TimerScheduler(displayType,time,tittle,sound,location);
        runnable.runTaskTimer(RecordingAssistant.getPlugin(),0,20);
        BukkitRunnableList.timer = runnable;
    }
    static public void stopTimer(){
        BukkitRunnableList.timerEnable = false;
    }
    static public void resetTimer(){
        v.bossBar.setVisible(false);
        BukkitRunnableList.timerEnable = false;
        BukkitRunnableList.timer.cancel();

        sendTimeStamp();
        v.timerStamp.clear();
     }
    static public boolean restartTimer(){
         if(BukkitRunnableList.timerEnable) {
             ((TimerScheduler) BukkitRunnableList.timer).timeReseter();

             sendTimeStamp();
             v.timerStamp.clear();

             return true;
         }else {
             BukkitRunnableList.timerEnable = true;
             return false;
         }
    }

    static public void stampTimer(Player player){
        int i =((TimerScheduler)BukkitRunnableList.timer).getTime();
        List<Integer> list;
        if(v.timerStamp.containsKey(player)){
            list = new ArrayList<>(v.timerStamp.get(player));
            list.add(i);
        }else {
            list = new ArrayList<>(List.of(i));
        }
        v.timerStamp.put(player,list);
    }

    public static void sendTimeStamp(){
        for (Player player : Bukkit.getOnlinePlayers()){
            if(v.timerStamp.containsKey(player)){
                List<Integer> l = new ArrayList<>();
                try {
                    l = v.timerStamp.get(player);
                } catch (Exception ignored) {
                }
                player.sendMessage(ChatColor.GREEN + "タイムスタンプ表");
                for (int i = 0; i < l.size(); i++) {
                    String message = "|" + ChatColor.AQUA + String.format("%2d", i) + ChatColor.RESET + " | " + ChatColor.YELLOW + String.format("%3d", l.get(i) / 60) + ":" + String.format("%02d", l.get(i) % 60) + ChatColor.RESET + " |";
                    player.sendMessage(message);
                }
            }
        }
        Bukkit.getServer().getLogger().info(ChatColor.GREEN + "タイムスタンプ表");
        int max = 0;
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        for (Player value : players) {
            if(v.timerStamp.containsKey(value)) {
                max = Math.max(max, v.timerStamp.get(value).size());
            }
        }

        StringBuilder s = new StringBuilder("|No.|");
        for(Player player : players){
            s.append(player.getName()).append("|");
        }
        Bukkit.getServer().getLogger().info(s.toString());
        for(int i = 0; i < max; i++){
            StringBuilder string = new StringBuilder("|" + String.format("%3d", i + 1) + "|");
            for(Player player : players){
                List<Integer> l = v.timerStamp.get(player);
                if(!v.timerStamp.containsKey(player) || i < l.size()) {
                    string.append(String.format("%" + player.getName().length() + "s", String.format("%3d", l.get(i) / 60) + ":" + String.format("%02d", l.get(i) % 60))).append("|");
                }else{
                    string.append(String.format("%" + player.getName().length() + "s", "N/A")).append("|");
                }
            }
            Bukkit.getServer().getLogger().info(string.toString());
        }
    }

    static public void startStopWatch(Player player,DisplayType type){
        if(BukkitRunnableList.stopWatch.containsKey(player)){
            BukkitRunnableList.stopWatch.get(player).cancel();
        }

        StopWatchScheduler runnable = new StopWatchScheduler(player,type);
        runnable.setEnable(true);
        runnable.runTaskTimer(RecordingAssistant.getPlugin(),0,20);
        BukkitRunnableList.stopWatch.put(player,runnable);
    }

    static public void stopStopWatch(Player player){
        ((StopWatchScheduler)BukkitRunnableList.stopWatch.get(player)).setEnable(false);
    }
    static public void restartStopWatch(Player player){
        ((StopWatchScheduler)BukkitRunnableList.stopWatch.get(player)).setEnable(true);
    }
    static public void rapStopWatch(Player player){
        int i =((TimerScheduler)BukkitRunnableList.stopWatch.get(player)).getTime();
        List<Integer> list;
        if(v.stopWatchRap.containsKey(player)){
            list = new ArrayList<>(v.stopWatchRap.get(player));
            list.add(i);
        }else {
            list = new ArrayList<>(List.of(i));
        }
        v.stopWatchRap.put(player,list);
    }
    static public int resetStopWatch(Player player){
         int time = ((StopWatchScheduler)BukkitRunnableList.stopWatch.get(player)).getTime();
         BukkitRunnableList.stopWatch.get(player).cancel();
         return time;
    }

    static public void sendRap(Player player){
         if(v.stopWatchRap.containsKey(player)){
             List<Integer> l = new ArrayList<>();
             try {
                 l = v.stopWatchRap.get(player);
             } catch (Exception ignored) {
             }
             player.sendMessage(ChatColor.GREEN + "ラップ表");
             for (int i = 0; i < l.size(); i++) {
                 String message = "|" + ChatColor.AQUA + String.format("%2d", i) + ChatColor.RESET + " | " + ChatColor.YELLOW + String.format("%3d", l.get(i) / 60) + ":" + String.format("%02d", l.get(i) % 60) + ChatColor.RESET + " |";
                 player.sendMessage(message);
             }
         }
    }

    public enum DisplayType {
        BOSSBAR,
        ACTIONBAR,
        CHAT,
        INVISIBLE
    }
}
