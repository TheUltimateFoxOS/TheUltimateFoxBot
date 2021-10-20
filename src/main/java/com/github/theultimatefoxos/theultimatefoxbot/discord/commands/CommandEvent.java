package com.github.theultimatefoxos.theultimatefoxbot.discord.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandEvent {
	public final String message;
	public final String command;
	public final String[] args;

	public final MessageReceivedEvent event;

	public CommandEvent(String message, String command, String[] args, MessageReceivedEvent event) {
		this.message = message;
		this.command = command;
		this.args = args;
		this.event = event;
	}

	public static String[] getArguments(String[] array) {
		String[] args = new String[array.length - 1];
		System.arraycopy(array, 1, args, 0, array.length - 1);
		return args;
	}

	public void commandFail(String reason) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Command failed");
		eb.setDescription(reason);
		event.getChannel().sendMessage(eb.build()).queue();
	}

	public void commandSuccess(String reason) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Command succeeded");
		eb.setDescription(reason);
		event.getChannel().sendMessage(eb.build()).queue();
	}

	public void permFail() {
		commandFail("You do not have permission to use this command");
	}
}
