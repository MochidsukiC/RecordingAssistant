package net.colsika.mochidsuki.recordingassistant;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scoreboard.Team;


public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (Config.enablePin && event.getItem().getType() == Config.pinItem) {
                    if (event.getPlayer().getTargetBlockExact(400) != null) {
                        v.pin.put(event.getPlayer(), event.getPlayer().getTargetBlockExact(400).getLocation());

                        Team playerTeam = event.getPlayer().getScoreboard().getEntryTeam(event.getPlayer().getName());
                        for (String entry : playerTeam.getEntries()) {
                            if (event.getPlayer().getServer().getOnlinePlayers().contains(Bukkit.getPlayer(entry))) {
                                Player teammate = Bukkit.getPlayer(entry);
                                teammate.playSound(teammate.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 100, 0);
                            }
                        }

                    } else {
                        v.pin.remove(event.getPlayer());
                        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 0.5F, 1);
                    }
                }
            }
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (Config.enablePin && event.getItem().getType() == Config.pinItem) {
                if (event.getPlayer().getTargetBlockExact(400) != null) {
                    v.pinRed.put(event.getPlayer(), event.getPlayer().getTargetBlockExact(400).getLocation());

                    Team playerTeam = event.getPlayer().getScoreboard().getEntryTeam(event.getPlayer().getName());
                    for (String entry : playerTeam.getEntries()) {
                        if (event.getPlayer().getServer().getOnlinePlayers().contains(Bukkit.getPlayer(entry))) {
                            Player teammate = Bukkit.getPlayer(entry);
                            teammate.playSound(teammate.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 50, 0);
                            teammate.playSound(teammate.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 50, 0.3F);
                            teammate.playSound(teammate.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 50, 0.6F);
                            teammate.playSound(teammate.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 50, 1);
                        }
                    }

                } else {
                    v.pinRed.remove(event.getPlayer());
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 0.5F, 1);
                }
            }
        }
    }
}
