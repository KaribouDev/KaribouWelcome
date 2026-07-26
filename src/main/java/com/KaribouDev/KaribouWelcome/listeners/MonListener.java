package com.KaribouDev.KaribouWelcome.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.KaribouDev.KaribouWelcome.MonPlugin;

public class MonListener implements Listener {

    private final MonPlugin plugin;

    public MonListener(MonPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) {
            // Custom join message for new players
            event.joinMessage(plugin.getConfigMessage(player, "public.new-player"));

            // Send welcome and rules messages to new players
            plugin.sendConfigMessages(player, "private.welcome");
            plugin.sendConfigMessages(player, "private.rules");

        } else {
            // Custom join message for returning players
            event.joinMessage(plugin.getConfigMessage(player, "public.login"));
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        
        // Custom quit message
        event.quitMessage(plugin.getConfigMessage(player, "public.quit"));
    }
}