package cz.kuba1428.clickableblocks.essentials;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataManager {

        static FileConfiguration customConfig;

        static File customConfigFile = new File(GlobalVars.plugin.getDataFolder(), "data.yml");

        public static void LoadData(){

            if (!customConfigFile.exists()) {
                GlobalVars.plugin.saveResource("data.yml", false);
            }

            customConfig = new YamlConfiguration();
            try {
                customConfig.load(customConfigFile);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
        public static void addAction(Location BlockLocation, String ActionType, String action) throws IOException {
                List<String> actionList = customConfig.getStringList(locationToName(BlockLocation) + ".actions." + ActionType);
                actionList.add(action);
                customConfig.set(locationToName(BlockLocation) + ".actions." + ActionType, actionList);
                customConfig.save(customConfigFile);
        }
        public static void deleteAllActions(Location BlockLocation) throws IOException {
            customConfig.set(locationToName(BlockLocation), null);
            customConfig.save(customConfigFile);


        }
        public static List<String> getActions(Location BlockLocation, String ActionType){
            return customConfig.getStringList(locationToName(BlockLocation) + ".actions." + ActionType);

        }

        private static String locationToName(Location location){
            return (location.getX() + "_" + location.getY() + "_" + location.getZ() + "_" + location.getWorld().getName()).replace(".0", "");
        }

}
