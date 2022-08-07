package me.btelnyy.mcstamina.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;

import me.btelnyy.mcstamina.McStamina;
import me.btelnyy.mcstamina.constants.ConfigData;
import me.btelnyy.mcstamina.service.Utils;
import me.btelnyy.mcstamina.service.file_manager.Configuration;
import me.btelnyy.mcstamina.service.file_manager.FileID;


public class EventListener implements Listener {
    private static final Configuration language = McStamina.getInstance().getFileManager().getFile(FileID.LANGUAGE).getConfiguration();
    
    static List<Player> sprintingPlayers = new ArrayList<Player>();

    @EventHandler
    public void playerSprintToggle(PlayerToggleSprintEvent event){
        Player p = event.getPlayer();
        if(event.isSprinting()){
            sprintingPlayers.add(p);
            return;
        }
        if(!event.isSprinting()){
            if(p.getFoodLevel() == 4){
                Utils.sendActionBarMessage(p, Utils.colored(language.getString("out_of_stamina")));
            }
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

