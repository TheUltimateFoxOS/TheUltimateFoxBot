package com.github.theultimatefoxos.theultimatefoxbot.discord.command;

import org.javacord.api.interaction.SlashCommandOption;

import java.util.List;

public abstract class Command {
    private final String name;
    private final String description;
    private final boolean requiresAdmin;

    public Command(String name, String description, boolean requiresAdmin) {
        this.name = name;
        this.description = description;
        this.requiresAdmin = requiresAdmin;
    }

    public Command(String name, String description) {
        this(name, description, false);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean requiresAdmin() {
        return this.requiresAdmin;
    }

    public abstract void execute(CommandEvent event);

    public abstract List<SlashCommandOption> getOptions();
}
