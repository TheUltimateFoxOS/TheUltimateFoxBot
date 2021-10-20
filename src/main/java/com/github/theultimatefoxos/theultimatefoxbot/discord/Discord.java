package com.github.theultimatefoxos.theultimatefoxbot.discord;

import javax.security.auth.login.LoginException;

import com.github.theultimatefoxos.theultimatefoxbot.discord.commands.CommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Discord {
	// ------------------- static fields -------------------
	public static Discord discord;
	
	public static void init(String token, String prefix) throws LoginException, InterruptedException {
		Discord.discord = new Discord(token, prefix);
	}
	// -----------------------------------------------------

	String current_rp;

	private String token;
	public JDA jda;
	public CommandManager commandManager;

	public DiscordReceiver receiver;

	private Discord(String token, String prefix) throws LoginException, InterruptedException {
		this.token = token;

		JDABuilder jdaBuilder = JDABuilder.createDefault(this.token);
        jda = jdaBuilder.build();

		jda.awaitReady();

		receiver = new DiscordReceiver();

		jda.addEventListener(receiver);

		commandManager = new CommandManager(prefix);

		this.setDefaultRP();

		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000 * 60);
					} catch (InterruptedException e) {
						e.printStackTrace();
						return;
					}

					Discord.discord.updateRP();
				}
			}
		}.start();
	}

	public void setDefaultRP() {
		jda.getPresence().setActivity(net.dv8tion.jda.api.entities.Activity.listening(this.commandManager.prefix + "help"));
	}

	public void updateRP() {
		jda.getPresence().setActivity(net.dv8tion.jda.api.entities.Activity.listening(this.commandManager.prefix + "help"));
	}
}
