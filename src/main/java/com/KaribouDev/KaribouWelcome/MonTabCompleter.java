package com.KaribouDev.KaribouWelcome;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

/**
 * Tab completer for /karibouwelcome and /kbw.
 * Filters suggestions by player permissions and typed prefix.
 */
public final class MonTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(
            CommandSender sender,
            Command command,
            String alias,
            String[] args
    ) {
        if (!command.getName().equalsIgnoreCase("karibouwelcome")) {
            return List.of();
        }

        if (args.length == 1) {
            return completeFirstArgument(sender, args[0]);
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("test")) {
            return completeTestType(sender, args[1]);
        }

        return List.of();
    }

    private List<String> completeFirstArgument(CommandSender sender, String input) {
        String prefix = input.toLowerCase(Locale.ROOT);
        List<String> suggestions = new ArrayList<>();

        if (sender.hasPermission("karibouwelcome.reload")
                && "reload".startsWith(prefix)) {
            suggestions.add("reload");
        }

        if (sender.hasPermission("karibouwelcome.test")
                && "test".startsWith(prefix)) {
            suggestions.add("test");
        }

        return suggestions;
    }

    private List<String> completeTestType(CommandSender sender, String input) {
        if (!sender.hasPermission("karibouwelcome.test")) {
            return List.of();
        }

        String prefix = input.toLowerCase(Locale.ROOT);
        List<String> suggestions = new ArrayList<>();
        List<String> testTypes = List.of("join", "quit", "newplayer", "welcome", "rules");

        for (String testType : testTypes) {
            if (testType.startsWith(prefix)) {
                suggestions.add(testType);
            }
        }

        return suggestions;
    }
}