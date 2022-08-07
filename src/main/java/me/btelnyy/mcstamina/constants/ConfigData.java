package me.btelnyy.mcstamina.constants;

import me.btelnyy.mcstamina.service.file_manager.Configuration;

public class ConfigData {
    private static ConfigData instance;

    public int depleteRate = 10;
    public Boolean pluginEnabled = true;

    public void load(Configuration config) {
        pluginEnabled = config.getBoolean("plugin_enabled");
        depleteRate = config.getInt("stamina_deplete_rate");
        instance = this;
    }
    public static ConfigData getInstance(){
        return instance;
    }
}
