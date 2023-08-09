package net.colsika.mochidsuki.recordingassistant;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> autoFillList = new ArrayList<>();
        if(command.getName().equalsIgnoreCase("ra")){
            switch (args.length){
                case 0:
                    autoFillList.add("reload");
                    return autoFillList;
            }
        }
        if(command.getName().equalsIgnoreCase("timer")){
            switch (args.length){
                case 0:
                    autoFillList.addAll(Arrays.asList("help","start","stop","reset"));
                    return autoFillList;
                case 1: {
                    switch (args[0]){
                        case "start":
                            autoFillList.addAll(Arrays.asList("bossbar","actionbar","chat","invisible"));
                            return autoFillList;
                    }
                }
                case 2:
            }
        }


        return null;
    }
}
