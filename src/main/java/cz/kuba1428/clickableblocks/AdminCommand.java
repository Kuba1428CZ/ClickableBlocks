package cz.kuba1428.clickableblocks;

import cz.kuba1428.clickableblocks.essentials.DataManager;
import cz.kuba1428.clickableblocks.essentials.LangManager;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;


public class AdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        switch (args[0]) {
            case "add":
                if (commandSender instanceof Player) {
                    if (args[1].equals("proxy") || args[1].equals("console") || args[1].equals("player")) {
                        Block block = ((Player) commandSender).getTargetBlock(null, 5);
                        String action = "";
                        int num = 0;
                        for (String arg :  args) {
                            if(num < 2){
                                num++;
                            }else {
                                action += arg + " ";
                            }
                        }
                        if (!block.getType().equals(Material.AIR)) {
                            try {
                                DataManager.addAction(block.getLocation() ,args[1], action);
                                commandSender.sendMessage(LangManager.getMessage("message.success.create." + args[1]));
                            } catch (IOException e) {
                                commandSender.sendMessage(LangManager.getMessage("error.general.undefined"));
                                throw new RuntimeException(e);
                            }
                        } else {
                            commandSender.sendMessage(LangManager.getMessage("message.error.create.air"));
                        }
                    } else {
                        commandSender.sendMessage(LangManager.getMessage("message.error.create.wrong_action_type"));
                    }
                } else {
                    commandSender.sendMessage(LangManager.getMessage("message.error.general.not_player"));
                }

                break;
            case "delete":
                if (commandSender instanceof Player){
                    Block block = ((Player) commandSender).getTargetBlock(null, 5);
                    if(!block.getType().equals(Material.AIR)){
                        try {
                            DataManager.deleteAllActions(block.getLocation());
                            commandSender.sendMessage(LangManager.getMessage("message.success.delete"));
                        } catch (IOException e) {
                            commandSender.sendMessage(LangManager.getMessage("message.error.general.undefined"));
                            throw new RuntimeException(e);
                        }
                    }else{
                        commandSender.sendMessage(LangManager.getMessage("message.create.air"));
                    }
                }else{
                    commandSender.sendMessage(LangManager.getMessage("message.error.general.not_player"));
                }

                break;
            case "info":

                break;
            case "reload":

                break;
            default:

                break;
        }
        return true;

    }
}
