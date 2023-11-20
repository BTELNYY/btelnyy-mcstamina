package me.btelnyy.mcstamina.constants;

import me.btelnyy.mcstamina.service.file_manager.Configuration;

public class ConfigData {
    private static ConfigData instance;

    public int depleteRate = 10;
    public Boolean pluginEnabled = true;
    public int staminaDepleteAmount = 1;
    public int staminaRegainAmount = 1;

    public void load(Configuration config) {
        pluginEnabled = config.getBoolean("plugin_enabled");
        depleteRate = config.getInt("stamina_deplete_rate");
        staminaDepleteAmount = config.getInt("stamina_deplete_amount");
        staminaRegainAmount = config.getInt("stamina_regen_amount");
        instance = this;
    }
    public static ConfigData getInstance(){
        return instance;
    }
}
