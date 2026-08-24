package com.KaribouDev.KaribouWelcome;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

/**
 * Tab completer for /karibouwelcome and /kbw.
 * Filters suggestions by player permissions and typed prefix.
 */
public class MonTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {
        List<String> completions = new ArrayList<>();

        if (!command.getName().equalsIgnoreCase("karibouwelcome")) {
            return completions;
        }

        // /karibouwelcome _
        if (args.length == 1) {
            String typed = args[0].toLowerCase();

            if (sender.hasPermission("karibouwelcome.reload") && "reload".startsWith(typed)) {
                completions.add("reload");
            }
            if (sender.hasPermission("karibouwelcome.test") && "test".startsWith(typed)) {
                completions.add("test");
            }
        }

        // /karibouwelcome test _
        if (args.length == 2 && args[0].equalsIgnoreCase("test")) {
            if (sender.hasPermission("karibouwelcome.test")) {
                String typed = args[1].toLowerCase();
                List<String> testTypes = List.of("join", "quit", "newplayer", "welcome", "rules");
                for (String type : testTypes) {
                    if (type.startsWith(typed)) {
                        completions.add(type);
                    }
                }
            }
        }

        return completions;
    }
}