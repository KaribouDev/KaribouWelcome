package com.KaribouDev.KaribouWelcome;

import org.bukkit.plugin.java.JavaPlugin;

import com.KaribouDev.KaribouWelcome.listeners.EventListener;

/**
 * Main plugin class.
 * Paper calls onEnable() on startup and onDisable() on server shutdown.
 *
 * Responsibility: plugin lifecycle only.
 * Message logic is in MessageService, commands in dedicated executors.
 */
public final class KaribouWelcome extends JavaPlugin {

    private MessageService messageService;

    @Override
    public void onEnable() {
        getLogger().info("KaribouWelcome has been successfully activated.");

        // Load/create config.yml
        saveDefaultConfig();

        // Initialize the message service
        messageService = new MessageService(this);

        // Register the event listener
        getServer().getPluginManager().registerEvents(new EventListener(this), this);

        // Register command executors
        var kbwCommand = getCommand("karibouwelcome");
        if (kbwCommand != null) {
            kbwCommand.setExecutor(new KaribouCommandExecutor(this));
            kbwCommand.setTabCompleter(new MonTabCompleter());
        } else {
            getLogger().severe("Failed to register command 'karibouwelcome'. Check plugin.yml.");
        }

        var rulesCommand = getCommand("rules");
        if (rulesCommand != null) {
            rulesCommand.setExecutor(new RuleCommandExecutor(this));
        } else {
            getLogger().severe("Failed to register command 'rules'. Check plugin.yml.");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("KaribouWelcome has been deactivated.");
    }

    /**
     * Returns the message service for use by executors and listeners.
     */
    public MessageService getMessageService() {
        return messageService;
    }
}
