package net.colsika.mochidsuki.recordingassistant;

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
            sender.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD +"/timer" + ChatColor.RESET + "- タイマーコマンド" + "\n" +
                    "使い方はこちらのWikiを参照してください。" + "\n" +
                    ChatColor.BLUE+ChatColor.UNDERLINE+"https://github.com/MochidsukiC/RecordingAssistant/wiki/コマンド#タイマーを開始する");
            } else if (args[0].equalsIgnoreCase("start")){
                String args1 = args[1].toUpperCase();
                if(args1.equalsIgnoreCase("BOSSBAR") || args1.equalsIgnoreCase("ACTIONBAR") || args1.equalsIgnoreCase("CHAT") || args1.equalsIgnoreCase("INVISIBLE")) {
                    Clock.DisplayType displayType = Clock.DisplayType.valueOf(args1);
                    if(args[2].chars().allMatch(Character::isDigit)){
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
                                } catch (Exception e) {
                                }

                                try {
                                    Player player = (Player) sender;
                                    world = player.getWorld();
                                } catch (Exception e) {
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
                        new Clock().startTimer(displayType,Integer.parseInt(args[2]),title,sound,location);

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
            } else if (args[0].equalsIgnoreCase("stop")) {

            } else if (args[0].equalsIgnoreCase("reset")) {

            }
        }
        return true;
    }
}
