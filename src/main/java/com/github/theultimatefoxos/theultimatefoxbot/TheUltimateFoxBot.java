package com.github.theultimatefoxos.theultimatefoxbot;

import com.github.theultimatefoxos.theultimatefoxbot.commands.*;
import com.github.theultimatefoxos.theultimatefoxbot.config.BotConfig;
import com.github.theultimatefoxos.theultimatefoxbot.config.BotJacksonConfig;
import com.github.theultimatefoxos.theultimatefoxbot.discord.DiscordBot;
import com.github.theultimatefoxos.theultimatefoxbot.discord.command.CommandManager;
import com.github.theultimatefoxos.theultimatefoxbot.utils.TerminalLogger;

import java.io.IOException;

public class TheUltimateFoxBot {
	private static TerminalLogger logger;
	private static BotConfig config;
	private static DiscordBot bot;

	public static void main(String[] args) {
		logger = new TerminalLogger();
		logger.info("Bot is starting up...");

		try {
			config = BotJacksonConfig.loadConfig();
		} catch (IOException e) {
			logger.error("Failed to load config file", e);
		}

		bot = new DiscordBot();
		logger.info("Bot started and logged in as " + bot.getBotTag() + "!");

		CommandManager commandManager = bot.getCommandManager();
		commandManager.registerCommand(new InviteCommand());
		commandManager.registerCommand(new PingCommand());
		commandManager.registerCommand(new SiteCommand());
		commandManager.registerCommand(new WikipediaCommand());
		commandManager.registerCommand(new WindowsCommand());
	}

	public static TerminalLogger getLogger() {
		return logger;
	}

	public static BotConfig getConfig() {
		return config;
	}

	public static DiscordBot getBot() {
		return bot;
	}
}
