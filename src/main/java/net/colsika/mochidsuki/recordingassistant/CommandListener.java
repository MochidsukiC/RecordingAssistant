package net.colsika.mochidsuki.recordingassistant;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command,@Nonnull String label,@Nonnull String[] args) {
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
        if(command.getName().equalsIgnoreCase("timer")){
            if(args.length == 0 || args[0].equals("help")){
            sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD +"/timer" + ChatColor.RESET + "- タイマーコマンド");
            String message = "タイマースタート" + "\n" +
                    ChatColor.AQUA+ChatColor.BOLD+ "/timer start" + ChatColor.RESET+ ChatColor.GREEN + "[表示場所]" + ChatColor.BLUE + " [時間]"+ ChatColor.GREEN +" <タイムアップした時に表示するタイトル> <タイムアップした時に流すサウンド> <タイムアップした時にテレポートする座標>" + "\n" +
                    ChatColor.GREEN+ChatColor.BOLD+ "  [表示場所] - " + ChatColor.RESET + "タイマーの残り時間を表示するは所を指定します。" + "\n"+
                    "               表示場所の種類" + "\n"+
                    ChatColor.GREEN+ChatColor.BOLD + "                 bossbar" + ChatColor.RESET + "- ボスバーに時間を表示します" + "\n"+
                    ChatColor.GREEN+ChatColor.BOLD + "               actionbar" + ChatColor.RESET + "- アクションバーに時間を表示します" + "\n"+
                    ChatColor.GREEN+ChatColor.BOLD + "                    chat" + ChatColor.RESET + "- 残り時間を定期的にチャットでお知らせします" + "\n"+
                    ChatColor.GREEN+ChatColor.BOLD + "               invisible" + ChatColor.RESET + "- 残り時間を表示しません" + "\n"+
                    ChatColor.BLUE+ChatColor.BOLD+ "  [時間] - " + ChatColor.RESET + "タイマーで計測する時間を秒で指定します。" + "\n"+
                    ChatColor.GREEN+ChatColor.BOLD+ "  <タイムアップした時に表示するタイトル> - " + ChatColor.RESET + "タイマーで計測する時間を秒で指定します。";
            sender.sendMessage(message);
            }
        }
        return true;
    }
}
