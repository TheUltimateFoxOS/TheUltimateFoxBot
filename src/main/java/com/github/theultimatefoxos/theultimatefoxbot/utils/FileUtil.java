package com.github.theultimatefoxos.theultimatefoxbot.utils;

import com.github.theultimatefoxos.theultimatefoxbot.TheUltimateFoxBot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
    public static File copyFileFromResources(String resourceName, File outFile) throws IOException {
        InputStream stream = TheUltimateFoxBot.class.getClassLoader().getResourceAsStream(resourceName); //note that each / is a directory down in the "jar tree" been the jar the root of the tree
        if (stream == null) {
            throw new IOException("Cannot get resource \"" + resourceName + "\" from jar file.");
        }

        FileOutputStream resStreamOut = new FileOutputStream(outFile);

        int readBytes;
        byte[] buffer = new byte[4096];
        while ((readBytes = stream.read(buffer)) > 0) {
            resStreamOut.write(buffer, 0, readBytes);
        }

        stream.close();
        resStreamOut.close();

        return outFile;
    }
}
