package com.github.theultimatefoxos.theultimatefoxbot.discord.commands.impl;

import com.github.theultimatefoxos.theultimatefoxbot.discord.Discord;
import com.github.theultimatefoxos.theultimatefoxbot.discord.commands.Command;
import com.github.theultimatefoxos.theultimatefoxbot.discord.commands.CommandEvent;
import com.github.theultimatefoxos.theultimatefoxbot.utils.ArrayUtils;

public class InviteCommand implements Command {
	@Override
	public void execute(CommandEvent event) throws Exception {
		event.commandSuccess("Here is the FoxOS discord invite link: " + Discord.discord.discord_invite);
	}

	@Override
	public void on_register() {
		
	}

	@Override
	public String get_short_help() {
		return "Sends the FoxOS discord invite link.";
	}

	@Override
	public String get_long_help() {
		return String.format("Use '%sinvite' to get the FoxOS discord invite link!", Discord.discord.commandManager.prefix);
	}

	@Override
	public String get_permission() {
		return null;
	}
}
