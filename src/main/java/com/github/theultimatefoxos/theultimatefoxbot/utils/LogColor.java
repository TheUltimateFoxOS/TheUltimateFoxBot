package com.github.theultimatefoxos.theultimatefoxbot.utils;

public class LogColor {
    public static final String ESCAPE = (char) 0x1b + "[";
    public static final String BLACK = ESCAPE + "0;30m";
    public static final String DARK_BLUE = ESCAPE + "0;34m";
    public static final String DARK_GREEN = ESCAPE + "0;32m";
    public static final String DARK_AQUA = ESCAPE + "0;36m";
    public static final String DARK_RED = ESCAPE + "0;31m";
    public static final String DARK_PURPLE = ESCAPE + "0;35m";
    public static final String GOLD = ESCAPE + "0;33m";
    public static final String GRAY = ESCAPE + "0;37m";
    public static final String DARK_GRAY = ESCAPE + "30;1m";
    public static final String BLUE = ESCAPE + "34;1m";
    public static final String GREEN = ESCAPE + "32;1m";
    public static final String AQUA = ESCAPE + "36;1m";
    public static final String RED = ESCAPE + "31;1m";
    public static final String LIGHT_PURPLE = ESCAPE + "35;1m";
    public static final String YELLOW = ESCAPE + "33;1m";
    public static final String WHITE = ESCAPE + "37;1m";
    public static final String OBFUSCATED = ESCAPE + "5m";
    public static final String BOLD = ESCAPE + "1m";
    public static final String STRIKETHROUGH = ESCAPE + "9m";
    public static final String UNDERLINE = ESCAPE + "4m";
    public static final String ITALIC = ESCAPE + "3m";
    public static final String RESET = ESCAPE + "0m";
}
