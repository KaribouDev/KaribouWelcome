package com.KaribouDev.KaribouWelcome.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.KaribouDev.KaribouWelcome.KaribouWelcome;
import com.KaribouDev.KaribouWelcome.MessageService;

/**
 * Listens for player join/quit events and sends configured messages.
 */
public class EventListener implements Listener {

    private final KaribouWelcome plugin;

    public EventListener(KaribouWelcome plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        MessageService messages = plugin.getMessageService();

        if (!player.hasPlayedBefore()) {
            // Custom join message for new players
            event.joinMessage(messages.getConfigMessage(player, "public.new-player"));

            // Send welcome and rules messages to new players
            messages.sendConfigMessages(player, "private.welcome");
            messages.sendConfigMessages(player, "private.rules");

        } else {
            // Custom join message for returning players
            event.joinMessage(messages.getConfigMessage(player, "public.login"));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // Custom quit message
        event.quitMessage(plugin.getMessageService().getConfigMessage(player, "public.quit"));
    }
}