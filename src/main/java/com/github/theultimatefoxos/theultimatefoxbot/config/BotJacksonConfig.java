package com.github.theultimatefoxos.theultimatefoxbot.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.theultimatefoxos.theultimatefoxbot.utils.FileUtil;
import lombok.Getter;

import java.io.File;
import java.io.IOException;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BotJacksonConfig implements BotConfig {
    @JsonProperty("discord-invite")
    private String discordInvite;

    @JsonProperty("bot")
    private BotSection bot = new BotSection();

    @JsonProperty("roles")
    private RolesSection roles = new RolesSection();

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BotSection implements IBotSection {
        @JsonProperty("token")
        private String token;

        @JsonProperty("command-prefix")
        private String commandPrefix;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RolesSection implements IRolesSection {
        @JsonProperty("admins")
        private long[] admins;
    }

    public static BotJacksonConfig loadConfig() throws IOException {
        File configFile = new File("config.yml");
        if (!configFile.exists()) {
            FileUtil.copyFileFromResources("config.yml", configFile);
        }

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(configFile, BotJacksonConfig.class);
    }
}
