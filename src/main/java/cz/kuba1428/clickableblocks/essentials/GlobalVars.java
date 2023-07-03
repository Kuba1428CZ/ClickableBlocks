package cz.kuba1428.clickableblocks.essentials;

import cz.kuba1428.clickableblocks.ClickableBlocks;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

public interface GlobalVars {
    Plugin plugin = ClickableBlocks.getPlugin(ClickableBlocks.class);
    Logger logger = plugin.getLogger();
    FileConfiguration config = plugin.getConfig();

}
