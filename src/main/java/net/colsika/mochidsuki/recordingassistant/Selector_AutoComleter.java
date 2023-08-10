package net.colsika.mochidsuki.recordingassistant;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class Selector_AutoComleter {
    static public List<String> getPlayerList(){
        List<String> players = new ArrayList<>();
        for(Player player : Bukkit.getOnlinePlayers()){
            players.add(player.getName());
        }
        players.addAll(Arrays.asList("@a","@s","@r"));
        return players;
    }

    static public Collection<Player> toPlayers(CommandSender sender, String args){
        Collection<Player> list = new ArrayList<>();
        if(Objects.equals(args, "@a")){
            list.addAll(Bukkit.getOnlinePlayers());
            return list;
        } else if (Objects.equals(args, "@s")) {
            list.add((Player) sender);
            return list;
        } else if (Objects.equals(args, "@r")) {
            int r = new Random().nextInt(Bukkit.getOnlinePlayers().size());
            List<Player> l = new ArrayList(Bukkit.getOnlinePlayers());
            list.add(l.get(r));
            return list;
        }else {
            list.add(Bukkit.getPlayer(args));
            return list;
        }


    }
}
