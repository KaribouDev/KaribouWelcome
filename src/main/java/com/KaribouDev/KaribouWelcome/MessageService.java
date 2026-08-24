package com.KaribouDev.KaribouWelcome;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

/**
 * Centralizes all config message logic with proper error handling.
 * Replaces the inline methods that were in KaribouWelcome.
 */
public final class MessageService {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    private final KaribouWelcome plugin;

    public MessageService(KaribouWelcome plugin) {
        this.plugin = plugin;
    }

    /**
     * Sends a list of MiniMessage lines from config to a player.
     * Silently skips empty/missing entries and logs warnings for invalid tags.
     */
    public void sendConfigMessages(Player player, String path) {
        FileConfiguration config = plugin.getConfig();
        if (!config.contains(path)) {
            plugin.getLogger().warning("Config path '" + path + "' does not exist. Check your config.yml.");
            return;
        }

        for (String line : config.getStringList(path)) {
            if (line == null || line.isEmpty()) {
                continue;
            }

            line = line.replace("%player%", player.getName());

            try {
                player.sendMessage(MINI_MESSAGE.deserialize(line));
            } catch (Exception e) {
                plugin.getLogger().warning("Invalid MiniMessage in config at '" + path + "': " + line);
                plugin.getLogger().warning("Error: " + e.getMessage());
                // Send fallback plain text so the player still sees something
                player.sendMessage(Component.text(line));
            }
        }
    }

    /**
     * Returns a single MiniMessage Component from config, with variable substitution.
     * Returns an error component if the path is missing or the tag is invalid.
     */
    public Component getConfigMessage(Player player, String path) {
        FileConfiguration config = plugin.getConfig();
        String line = config.getString(path, "");

        if (line.isEmpty()) {
            plugin.getLogger().warning("Config path '" + path + "' is empty or missing.");
            return Component.text("[Missing message: " + path + "]");
        }

        line = line.replace("%player%", player.getName());

        try {
            return MINI_MESSAGE.deserialize(line);
        } catch (Exception e) {
            plugin.getLogger().warning("Invalid MiniMessage in config at '" + path + "': " + line);
            plugin.getLogger().warning("Error: " + e.getMessage());
            return Component.text(line);
        }
    }

    /**
     * Returns a plain-text error message from config.
     * Used for system messages (no permission, player only, etc.).
     */
    public Component getErrorMessage(String path) {
        FileConfiguration config = plugin.getConfig();
        String line = config.getString(path, "");

        if (line.isEmpty()) {
            // Hardcoded fallbacks — should never be reached if config is correct
            return switch (path) {
                case "errors.no-permission" -> Component.text("You don't have permission.");
                case "errors.player-only" -> Component.text("This command must be executed by a player.");
                case "errors.config-reloaded" -> Component.text("Configuration reloaded.");
                default -> Component.text("[Missing error message: " + path + "]");
            };
        }

        try {
            return MINI_MESSAGE.deserialize(line);
        } catch (Exception e) {
            return Component.text(line);
        }
    }

    /**
     * Convenience: send a config error/system message directly to a player.
     */
    public void sendErrorMessage(Player player, String path) {
        player.sendMessage(getErrorMessage(path));
    }
}
