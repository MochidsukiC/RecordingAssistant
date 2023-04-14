package net.colsika.mochidsuki.recordingassistant;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;


public class EveryTicks extends BukkitRunnable {
    @Override
    public void run() {

        Player[] players = Bukkit.getServer().getOnlinePlayers().toArray((new Player[0]));
        for(Player player : players){

            //Pin
            Team team = player.getScoreboard().getEntryTeam(player.getName());
            if(team != null){
                Location[] location = new Location[team.getSize()];
                int i = 0;
                for(String playerName : team.getEntries()){

                    Player teammate = RecordingAssistant.getPlugin().getServer().getPlayer(playerName);
                    location[i] = v.pin.get(teammate);
                    i++;
                }
                Protocol protocol = new Protocol();
                protocol.pushPin(player,location);
            }else {

            }
        }
    }
}
