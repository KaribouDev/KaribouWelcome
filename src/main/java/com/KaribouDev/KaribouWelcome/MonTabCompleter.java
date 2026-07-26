package com.KaribouDev.KaribouWelcome;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class MonTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {

        List<String> completions = new ArrayList<>();

        if (command.getName().equalsIgnoreCase("karibouwelcome")) {

            // /karibouwelcome _
            if (args.length == 1) {
                completions.add("reload");
                completions.add("test");
            }

            // /karibouwelcome test _
            if (args.length == 2 && args[0].equalsIgnoreCase("test")) {
                completions.add("join");
                completions.add("quit");
                completions.add("newplayer");
                completions.add("welcome");
                completions.add("rules");
            }
        }

        return completions;
    }
}