package com.main;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ConsumerMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		new GenericXmlApplicationContext("consumer-applicationContext.xml");
	}
}
