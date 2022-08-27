package com.github.theultimatefoxos.theultimatefoxbot.api;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaseApi {
	public String request(String url) throws IOException {
		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		con.setRequestProperty("Accept", "application/json");
		
		StringBuilder response = new StringBuilder();
		for (byte b : con.getInputStream().readAllBytes()) {
			response.append((char) b);
		}

		con.getInputStream().close();
		con.disconnect();

		return response.toString();
	}

	public void download(String path, String url) throws IOException {
		File destination = new File(path);
		BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());

		FileOutputStream fileOutputStream = new FileOutputStream(destination);
		
		byte[] dataBuffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
			fileOutputStream.write(dataBuffer, 0, bytesRead);
		}

		in.close();
		fileOutputStream.close();
	}
}
