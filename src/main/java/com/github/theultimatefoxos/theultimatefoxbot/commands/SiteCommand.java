package com.github.theultimatefoxos.theultimatefoxbot.commands;

import com.github.theultimatefoxos.theultimatefoxbot.discord.command.Command;
import com.github.theultimatefoxos.theultimatefoxbot.discord.command.CommandEvent;
import org.javacord.api.interaction.SlashCommandOption;

import java.util.List;

public class SiteCommand extends Command {
	public SiteCommand() {
		super("site", "Sends the FoxOS website link.");
	}

	@Override
	public void execute(CommandEvent event) {
		event.reply("Here is the FoxOS website link: https://theultimatefoxos.dev/");
	}

	@Override
	public List<SlashCommandOption> getOptions() {
		return null;
	}
}
