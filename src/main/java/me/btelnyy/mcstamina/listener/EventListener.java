package me.btelnyy.mcstamina.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;

import me.btelnyy.mcstamina.constants.Globals;
import me.btelnyy.mcstamina.service.StaminaService;


public class EventListener implements Listener {

    static List<Player> sprintingPlayers = new ArrayList<Player>();

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event){
        StaminaService service = new StaminaService();
        Globals.hungerThreads.put(event.getPlayer(), service.startService(event.getPlayer()));
    }

    @EventHandler
    public void playerLeaveEvent(PlayerKickEvent event){
        Bukkit.getScheduler().cancelTask(Globals.hungerThreads.get(event.getPlayer()));
        Globals.hungerThreads.remove(event.getPlayer());
    }
}

