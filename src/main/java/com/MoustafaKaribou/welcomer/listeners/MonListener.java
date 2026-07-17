package com.MoustafaKaribou.welcomer.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Exemple d'écouteur d'événements.
 * Ici, on affiche un message personnalisé quand un joueur rejoint le serveur.
 * Tu peux ajouter d'autres méthodes @EventHandler pour écouter d'autres événements
 * (PlayerQuitEvent, BlockBreakEvent, PlayerDeathEvent, etc.)
 */
public class MonListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.joinMessage(
            Component.text("Welcome " + event.getPlayer().getName() + " !")
                .color(net.kyori.adventure.text.format.NamedTextColor.GREEN)
        );
    }
}