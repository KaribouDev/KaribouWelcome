package com.KaribouDev.KaribouWelcome;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.KaribouDev.KaribouWelcome.listeners.MonListener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

/**
* Main plugin class. 
* Paper calls onEnable() on startup
* and onDisable() on server shutdown. 
*/
public final class MonPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Message displayed in the console when the plugin starts up
        getLogger().info("Welcomer has been successfully activated !");
        
        saveDefaultConfig(); // Charge/crée config.yml

        // Registering the MonListener.java event listener
        getServer().getPluginManager().registerEvents(new MonListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Welcomer has been deactivated.");
    }

    public void sendRules(Player player) {
    for (String line : getConfig().getStringList("rules")) {
        player.sendMessage(
            LegacyComponentSerializer.legacyAmpersand().deserialize(line)
        );
    }
}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Commande /rule
        if (command.getName().equalsIgnoreCase("rule")) {

            if (sender instanceof Player player) {
                sendRules(player);
            } else {
                sender.sendMessage("This command must be executed by a player.");
            }

            return true;
        }

        // Commande /karibouwelcome
        if (command.getName().equalsIgnoreCase("karibouwelcome")) {

            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {

                if (!sender.hasPermission("karibouwelcome.reload")) {
                    sender.sendMessage(
                        Component.text("You don't have permission.")
                    );
                    return true;
                }

                reloadConfig();

                sender.sendMessage(
                    Component.text("Configuration reloaded.")
                );
                return true;
            }
            return true;
        }
        return false;
    }
}