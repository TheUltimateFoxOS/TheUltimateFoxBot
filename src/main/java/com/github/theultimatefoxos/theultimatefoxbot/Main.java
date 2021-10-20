package com.github.theultimatefoxos.theultimatefoxbot;

import com.github.theultimatefoxos.theultimatefoxbot.discord.Discord;
import com.github.theultimatefoxos.theultimatefoxbot.plugin.PluginsLoader;
import com.github.theultimatefoxos.theultimatefoxbot.utils.ArgParser;
import com.github.theultimatefoxos.theultimatefoxbot.utils.FileUtils;
import com.github.theultimatefoxos.theultimatefoxbot.discord.commands.impl.*;

import net.shadew.json.Json;
import net.shadew.json.JsonNode;

import com.google.common.io.Resources;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {
	public static PluginsLoader pluginsLoader;
	public static ArgParser parser;

	public static void main(String[] args) throws Exception {
		parser = new ArgParser(args);
		parser.parse();

		String token;
		String prefix;
		String discord_invite;

		if (parser.is_option("--no-cfg")) {
			token = System.getenv("DISCORD_TOKEN");
			prefix = System.getenv("DISCORD_PREFIX");
			discord_invite = System.getenv("DISCORD_INVITE");
		} else {
			String config_file = parser.consume_option("--config", "config.json");
			if (!FileUtils.getFileExists(config_file)) {
				String contents = null;
				try {
					URL url = Resources.getResource("config.json");
					contents = Resources.toString(url, StandardCharsets.UTF_8);
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (contents == null) {
					System.out.print("[ERROR] Your jar file is corrupted, try re-building it or contact a developer.");
					return;
				}

				try (FileWriter file = new FileWriter("config.json")) {
					file.write(contents);
					file.flush();

				} catch (IOException e) {
					System.out.print("[ERROR] Unable to save the config.");
					e.printStackTrace();
				}

				System.out.print("[ERROR] Please set a token and a prefix in \"config.json\"\n");
				return;
			}
			String config = FileUtils.readFile(config_file);

			Json config_json = Json.json();
			JsonNode config_root = config_json.parse(config);

			token = config_root.get("token").asString();
			prefix = config_root.get("prefix").asString();
			discord_invite = config_root.get("discord_invite").asString();
		}

		Discord.init(token, prefix, discord_invite);

		Discord.discord.receiver.tiny_crash_report = parser.is_option("--tiny-crash");

		Discord.discord.commandManager.addCommand("ping", new PingCommand());
		Discord.discord.commandManager.addCommand("say", new SayCommand());
		Discord.discord.commandManager.addCommand("nick", new NickCommand());
		Discord.discord.commandManager.addCommand("wikipedia", new WikipediaCommand());
		Discord.discord.commandManager.addCommand("role", new RoleCommand());
		Discord.discord.commandManager.addCommand("invite", new InviteCommand());

		pluginsLoader = new PluginsLoader("plugins");
		pluginsLoader.load_all();
	}
}
