package net.colsika.mochidsuki.recordingassistant;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event){
        if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK){
            if(event.getItem() != null) {
                if(Config.enablePin && event.getItem().getType() == Config.pinItem){

                }
            }
        }
    }
}
