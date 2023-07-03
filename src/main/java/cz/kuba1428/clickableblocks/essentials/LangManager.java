package cz.kuba1428.clickableblocks.essentials;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import net.md_5.bungee.api.chat.TextComponent;
import java.io.File;
import java.io.IOException;

public class LangManager {
    static FileConfiguration customConfig;

    public static void LoadLang(){
        File customConfigFile;
        customConfigFile = new File(GlobalVars.plugin.getDataFolder(), "lang.yml");
        if (!customConfigFile.exists()) {
            GlobalVars.plugin.saveResource("lang.yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    public static TextComponent getMessage(String key){
        String text;
        if (!customConfig.isSet(key)){
            text = key;
        }else{
            text = customConfig.getString(key);
        }
        TextComponent textcomponent = new TextComponent();
        textcomponent.setText(ChatColor.translateAlternateColorCodes('&', text));
        return textcomponent;

    }
}
