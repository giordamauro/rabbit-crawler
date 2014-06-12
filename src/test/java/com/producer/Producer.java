package com.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Producer {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new GenericXmlApplicationContext("producer-applicationContext.xml");
		AmqpTemplate amqpTemplate = context.getBean(AmqpTemplate.class);

		for (int i = 0; i < 1000; i++) {
			amqpTemplate.convertAndSend("helloworld.queue", "Hello World " + i);
		}

		System.out.println("Finished sending messages");

		System.exit(0);
	}
}
