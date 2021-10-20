package com.github.theultimatefoxos.theultimatefoxbot.discord.commands.impl;

import com.github.theultimatefoxos.theultimatefoxbot.discord.Discord;
import com.github.theultimatefoxos.theultimatefoxbot.discord.commands.CommandEvent;
import com.github.theultimatefoxos.theultimatefoxbot.utils.ArrayUtils;
import com.github.theultimatefoxos.theultimatefoxbot.discord.commands.Command;

public class SayCommand implements Command {
	@Override
	public void execute(CommandEvent event) throws Exception {
		if (event.args.length == 0) {
			event.commandFail("You need to specify a message to say.");
		} else {
			String message = ArrayUtils.stringify(event.args, " ");

			event.event.getChannel().sendMessage(message).queue();
		}
	}

	@Override
	public void on_register() {
		
	}

	@Override
	public String get_short_help() {
		return "Says a message.";
	}

	@Override
	public String get_long_help() {
		return String.format("Use '%ssay [what]' to say something!", Discord.discord.commandManager.prefix);
	}

	@Override
	public String get_permission() {
		return "say";
	}
}
