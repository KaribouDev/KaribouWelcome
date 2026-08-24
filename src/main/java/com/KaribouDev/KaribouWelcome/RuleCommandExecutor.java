package com.KaribouDev.KaribouWelcome;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Handles the /rules command.
 * Permission: karibouwelcome.rules (enforced here + plugin.yml as defense-in-depth).
 */
public final class RuleCommandExecutor implements CommandExecutor {

    private final KaribouWelcome plugin;

    public RuleCommandExecutor(KaribouWelcome plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            plugin.getMessageService().sendConfigMessages(player, "private.rules");
        } else {
            // Console can also receive rules — send as plain text
            for (String line : plugin.getConfig().getStringList("private.rules")) {
                if (line == null || line.isEmpty()) continue;
                // Strip MiniMessage tags for console readability
                sender.sendMessage(line.replaceAll("<[^>]+>", ""));
            }
        }
        return true;
    }
}
