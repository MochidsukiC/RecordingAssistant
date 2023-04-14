package net.colsika.mochidsuki.recordingassistant;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("ra")){
            if(args.length == 0){
                return false;
            }else if (args[0] == "reload"){
                Config config = new Config(RecordingAssistant.getPlugin());
                config.load();
                return true;
            }
        }
        return false;
    }
}
