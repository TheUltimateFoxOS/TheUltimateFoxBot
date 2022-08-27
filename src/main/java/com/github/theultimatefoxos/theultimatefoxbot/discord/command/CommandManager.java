package com.github.theultimatefoxos.theultimatefoxbot.discord.command;

import com.github.theultimatefoxos.theultimatefoxbot.TheUltimateFoxBot;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandBuilder;
import org.javacord.api.interaction.SlashCommandInteraction;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final DiscordApi discordApi;

    private final String botPrefix = TheUltimateFoxBot.getConfig().getBot().getCommandPrefix();
    private final Map<String, Command> commandMap = new HashMap<>();

    public CommandManager(DiscordApi discordApi) {
        this.discordApi = discordApi;

        SlashCommand.with("help", "Get information about commands")
                .setEnabledInDms(true)
                .createGlobal(this.discordApi)
                .join();
    }

    public void triggerCommand(MessageCreateEvent event) {
        if (event.getMessageAuthor().isBotUser() || !event.getMessageContent().startsWith(this.botPrefix)) {
            return;
        }

        String command = event.getMessageContent();
        if (command.contains(" ")) {
            command = command.split(" ")[0];
        }

        if (command.startsWith(this.botPrefix)) {
            command = command.substring(this.botPrefix.length());
        } else {
            return;
        }

        this.triggerCommand(new CommandEvent(command, event));
    }

    public void triggerCommand(SlashCommandCreateEvent event) {
        SlashCommandInteraction slashCommandInteraction = event.getSlashCommandInteraction();

        String command = slashCommandInteraction.getCommandName();

        this.triggerCommand(new CommandEvent(command, event));
    }

    private void triggerCommand(CommandEvent event) {
        String command = event.getCommand();

        if (command.equals("help")) {
            this.helpCommand(event);
            return;
        }

        if (this.commandMap.containsKey(command)) {
            Command cmd = this.commandMap.get(command);

            if (cmd.requiresAdmin() && !this.isUserAdmin(event.getAuthor())) {
                event.reply("You do not have permission to run this command.");
                return;
            }

            try {
                TheUltimateFoxBot.getLogger().info("Command executed by " + event.getAuthor().getName() + " (" + event.getAuthor().getId() + "): " + command);
                cmd.execute(event);
            } catch (Exception e) {
                TheUltimateFoxBot.getLogger().error("An error occurred whilst running command by " + event.getAuthor().getName() + " (" + event.getAuthor().getId() + "): " + command, e);
                event.reply("An error occurred whilst running this command.");
            }
        }
    }

    private void helpCommand(CommandEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("Available commands:\n");
        for (Command cmd : commandMap.values()) {
            if (cmd.requiresAdmin() && !this.isUserAdmin(event.getAuthor())) {
                continue;
            }
            sb.append("- ").append(cmd.getName()).append(": ").append(cmd.getDescription()).append("\n");
        }
        event.reply(sb.toString());
    }

    private boolean isUserAdmin(User author) {
        return TheUltimateFoxBot.getBot().getPermissionsManager().isUserAdmin(author.getId());
    }

    public void registerCommand(Command command) {
        commandMap.put(command.getName(), command);

        SlashCommandBuilder slashCommand;
        if (command.getOptions() == null) {
            slashCommand = SlashCommand.with(command.getName(), command.getDescription());
        } else {
            slashCommand = SlashCommand.with(command.getName(), command.getDescription(), command.getOptions());
        }

        slashCommand.setEnabledInDms(true)
                .createGlobal(this.discordApi)
                .join();
    }
}
