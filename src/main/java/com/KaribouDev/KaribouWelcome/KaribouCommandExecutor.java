package com.KaribouDev.KaribouWelcome;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.kyori.adventure.text.Component;

/**
 * Handles the /karibouwelcome (and /kbw) command.
 * Sub-commands: reload, test <type>
 */
public final class KaribouCommandExecutor implements CommandExecutor {

    private final KaribouWelcome plugin;

    public KaribouCommandExecutor(KaribouWelcome plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MessageService messages = plugin.getMessageService();

        // No arguments → show usage
        if (args.length == 0) {
            sender.sendMessage(Component.text("Usage: /karibouwelcome <reload|test>"));
            return true;
        }

        // /karibouwelcome reload
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("karibouwelcome.reload")) {
                sender.sendMessage(messages.getErrorMessage("errors.no-permission"));
                return true;
            }

            plugin.reloadConfig();
            sender.sendMessage(messages.getErrorMessage("errors.config-reloaded"));
            return true;
        }

        // /karibouwelcome test <type>
        if (args.length == 2 && args[0].equalsIgnoreCase("test")) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage(messages.getErrorMessage("errors.player-only"));
                return true;
            }

            if (!player.hasPermission("karibouwelcome.test")) {
                player.sendMessage(messages.getErrorMessage("errors.no-permission"));
                return true;
            }

            return handleTest(player, args[1].toLowerCase());
        }

        // Unknown sub-command
        sender.sendMessage(Component.text("Usage: /karibouwelcome <reload|test <join|quit|newplayer|welcome|rules>>"));
        return true;
    }

    /**
     * Handles the /karibouwelcome test <type> sub-command.
     * @return true always (command was handled)
     */
    private boolean handleTest(Player player, String type) {
        MessageService messages = plugin.getMessageService();

        switch (type) {
            case "join" -> player.sendMessage(messages.getConfigMessage(player, "public.login"));
            case "quit" -> player.sendMessage(messages.getConfigMessage(player, "public.quit"));
            case "newplayer" -> player.sendMessage(messages.getConfigMessage(player, "public.new-player"));
            case "welcome" -> messages.sendConfigMessages(player, "private.welcome");
            case "rules" -> messages.sendConfigMessages(player, "private.rules");
            default -> player.sendMessage(Component.text(
                "Usage: /karibouwelcome test <join|quit|newplayer|welcome|rules>"
            ));
        }

        return true;
    }
}
