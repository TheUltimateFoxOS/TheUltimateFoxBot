package com.github.theultimatefoxos.theultimatefoxbot.commands;

import com.github.theultimatefoxos.theultimatefoxbot.discord.command.Command;
import com.github.theultimatefoxos.theultimatefoxbot.discord.command.CommandEvent;
import org.javacord.api.interaction.SlashCommandOption;

import java.util.List;

public class WindowsCommand extends Command {
	public WindowsCommand() {
		super("windows", "Says that this is not Windows.");
	}

	@Override
	public void execute(CommandEvent event) {
		event.reply("This is not the FoxOS for Windows, this FoxOS is our own operating system and has nothing to do with Windows. Here is their Discord server: https://discord.gg/y6tYP6Z");
	}

	@Override
	public List<SlashCommandOption> getOptions() {
		return null;
	}
}
