package com.hectorhw.iplusplus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Pose;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.UUID;

public class StickListener implements Listener {
    HashMap<UUID, Integer> storage;
    public StickListener(HashMap<UUID, Integer> db){
        this.storage = db;
    }

    @EventHandler
    public void StickRightClickHandler(PlayerInteractEvent e){
        if(e.getItem()!=null && e.getItem().getType()== Material.STICK){
            if(e.getAction()== Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK){
                if(e.getPlayer().getPose()== Pose.SNEAKING){
                    storage.put(e.getPlayer().getUniqueId(), 0);
                    e.getPlayer().sendMessage(ChatColor.GREEN+"i=0");
                }else if(e.getPlayer().getPose()==Pose.STANDING){
                    int i = storage.getOrDefault(e.getPlayer().getUniqueId(), 0);
                    i++;
                    storage.put(e.getPlayer().getUniqueId(), i);
                    e.getPlayer().sendMessage(ChatColor.GREEN+"i++"+"(i is now "+i+")");
                }
            }
        }
    }

    @EventHandler
    public void StickLeftClickEvent(PlayerInteractEvent e){
        if(e.getItem()!=null && e.getItem().getType()== Material.STICK){
            if(e.getAction()== Action.LEFT_CLICK_AIR||e.getAction()== Action.LEFT_CLICK_BLOCK){
                int v = storage.getOrDefault(e.getPlayer().getUniqueId(), 0);
                e.getPlayer().sendMessage(ChatColor.GRAY+"i -> "+ v);
            }
        }

    }

}
