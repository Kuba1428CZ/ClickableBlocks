package cz.kuba1428.clickableblocks;

import cz.kuba1428.clickableblocks.essentials.DataManager;
import cz.kuba1428.clickableblocks.essentials.GlobalVars;
import cz.kuba1428.clickableblocks.essentials.LangManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ClickableBlocks extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        LangManager.LoadLang();
        DataManager.LoadData();
        GlobalVars.logger.info("Plugin enabled");
        this.getCommand("clickable-blocks").setTabCompleter(new AdminCommandCompleter());
        this.getCommand("clickable-blocks").setExecutor(new AdminCommand());
        getServer().getPluginManager().registerEvents(new OnClick(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
