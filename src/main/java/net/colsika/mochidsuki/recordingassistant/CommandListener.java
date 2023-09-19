package net.colsika.mochidsuki.recordingassistant;

import net.colsika.mochidsuki.recordingassistant.Scheduler.TimerScheduler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class CommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command,@Nonnull String label,@Nonnull String[] args) {
        if(command.getName().equalsIgnoreCase("ra")){
            if(args.length == 0){
                sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD +"/ra reload" + ChatColor.RESET + "- 設定ファイルの再読み込み");
                return true;
            }else if (args[0].equalsIgnoreCase("reload")) {
                Config config = new Config(RecordingAssistant.getPlugin());
                config.load();
                sender.sendMessage(ChatColor.AQUA + "設定ファイルを再読み込みします");
                return true;
            }
        }
        if(command.getName().equalsIgnoreCase("timer")){
            if(args.length == 0 || args[0].equals("help")){
            sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD +"/timer" + ChatColor.RESET + "- タイマーコマンド" + "\n" +
                    "使い方はこちらのWikiを参照してください。" + "\n" +
                    ChatColor.BLUE+ChatColor.UNDERLINE+"https://github.com/MochidsukiC/RecordingAssistant/wiki/コマンド#タイマーを開始する");
            } else if (args[0].equalsIgnoreCase("start")){
                if(args.length >= 2 && (args[1].equalsIgnoreCase("BOSSBAR") || args[1].equalsIgnoreCase("ACTIONBAR") || args[1].equalsIgnoreCase("CHAT") || args[1].equalsIgnoreCase("INVISIBLE"))) {
                    String args1 = args[1].toUpperCase();
                    Clock.DisplayType displayType = Clock.DisplayType.valueOf(args1);
                    if(args.length >= 3 && args[2].chars().allMatch(Character::isDigit)){
                        String title = null;
                        Sound sound = null;
                        Location location = null;
                        if(args.length > 3 && !args[3].equalsIgnoreCase("null")){
                            title = args[3];
                        }
                        if(args.length > 4 && !args[4].equalsIgnoreCase("null")){
                            sound = Sound.valueOf(args[4]);
                        }
                        if(args.length > 5 && !args[5].equalsIgnoreCase("null")){
                            try {
                                World world = sender.getServer().getWorld("world");
                                try {
                                    Block b = (Block) sender;
                                    world = b.getWorld();
                                } catch (Exception ignored) {
                                }

                                try {
                                    Player player = (Player) sender;
                                    world = player.getWorld();
                                } catch (Exception ignored) {
                                }
                                location = new Location(world, Double.parseDouble(args[5]), Double.parseDouble(args[6]), Double.parseDouble(args[7]));
                            }catch (Exception e){
                                sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD +"/timer" + ChatColor.RESET + "- タイマーコマンド" + "\n" +
                                    ChatColor.GREEN + "<TP先>" + ChatColor.RESET +"が正しく指定されていません。" + "\n" +
                                    "詳細はこちらのWikiを参照してください。" + "\n" +
                                    ChatColor.BLUE+ChatColor.UNDERLINE+"https://github.com/MochidsukiC/RecordingAssistant/wiki/コマンド#タイマーを開始する");
                                return true;
                            }
                        }
                        Clock.startTimer(displayType,Integer.parseInt(args[2]),title,sound,location);

                    }else {
                        sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD +"/timer" + ChatColor.RESET + "- タイマーコマンド" + "\n" +
                            ChatColor.BLUE + "[計測時間]" + ChatColor.RESET +"が正しく指定されていません。" + "\n" +
                            "詳細はこちらのWikiを参照してください。" + "\n" +
                            ChatColor.BLUE+ChatColor.UNDERLINE+"https://github.com/MochidsukiC/RecordingAssistant/wiki/コマンド#タイマーを開始する");
                        return true;
                    }
                }else {
                    sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD +"/timer" + ChatColor.RESET + "- タイマーコマンド" + "\n" +
                            ChatColor.GREEN + "[表示場所]" + ChatColor.RESET +"が正しく指定されていません。" + "\n" +
                            "詳細はこちらのWikiを参照してください。" + "\n" +
                            ChatColor.BLUE+ChatColor.UNDERLINE+"https://github.com/MochidsukiC/RecordingAssistant/wiki/コマンド#タイマーを開始する");
                    return true;
                }
            } else if (args[0].equalsIgnoreCase("stamp")) {
                if(args.length>1) {
                    for (Player player : Selector_AutoComleter.toPlayers(sender, args[1])) {
                        Clock.stampTimer(player);
                    }
                }else {
                    try {
                        Clock.stampTimer((Player) sender);
                    }catch (Exception e){
                        return false;
                    }
                }
            } else if (args[0].equalsIgnoreCase("stop")) {
                Clock.stopTimer();
                sender.sendMessage(ChatColor.GOLD + "タイマーを一時停止しました");
            } else if (args[0].equalsIgnoreCase("restart")) {
                boolean b = Clock.restartTimer();
                if(b){
                    sender.sendMessage(ChatColor.AQUA + "タイマーを再スタートします");
                }else {
                    sender.sendMessage(ChatColor.GREEN + "タイマーを再開します");
                }
            } else if (args[0].equalsIgnoreCase("reset")) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "タイマーを終了しました");
                Clock.resetTimer();
            }
        }
        if(command.getName().equalsIgnoreCase("stopwatch")){
            if(args.length > 0){
                switch (args[0]){
                    case "start": {
                        if (args.length > 1 && (args[1].equalsIgnoreCase("BOSSBAR") || args[1].equalsIgnoreCase("ACTIONBAR") || args[1].equalsIgnoreCase("CHAT") || args[1].equalsIgnoreCase("INVISIBLE"))) {
                            if(args.length>2){
                                for(Player player : Selector_AutoComleter.toPlayers(sender,args[2])){
                                    String args1 = args[1].toUpperCase();
                                    Clock.DisplayType displayType = Clock.DisplayType.valueOf(args1);
                                    Clock.startStopWatch(player,displayType);
                                    sender.sendMessage(ChatColor.GREEN + "ストップウォッチを開始しました");
                                }
                            }else {
                                try {
                                    String args1 = args[1].toUpperCase();
                                    Clock.DisplayType displayType = Clock.DisplayType.valueOf(args1);
                                    Clock.startStopWatch((Player) sender, displayType);
                                    sender.sendMessage(ChatColor.GREEN + "ストップウォッチを開始しました");
                                }catch (Exception e){
                                    sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD +"/stopwatch" + ChatColor.RESET + "- ストップウォッチコマンド" + "\n" +
                                            ChatColor.BLUE + "[セレクター]" + ChatColor.RESET +"が正しく指定されていません。" + "\n" +
                                            "詳細はこちらのWikiを参照してください。" + "\n" +
                                            ChatColor.BLUE+ChatColor.UNDERLINE+"https://github.com/MochidsukiC/RecordingAssistant/wiki/コマンド#ストップウォッチを開始する");

                                }
                            }
                        }else {
                            sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD +"/stopwatch" + ChatColor.RESET + "- ストップウォッチコマンド" + "\n" +
                                    ChatColor.BLUE + "[表示場所]" + ChatColor.RESET +"が正しく指定されていません。" + "\n" +
                                    "詳細はこちらのWikiを参照してください。" + "\n" +
                                    ChatColor.BLUE+ChatColor.UNDERLINE+"https://github.com/MochidsukiC/RecordingAssistant/wiki/コマンド#ストップウォッチを開始する");
                        }
                    }
                        return true;
                    case "stop":
                        return true;
                    case "rap":
                        return true;
                    case "reset":
                        return true;
                    case "get":
                        return true;
                }
            }
        }
        if(command.getName().equalsIgnoreCase("randomTeam")){
            sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD +"/randomTeam" + ChatColor.RESET + "- チーム自動振り分けコマンド" + "\n" +
                    ChatColor.BLUE + "[表示場所]" + ChatColor.RESET +"が正しく指定されていません。" + "\n" +
                    "詳細はこちらのWikiを参照してください。" + "\n" +
                    ChatColor.BLUE+ChatColor.UNDERLINE+"https://github.com/MochidsukiC/RecordingAssistant/wiki/コマンド#ストップウォッチを開始する");
        }
        return true;
    }
}
