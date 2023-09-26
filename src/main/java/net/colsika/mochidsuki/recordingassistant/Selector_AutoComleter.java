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
            if(args.charAt(2) == '['){
                if(args.toLowerCase().contains("team=")){
                    String arg = sortArgs(args,"team");
                    if(Bukkit.getServer().getScoreboardManager() != null && Bukkit.getServer().getScoreboardManager().getMainScoreboard().getTeam(arg) != null) {
                        list.addAll((Collection<? extends Player>) Bukkit.getServer().getScoreboardManager().getMainScoreboard().getTeam(arg).getPlayers());
                    }
                }
                if(args.toLowerCase().contains("tag=")){
                    String arg = sortArgs(args,"tag");
                    for(Player player : Bukkit.getOnlinePlayers()){
                        if(player.getScoreboardTags().contains(arg)){
                            list.add(player);
                        }
                    }
                }
            }
            return list;
        } else if (Objects.equals(args, "@s") || args == null) {
            list.add((Player) sender);
            return list;
        } else if (Objects.equals(args, "@r")) {
            int r = new Random().nextInt(Bukkit.getOnlinePlayers().size());
            List<Player> l = new ArrayList<>(Bukkit.getOnlinePlayers());
            list.add(l.get(r));
            return list;
        } else {
            list.add(Bukkit.getPlayer(args));
            return list;
        }


    }
    static public String sortArgs(String args,String argumentCode){
        int top = args.indexOf(argumentCode) + argumentCode.length()+1;
        StringBuilder sb =  new StringBuilder(args);
        sb.delete(0,top);
        args = sb.toString();
        int end = args.indexOf(",");
        sb.delete(end,args.length());
        args = sb.toString();
        return args;
    }
}
