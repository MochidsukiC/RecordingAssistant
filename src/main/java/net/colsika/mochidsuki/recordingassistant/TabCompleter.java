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
            if(args.length == 0) {
                autoFillList.addAll(Arrays.asList("help", "start","stamp", "stop","restart", "reset"));
                return autoFillList;
            }else {
                switch (args[0]) {
                    case "start": {
                        switch (args.length) {
                            case 1:
                                autoFillList.addAll(Arrays.asList("bossbar", "actionbar", "chat", "invisible"));
                                return autoFillList;
                            case 2:
                                autoFillList.set(0,"[TIME(second)]");
                                return autoFillList;
                            case 3:
                                autoFillList.set(0,"<TITLE>");
                                autoFillList.set(1,"null");
                                return autoFillList;
                            case 4:
                                autoFillList.set(0,"<SOUND_ID>");
                                autoFillList.set(1,"null");
                                return autoFillList;
                            case 5:
                                autoFillList.set(0,"<TP_X>");
                                return autoFillList;
                            case 6:
                                autoFillList.set(0,"<TP_Y>");
                                return autoFillList;
                            case 7:
                                autoFillList.set(0,"<TP_Z>");
                                return autoFillList;

                        }
                    }
                    case "stamp":{
                        return Selector_AutoComleter.getPlayerList();
                    }
                    case "stop":{

                    }
                    case "restart":{

                    }
                    case "reset":{

                    }
                }
            }
        }
        return null;
    }
}
