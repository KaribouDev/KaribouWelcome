package com.MoustafaKaribou.KaribouWelcome;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.MoustafaKaribou.KaribouWelcome.listeners.MonListener;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

/**
 * Classe principale du plugin.
 * C'est le point d'entrée : Bukkit/Paper appelle onEnable() au démarrage
 * et onDisable() à l'arrêt du serveur.
 */
public final class MonPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Message affiché dans la console au démarrage du plugin
        getLogger().info("Welcomer has been successfully activated !");
        

        // Enregistrement de l'écouteur d'événements (voir MonListener.java)
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
        if (command.getName().equalsIgnoreCase("rule")) {
            if (sender instanceof Player player) {
                sendRules(player);
            } else {
                sender.sendMessage("This command must be executed by a player.");
            }
            return true;
        }
        return false;
    }
}