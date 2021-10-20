package com.github.theultimatefoxos.theultimatefoxbot;

import com.github.theultimatefoxos.theultimatefoxbot.discord.Discord;
import com.github.theultimatefoxos.theultimatefoxbot.plugin.PluginsLoader;
import com.github.theultimatefoxos.theultimatefoxbot.utils.ArgParser;
import com.github.theultimatefoxos.theultimatefoxbot.utils.FileUtils;
import com.github.theultimatefoxos.theultimatefoxbot.discord.commands.impl.*;
import net.shadew.json.Json;
import net.shadew.json.JsonNode;

public class Main {
	public static PluginsLoader pluginsLoader;
	public static ArgParser parser;

	public static void main(String[] args) throws Exception {
		parser = new ArgParser(args);
		parser.parse();

		String token;
		String prefix;

		if (parser.is_option("--no-cfg")) {
			token = System.getenv("DISCORD_TOKEN");
			prefix = System.getenv("DISCORD_PREFIX");
		} else {
			String config_file = parser.consume_option("--config", "config.json");
			String config = FileUtils.readFile(config_file);

			Json config_json = Json.json();
			JsonNode config_root = config_json.parse(config);

			token = config_root.get("token").asString();
			prefix = config_root.get("prefix").asString();
		}

		Discord.init(token, prefix);

		Discord.discord.receiver.tiny_crash_report = parser.is_option("--tiny-crash");

		Discord.discord.commandManager.addCommand("ping", new PingCommand());
		Discord.discord.commandManager.addCommand("say", new SayCommand());
		Discord.discord.commandManager.addCommand("nick", new NickCommand());
		Discord.discord.commandManager.addCommand("wikipedia", new WikipediaCommand());
		Discord.discord.commandManager.addCommand("role", new RoleCommand());

		pluginsLoader = new PluginsLoader("plugins");
		pluginsLoader.load_all();
	}
}
