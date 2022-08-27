package com.github.theultimatefoxos.theultimatefoxbot.commands;

import com.github.theultimatefoxos.theultimatefoxbot.TheUltimateFoxBot;
import com.github.theultimatefoxos.theultimatefoxbot.discord.command.Command;
import com.github.theultimatefoxos.theultimatefoxbot.discord.command.CommandEvent;
import org.javacord.api.interaction.SlashCommandOption;

import java.util.List;

public class InviteCommand extends Command {
	public InviteCommand() {
		super("invite", "Sends the FoxOS discord invite link.");
	}

	@Override
	public void execute(CommandEvent event) {
		event.reply("Here is the FoxOS discord invite link: " + TheUltimateFoxBot.getConfig().getDiscordInvite());
	}

	@Override
	public List<SlashCommandOption> getOptions() {
		return null;
	}
}
