package me.btelnyy.mcstamina.listener;

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
    
    @EventHandler
    public void playerSprintToggle(PlayerToggleSprintEvent event){
        Player p = event.getPlayer();
        if(event.isSprinting()){
            DepleteHunger(p);
        }
        if(!event.isSprinting()){
            RegenHunger(p);
        }
    }
    public void DepleteHunger(Player target){
        Bukkit.getScheduler().runTaskAsynchronously(McStamina.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
                while(target.isSprinting()){
                    try {
                        Thread.sleep(ConfigData.getInstance().depleteRate);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    target.setFoodLevel(target.getFoodLevel() - 1);
                }
                if(!target.isSprinting()){
                    return;
                }
            }
        });
    }
    public void RegenHunger(Player target){
        Bukkit.getScheduler().runTaskAsynchronously(McStamina.getInstance(), new Runnable() {
            @Override
            public void run() 
            {
                try {
                    Thread.sleep(ConfigData.getInstance().cooldownTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while(!target.isSprinting()){
                    try {
                        Thread.sleep(ConfigData.getInstance().depleteRate);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    target.setFoodLevel(target.getFoodLevel() + 1);
                }
                if(target.isSprinting()){
                    return;
                }
            }
        });
    }
}
