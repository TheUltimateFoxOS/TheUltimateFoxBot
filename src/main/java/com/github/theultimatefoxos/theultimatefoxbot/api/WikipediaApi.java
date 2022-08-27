package com.github.theultimatefoxos.theultimatefoxbot.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class WikipediaApi extends BaseApi {
	private final ObjectMapper mapper = new ObjectMapper();

	public String searchWiki(String query) throws IOException {
		query = query.replaceAll(" ", "%20");
		
		String res = this.request("https://en.wikipedia.org/api/rest_v1/page/summary/" + query);
		JsonNode root = this.mapper.readTree(res);
		return root.get("extract").asText();
	}
}
