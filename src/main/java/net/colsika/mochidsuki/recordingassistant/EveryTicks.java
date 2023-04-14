package net.colsika.mochidsuki.recordingassistant;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import java.util.Objects;
import java.util.Optional;

public class EveryTicks extends BukkitRunnable {
    @Override
    public void run() {

        Player[] players = Bukkit.getServer().getOnlinePlayers().toArray((new Player[0]));
        for(Player player : players){

            //Pin

            Team playerteam = player.getScoreboard().getPlayerTeam(player);
            if(playerteam != null) {
                String[] tp = new String[Objects.requireNonNull(playerteam).getEntries().size()];
                playerteam.getEntries().toArray(tp);
                Player[] teamplayer = new Player[tp.length + 3];
                for (int i = 0; i < tp.length; i++) {
                    teamplayer[i] = Bukkit.getPlayer(tp[i]);
                }

                Protocol pin = new Protocol();

                Optional<Location>[] loc = new Optional[teamplayer.length];

                for (int i = 0; i < teamplayer.length;i++){
                    loc[i] = Optional.ofNullable(v.pin.get(teamplayer[i]));
                }

                Location[] location = new Location[teamplayer.length];
                boolean[] booleans = new boolean[teamplayer.length];

                for (int i = 0; i < loc.length; i++) {
                    location[i] = loc[i].orElse(new Location(player.getWorld(), player.getLocation().getX(), -80, player.getLocation().getZ()));
                    booleans[i] = !(v.pin.get(teamplayer[i]) == null);
                }



                pin.pushPin(player, location, booleans, EntityType.DRAGON_FIREBALL,0);

                Optional<Location>[] locR = new Optional[teamplayer.length];

                for (int i = 0; i < teamplayer.length;i++){
                    locR[i] = Optional.ofNullable(v.pinRed.get(teamplayer[i]));
                }

                Location[] locationR = new Location[teamplayer.length];
                boolean[] booleansR = new boolean[teamplayer.length];

                for (int i = 0; i < locR.length; i++) {
                    locationR[i] = locR[i].orElse(new Location(player.getWorld(), player.getLocation().getX(), -80, player.getLocation().getZ()));
                    booleansR[i] = !(v.pinRed.get(teamplayer[i]) == null);
                }
                pin.pushPin(player,locationR,booleansR,EntityType.FIREBALL, teamplayer.length);


                for(int i = 0; i < tp.length;i++){
                    if(teamplayer[i] != null && teamplayer[i] != player){
                        pin.setGlowing(teamplayer[i],player);
                    }
                }




            }
        }
    }
}
