package com.github.theultimatefoxos.theultimatefoxbot.discord.commands.impl;

import com.github.theultimatefoxos.theultimatefoxbot.discord.Discord;
import com.github.theultimatefoxos.theultimatefoxbot.discord.commands.Command;
import com.github.theultimatefoxos.theultimatefoxbot.discord.commands.CommandEvent;

public class WindowsCommand implements Command {
	@Override
	public void execute(CommandEvent event) throws Exception {
		event.commandSuccess("This is not the FoxOS for Windows, this FoxOS is our own operating system and has nothing to do with Windows. Here is their Discord server: https://discord.gg/y6tYP6Z");
	}

	@Override
	public void on_register() {
		
	}

	@Override
	public String get_short_help() {
		return "Says that this is not Windows.";
	}

	@Override
	public String get_long_help() {
		return String.format("Use '%swindows' to explain to the mislead travelers that this is not Windows.", Discord.discord.commandManager.prefix);
	}

	@Override
	public String get_permission() {
		return null;
	}
}
