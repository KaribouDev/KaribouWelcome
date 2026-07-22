package com.MoustafaKaribou.KaribouWelcome.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MonListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.joinMessage(
                Component.text("Welcome " + player.getName() + "!")
                        .color(NamedTextColor.GREEN)
        );

        if (!player.hasPlayedBefore()) {
            player.sendMessage(Component.text("Welcome to the server!", NamedTextColor.GOLD));
            player.sendMessage(Component.text("------------------------------", NamedTextColor.GRAY));
            player.sendMessage(Component.text("Server Rules", NamedTextColor.YELLOW));
            player.sendMessage(Component.text("1. Respect other players.", NamedTextColor.WHITE));
            player.sendMessage(Component.text("2. No cheating.", NamedTextColor.WHITE));
            player.sendMessage(Component.text("3. No griefing.", NamedTextColor.WHITE));
            player.sendMessage(Component.text("4. Have fun!", NamedTextColor.WHITE));
            player.sendMessage(Component.text("------------------------------", NamedTextColor.GRAY));
        }
    }
}