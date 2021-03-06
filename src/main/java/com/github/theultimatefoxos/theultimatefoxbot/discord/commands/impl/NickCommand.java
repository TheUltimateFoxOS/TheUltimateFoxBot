package com.github.theultimatefoxos.theultimatefoxbot.discord.commands.impl;

import com.github.theultimatefoxos.theultimatefoxbot.discord.Discord;
import com.github.theultimatefoxos.theultimatefoxbot.discord.commands.CommandEvent;
import com.github.theultimatefoxos.theultimatefoxbot.discord.commands.Command;

public class NickCommand implements Command {
	@Override
	public void execute(CommandEvent event) throws Exception {
		if (event.args.length != 1) {
			event.commandFail("Please specify a new nickname.");
		} else {
			String nick = event.args[0];

			event.event.getGuild().getSelfMember().modifyNickname(nick).queue();

			event.commandSuccess("Nickname changed to " + nick);
		}
	}

	@Override
	public void on_register() {
		
	}

	@Override
	public String get_short_help() {
		return "Set the bot's nickname.";
	}

	@Override
	public String get_long_help() {
		return String.format("Use '%snick [nickname]' set the bot's nickname!", Discord.discord.commandManager.prefix);
	}

	@Override
	public String get_permission() {
		return "nick";
	}
}
