package cz.kuba1428.clickableblocks;

import org.bukkit.command.Command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class AdminCommandCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1){
            completions.add("add");
            completions.add("delete");
//            completions.add("reload");
//            completions.add("info");
        }
        if (args.length == 2){
            if (args[0].equals("add")){
                completions.add("player");
                completions.add("console");
                completions.add("proxy");
            }
        }
        if (args.length >= 3){
            if (args[0].equals("add")){
                if (args[1].equals("proxy")){
                    completions.add("<server>");
                }else {
                    completions.add("<command>");
                }
            }
        }
        return completions;

    }
}
