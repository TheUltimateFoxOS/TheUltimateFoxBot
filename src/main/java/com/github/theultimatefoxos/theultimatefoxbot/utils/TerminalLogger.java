package com.github.theultimatefoxos.theultimatefoxbot.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

public class TerminalLogger {
    private final Logger logger;

    public TerminalLogger() {
        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();

        RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.INFO);
        rootLogger.add(this.addAppender(builder, Level.INFO, Level.WARN, LogColor.AQUA));
        rootLogger.add(this.addAppender(builder, Level.WARN, Level.ERROR, LogColor.YELLOW));
        rootLogger.add(this.addAppender(builder, Level.ERROR, null, LogColor.RED));
        builder.add(rootLogger);

        Configurator.initialize(builder.build());

        logger = LogManager.getLogger("DragonWeb");
    }

    private AppenderRefComponentBuilder addAppender(ConfigurationBuilder<BuiltConfiguration> builder, Level level, Level overLevel, String colour) {
        LayoutComponentBuilder standard = builder.newLayout("PatternLayout");
        standard.addAttribute("pattern", "[%d{HH:mm:ss}] [" + colour + level.toString() + LogColor.RESET + "] %msg%n" + LogColor.RED + "%throwable" + LogColor.RESET);

        FilterComponentBuilder filter1 = builder.newFilter("ThresholdFilter", Filter.Result.NEUTRAL, Filter.Result.DENY);
        filter1.addAttribute("level", level.toString());

        FilterComponentBuilder filter2 = null;
        if (overLevel != null) {
            filter2 = builder.newFilter("ThresholdFilter", Filter.Result.DENY, Filter.Result.NEUTRAL);
            filter2.addAttribute("level", overLevel.toString());
        }

        AppenderComponentBuilder consoleAppender = builder.newAppender("stdout_" + level, "Console");
        consoleAppender.add(standard);
        builder.add(consoleAppender);

        AppenderRefComponentBuilder appenderRefComponent = builder.newAppenderRef("stdout_" + level);
        appenderRefComponent.addAttribute("level", level.toString());

        if (filter2 == null) {
            appenderRefComponent.addComponent(
                    builder.newComponent("filters")
                            .addComponent(filter1)
            );
        } else {
            appenderRefComponent.addComponent(
                    builder.newComponent("filters")
                            .addComponent(filter1)
                            .addComponent(filter2)
            );
        }

        return appenderRefComponent;
    }

    public void error(String message) {
        this.logger.error(message + LogColor.RESET);
    }

    public void error(String message, Throwable throwable) {
        this.logger.error(message + LogColor.RESET, throwable);
    }

    public void info(String message) {
        this.logger.info(message + LogColor.RESET);
    }

    public void warning(String message) {
        this.logger.warn(message + LogColor.RESET);
    }
}