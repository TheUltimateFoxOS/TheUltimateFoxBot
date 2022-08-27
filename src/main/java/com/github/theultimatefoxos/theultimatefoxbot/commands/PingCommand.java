package com.github.theultimatefoxos.theultimatefoxbot.commands;

import com.github.theultimatefoxos.theultimatefoxbot.discord.command.Command;
import com.github.theultimatefoxos.theultimatefoxbot.discord.command.CommandEvent;
import org.javacord.api.interaction.SlashCommandOption;

import java.util.List;

public class PingCommand extends Command {
	public PingCommand() {
		super("ping", "Ping the bot.");
	}

	@Override
	public void execute(CommandEvent event) {
		event.reply("Pong!");
	}

	@Override
	public List<SlashCommandOption> getOptions() {
		return null;
	}
}
