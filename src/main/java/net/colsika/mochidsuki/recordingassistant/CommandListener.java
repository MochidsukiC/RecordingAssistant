package net.colsika.mochidsuki.recordingassistant;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("ra")){
            if(args.length == 0){
                sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD +"/ra reload" + ChatColor.RESET + "- 設定ファイルの再読み込み");
                return true;
            }else if (args[0].equals("reload")){
                Config config = new Config(RecordingAssistant.getPlugin());
                config.load();
                sender.sendMessage( ChatColor.AQUA + "設定ファイルを再読み込みします");
                return true;
            }
        }
        return true;
    }
}
