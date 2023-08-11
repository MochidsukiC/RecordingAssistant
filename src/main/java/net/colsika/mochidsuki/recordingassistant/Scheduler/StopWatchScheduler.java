package net.colsika.mochidsuki.recordingassistant.Scheduler;

import net.colsika.mochidsuki.recordingassistant.BukkitRunnableList;
import net.colsika.mochidsuki.recordingassistant.Clock;
import net.colsika.mochidsuki.recordingassistant.RecordingAssistant;
import net.colsika.mochidsuki.recordingassistant.v;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class StopWatchScheduler extends BukkitRunnable {
    int time;
    Clock.DisplayType type;
    Player player;
    boolean enable;
    public StopWatchScheduler(Player player , Clock.DisplayType type){
        this.time = 0;
        this.type = type;
        this.player = player;
    }
    @Override
    public void run() {
        if(enable) {
            time++;
        }
        switch (type){
            case BOSSBAR:
                v.bossBar.addPlayer(player);

                if(enable){
                    v.bossBar.setColor(BarColor.GREEN);
                }else {
                    v.bossBar.setColor(BarColor.RED);
                }
                v.bossBar.setVisible(true);
                v.bossBar.setTitle(time/60 + ":" + time%60);
                v.bossBar.setProgress((double) (time % 60) / 60);
                break;
            case ACTIONBAR:
                    TextComponent component = new TextComponent();
                    ChatColor color;
                    if(enable){
                        color = ChatColor.WHITE;
                    }else {
                        color = ChatColor.GOLD;
                    }
                    component.setText(color +""+ time/60 + ":" + time%60);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR,component);
                break;
            case CHAT:
                if(time/60 < 5) {
                    if (time % 60 == 0) {
                        player.sendMessage(time / 60 + ":00");
                    }
                }else {
                    if (time % 300 == 0) {
                        player.sendMessage(time / 60 + ":00");
                    }
                }
            case INVISIBLE:
                break;
        }

    }
    public void setEnable(boolean enable){
        this.enable = enable;
    }
    public int getTime(){
        return time;
    }
}
