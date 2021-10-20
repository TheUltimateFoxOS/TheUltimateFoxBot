package com.github.theultimatefoxos.theultimatefoxbot.plugin;

public class PluginLoadFail extends Exception {
	public PluginLoadFail(Exception e)
	{
		super("Plugin load fail: " + e.getMessage());
	}
}
