package com.github.theultimatefoxos.theultimatefoxbot.commands;

import com.github.theultimatefoxos.theultimatefoxbot.api.WikipediaApi;
import com.github.theultimatefoxos.theultimatefoxbot.discord.command.Command;
import com.github.theultimatefoxos.theultimatefoxbot.discord.command.CommandEvent;
import org.javacord.api.interaction.SlashCommandOption;
import org.javacord.api.interaction.SlashCommandOptionType;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WikipediaCommand extends Command {
	public WikipediaCommand() {
		super("wikipedia", "Searches Wikipedia for a given query.");
	}

	@Override
	public void execute(CommandEvent event) {
		if (event.getArgs().length == 0) {
			event.reply("Invalid arguments");
		} else {
			WikipediaApi api = new WikipediaApi();

			try {
				event.reply(api.searchWiki(event.getArgsString()));
			} catch (IOException e) {
				event.reply("An error occurred while searching Wikipedia.");
			}
		}
	}

	@Override
	public List<SlashCommandOption> getOptions() {
		return Arrays.asList(SlashCommandOption.create(SlashCommandOptionType.STRING, "query", "The query to search for.", true));
	}
}
