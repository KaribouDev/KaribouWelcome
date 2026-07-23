package com.KaribouDev.KaribouWelcome.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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
            event.joinMessage(plugin.getConfigMessage(player, "public.new-player"));

            plugin.sendConfigMessages(player, "private.welcome");
            plugin.sendConfigMessages(player, "private.rules");

        } else {
            event.joinMessage(plugin.getConfigMessage(player, "public.login"));
        }
    }
}