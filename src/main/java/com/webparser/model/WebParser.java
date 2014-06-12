package com.webparser.model;

import java.util.Map;

import org.jsoup.select.Elements;

public interface WebParser {

	// Elements found per rule applied
	Map<String, Elements> getElementsForUrl(String url);

}
