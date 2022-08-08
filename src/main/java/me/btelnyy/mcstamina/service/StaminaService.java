package me.btelnyy.mcstamina.service;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.btelnyy.mcstamina.McStamina;
import me.btelnyy.mcstamina.constants.ConfigData;

public class StaminaService implements Runnable{

    public Player owner;

    @Override
    public void run() {
        if(owner.isSprinting()){
            owner.setFoodLevel(owner.getFoodLevel() - 1);
        }else{
            if(owner.getFoodLevel() >= 20){
                owner.setFoodLevel(20);
                return;
            }
            owner.setFoodLevel(owner.getFoodLevel() + 1);
        }
    }
    
    public Integer startService(Player owner){
        this.owner = owner;
        return Bukkit.getScheduler().scheduleSyncRepeatingTask(McStamina.getInstance(), this, 0L, ConfigData.getInstance().depleteRate);
    }
}
