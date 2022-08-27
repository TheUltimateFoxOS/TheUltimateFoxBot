package com.github.theultimatefoxos.theultimatefoxbot.config;

public interface BotConfig {
    String getDiscordInvite();

    IBotSection getBot();

    IRolesSection getRoles();

    interface IBotSection {
        String getToken();

        String getCommandPrefix();
    }

    interface IRolesSection {
        long[] getAdmins();
    }
}
