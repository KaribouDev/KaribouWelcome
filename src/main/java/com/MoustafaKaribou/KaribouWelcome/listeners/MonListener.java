package com.MoustafaKaribou.KaribouWelcome.listeners;

import com.MoustafaKaribou.KaribouWelcome.MonPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MonListener implements Listener {

    private final MonPlugin plugin;

    public MonListener(MonPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.joinMessage(
                Component.text("Welcome " + player.getName() + "!")
                        .color(NamedTextColor.GREEN)
        );

        if (!player.hasPlayedBefore()) {
            plugin.sendRules(player);
        }
    }
}