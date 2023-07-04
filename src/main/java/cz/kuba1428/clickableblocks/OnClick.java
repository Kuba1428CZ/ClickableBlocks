package cz.kuba1428.clickableblocks;

import cz.kuba1428.clickableblocks.essentials.DataManager;
import cz.kuba1428.clickableblocks.essentials.GlobalVars;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class OnClick implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event){
        EquipmentSlot e = event.getHand();
        if (e.equals(EquipmentSlot.HAND)) {

            if (event.getAction().toString().equals("LEFT_CLICK_BLOCK") || event.getAction().toString().equals("RIGHT_CLICK_BLOCK")){
                Location block_location = event.getClickedBlock().getLocation();
                for (String console_action : DataManager.getActions(block_location, "console")) {
                    ConsoleCommandSender console =  GlobalVars.plugin.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, placeholderReplace(console_action, event.getPlayer()));
                }
                for (String player_action : DataManager.getActions(block_location, "player")) {
                    Bukkit.dispatchCommand(event.getPlayer(), placeholderReplace(player_action, event.getPlayer()));
                }
                for (String proxy_action : DataManager.getActions(block_location, "proxy")){
                    Bukkit.dispatchCommand(event.getPlayer(), placeholderReplace("server " + proxy_action, event.getPlayer()));

                }
            }
        }
    }
    public String placeholderReplace(String unresolvedString, Player player){
        String resolvedString = unresolvedString.replace("{player}", player.getDisplayName());
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            return PlaceholderAPI.setPlaceholders(player, resolvedString);
        }
        return resolvedString;
    }
}
