package com.github.theultimatefoxos.theultimatefoxbot.discord.command;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.SlashCommandInteractionOption;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CommandEvent {
    private final String command;
    private final String[] args;
    private final TextChannel channel;
    private final User author;

    private final SlashCommandInteraction slashCommandInteraction;
    private boolean responseSent = false;

    public CommandEvent(String command, MessageCreateEvent event) {
        this.command = command;

        if (command.length() + 1 >= event.getMessageContent().length()) {
            this.args = new String[0];
        } else {
            this.args = event.getMessageContent().substring(command.length() + 1).split(" ");
        }

        this.channel = event.getChannel();

        if (event.getMessageAuthor().asUser().isPresent()) {
            this.author = event.getMessageAuthor().asUser().get();
        } else {
            this.author = null;
        }

        this.slashCommandInteraction = null;
    }

    public CommandEvent(String command, SlashCommandCreateEvent event) {
        SlashCommandInteraction slashCommandInteraction = event.getSlashCommandInteraction();

        this.command = command;

        List<String> args = new ArrayList<>();
        for (SlashCommandInteractionOption option : slashCommandInteraction.getOptions()) {
            if (option.getStringRepresentationValue().isPresent()) {
                args.add(option.getStringRepresentationValue().get());
            } else {
                args.add("");
            }
        }
        this.args = args.toArray(new String[0]);

        if (slashCommandInteraction.getChannel().isPresent()) {
            this.channel = slashCommandInteraction.getChannel().get();
        } else {
            this.channel = null;
        }

        this.author = slashCommandInteraction.getUser();

        this.slashCommandInteraction = slashCommandInteraction;
    }

    public String getCommand() {
        return this.command;
    }

    public String[] getArgs() {
        return this.args;
    }

    public String getArgsString() {
        if (args.length == 0) {
            return "";
        }

        return String.join(" ", this.args);
    }

    public TextChannel getChannel() {
        return this.channel;
    }

    public User getAuthor() {
        return this.author;
    }

    public void reply(String message) {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(this.command.substring(0, 1).toUpperCase() + this.command.substring(1) + " command")
                .setDescription(message)
                .setColor(Color.BLACK);

        if (this.slashCommandInteraction == null) {
            this.channel.sendMessage(embed);
        } else {
            if (this.responseSent) {
                this.getChannel().sendMessage(embed);
            } else {
                this.responseSent = true;
                this.slashCommandInteraction.createImmediateResponder()
                        .addEmbed(embed)
                        .respond().join();
            }
        }
    }
}
