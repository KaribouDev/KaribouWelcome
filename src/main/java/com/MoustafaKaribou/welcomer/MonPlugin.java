package com.MoustafaKaribou.welcomer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.MoustafaKaribou.welcomer.listeners.MonListener;

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
        getServer().getPluginManager().registerEvents(new MonListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Welcomer has been deactivated.");
    }

    /**
     * Gère l'exécution des commandes déclarées dans plugin.yml.
     * Ici on gère la commande /bonjour.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("hello")) {
            if (sender instanceof Player player) {
                player.sendMessage("§aWelcome " + player.getName() + " !.");
            } else {
                sender.sendMessage("This command must be executed by a player.");
            }
            return true;
        }
        return false;
    }
}