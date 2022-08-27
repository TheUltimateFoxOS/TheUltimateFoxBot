package com.github.theultimatefoxos.theultimatefoxbot.discord;

import com.github.theultimatefoxos.theultimatefoxbot.TheUltimateFoxBot;
import com.github.theultimatefoxos.theultimatefoxbot.discord.command.CommandManager;
import com.github.theultimatefoxos.theultimatefoxbot.discord.permissions.PermissionsManager;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.user.UserStatus;

public class DiscordBot {
    private final DiscordApi discordApi;
    private final CommandManager commandManager;
    private final PermissionsManager permissionsManager;

    public DiscordBot() {
        discordApi = new DiscordApiBuilder()
                .setToken(TheUltimateFoxBot.getConfig().getBot().getToken())
                .login().join();

        discordApi.updateStatus(UserStatus.ONLINE);
        discordApi.updateActivity(ActivityType.LISTENING, TheUltimateFoxBot.getConfig().getBot().getCommandPrefix() + "help");

        commandManager = new CommandManager(discordApi);
        permissionsManager = new PermissionsManager();

        discordApi.addMessageCreateListener(event -> {
            new Thread(() -> {
                this.commandManager.triggerCommand(event);
            }).start();
        });

        discordApi.addSlashCommandCreateListener(event -> {
            new Thread(() -> {
                this.commandManager.triggerCommand(event);
            }).start();
        });
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public PermissionsManager getPermissionsManager() {
        return permissionsManager;
    }

    public String getBotTag() {
        return discordApi.getYourself().getName() + "#" + discordApi.getYourself().getDiscriminator();
    }
}
