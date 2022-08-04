package me.btelnyy.mcstamina.constants;

import me.btelnyy.mcstamina.service.file_manager.Configuration;

public class ConfigData {
    private static ConfigData instance;

    public int maxRunTime = 10;
    public int cooldownTime = 1;
    public int depleteRate = 10;
    public Boolean pluginEnabled = true;

    public void load(Configuration config) {
        maxRunTime = config.getInt("player_max_run_time");
        cooldownTime = config.getInt("player_cooldown_time");
        pluginEnabled = config.getBoolean("plugin_enabled");
        depleteRate = config.getInt("stamina_deplete_rate");
        instance = this;
    }
    public static ConfigData getInstance(){
        return instance;
    }
}
