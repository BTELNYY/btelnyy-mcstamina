package me.btelnyy.mcstamina.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;

import me.btelnyy.mcstamina.McStamina;
import me.btelnyy.mcstamina.constants.ConfigData;
/*
import me.btelnyy.mcstamina.service.file_manager.Configuration;
import me.btelnyy.mcstamina.service.file_manager.FileID;
*/


public class EventListener implements Listener {
    //private static final Configuration language = McStamina.getInstance().getFileManager().getFile(FileID.LANGUAGE).getConfiguration();
    
    static List<Player> sprintingPlayers = new ArrayList<Player>();

    @EventHandler
    public void playerSprintToggle(PlayerToggleSprintEvent event){
        Player p = event.getPlayer();
        if(event.isSprinting()){
            McStamina.getInstance().log(Level.INFO, "Added player " + p.getName() + " to sprinting list.");
            sprintingPlayers.add(p);
            return;
        }
        if(!event.isSprinting()){
            McStamina.getInstance().log(Level.INFO, "removed player " + p.getName() + " from sprinting list.");
            sprintingPlayers.remove(p);
            return;
        }
    }

    public static int hungerThread(){
         return Bukkit.getScheduler().scheduleSyncRepeatingTask(McStamina.getInstance(), new Runnable() {
            @Override
            public void run() {
                if(!ConfigData.getInstance().pluginEnabled){
                    return;
                }
                for(Player player : Bukkit.getOnlinePlayers() ){
                    if(sprintingPlayers.contains(player)){
                        player.setFoodLevel(player.getFoodLevel() - 1);
                    }else{
                        player.setFoodLevel(player.getFoodLevel() + 1);
                    }
                }
            }
        }, 0L, ConfigData.getInstance().depleteRate);
    }
}

