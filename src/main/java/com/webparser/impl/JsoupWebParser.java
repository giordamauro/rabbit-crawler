package com.webparser.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.webparser.model.WebParser;

public class JsoupWebParser implements WebParser {

	private static final Log logger = LogFactory.getLog(JsoupWebParser.class);

	private final List<String> rules;

	private String userAgent = "Mozzila";

	public JsoupWebParser(List<String> rules) {
		if (rules == null) {
			throw new IllegalArgumentException("Rules cannot be null");
		}

		logger.info(String.format("Registering rules '%s'", rules));

		this.rules = rules;
	}

	public Map<String, Elements> getElementsForUrl(String url) {

		Map<String, Elements> elementsMap = new HashMap<>();

		try {
			Document doc = Jsoup.connect(url).userAgent(userAgent).get();

			for (String rule : rules) {

				logger.info(String.format("Applying rule '%s' to url '%s'", rule, url));

				Elements elements = doc.select(rule);
				elementsMap.put(rule, elements);
			}

			return elementsMap;

		} catch (IOException e) {
			throw new IllegalStateException(String.format("Exception getting elements for url '%s'", url), e);
		}
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

}
