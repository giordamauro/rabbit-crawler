package com.consumer.model;

import org.springframework.amqp.core.Message;

public interface MessageWorker {

	void handleMessage(Message message);
}
