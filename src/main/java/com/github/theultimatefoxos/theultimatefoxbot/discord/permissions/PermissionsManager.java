package com.github.theultimatefoxos.theultimatefoxbot.discord.permissions;

import com.github.theultimatefoxos.theultimatefoxbot.TheUltimateFoxBot;

public class PermissionsManager {
    public boolean isUserAdmin(long userId) {
        for (long adminId : TheUltimateFoxBot.getConfig().getRoles().getAdmins()) {
            if (adminId == userId) {
                return true;
            }
        }

        return false;
    }
}
