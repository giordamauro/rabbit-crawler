package com.consumer.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.select.Elements;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import com.consumer.model.MessageWorker;
import com.webparser.model.WebParser;

public class WebParserMessageWorker implements MessageWorker {

	private static final Log logger = LogFactory.getLog(WebParserMessageWorker.class);

	private final WebParser webParser;

	public WebParserMessageWorker(WebParser webParser) {
		if (webParser == null) {
			throw new IllegalArgumentException("WebParser cannot be null");
		}

		logger.info(String.format("Registering WebParser class %s", webParser.getClass()));

		this.webParser = webParser;
	}

	@Override
	public void handleMessage(Message message) {

		if (message == null) {
			throw new IllegalArgumentException("Message cannot be null");
		}

		byte[] messageBytes = message.getBody();
		String url = new String(messageBytes);

		MessageProperties messageProperties = message.getMessageProperties();

		logger.info(String.format("Handling message Body: '%s' - MessageProperties: '%s'", url, messageProperties));

		Map<String, Elements> elements = webParser.getElementsForUrl(url);

		// TODO: functionality
		System.out.println(elements);
	}
}
