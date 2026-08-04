package com.KaribouDev.KaribouWelcome;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.KaribouDev.KaribouWelcome.listeners.EventListener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

/**
* Main plugin class. 
* Paper calls onEnable() on startup
* and onDisable() on server shutdown. 
*/
public final class karibouWelcome extends JavaPlugin {

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public void onEnable() {
        // Message displayed in the console when the plugin starts up
        getLogger().info("KaribouWelcome has been successfully activated.");
        
        saveDefaultConfig(); // Load/create config.yml

        // Registering the MonListener.java event listener
        getServer().getPluginManager().registerEvents(new EventListener(this), this);

        // Registering the command executor and tab completer for /karibouwelcome
        getCommand("karibouwelcome").setTabCompleter(new MonTabCompleter());
    }

    @Override
    public void onDisable() {
        getLogger().info("KaribouWelcome has been deactivated.");
    }

    public void sendConfigMessages(Player player, String path) {
        for (String line : getConfig().getStringList(path)) {
            line = line.replace("%player%", player.getName());

            player.sendMessage(
                miniMessage.deserialize(line)
            );
        }
    }

    public Component getConfigMessage(Player player, String path) {
        String line = getConfig().getString(path, "");
        line = line.replace("%player%", player.getName());

        return miniMessage.deserialize(line);
    }

    private void sendPublicMessage(String path, Player player) {
        player.sendMessage(getConfigMessage(player, path));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Commande /rule
        if (command.getName().equalsIgnoreCase("rules")) {

            if (sender instanceof Player player) {
                sendConfigMessages(player, "private.rules");
            } else {
                sender.sendMessage("This command must be executed by a player.");
            }

            return true;
        }

        // Commande /karibouwelcome
        if (command.getName().equalsIgnoreCase("karibouwelcome")) {

            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {

                if (!sender.hasPermission("karibouwelcome.reload")) {
                    sender.sendMessage(Component.text("You don't have permission."));
                    return true;
                }

                reloadConfig();
                sender.sendMessage(Component.text("Configuration reloaded."));
                return true;
            }

            if (args.length == 2 && args[0].equalsIgnoreCase("test")) {

                if (!(sender instanceof Player player)) {
                    sender.sendMessage("This command must be executed by a player.");
                    return true;
                }

                if (!player.hasPermission("karibouwelcome.test")) {
                    player.sendMessage(Component.text("You don't have permission."));
                    return true;
                }

                switch (args[1].toLowerCase()) {
                    case "join" -> sendPublicMessage("public.login", player);
                    case "quit" -> sendPublicMessage("public.quit", player);
                    case "newplayer" -> sendPublicMessage("public.new-player", player);
                    case "welcome" -> sendConfigMessages(player, "private.welcome");
                    case "rules" -> sendConfigMessages(player, "private.rules");
                    default -> player.sendMessage(Component.text(
                            "Usage: /karibouwelcome test <join|quit|newplayer|welcome|rules>"
                    ));
                }

                return true;
            }

            return true;
        }
        return false;
    }
}