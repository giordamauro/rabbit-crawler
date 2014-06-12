package com.consumer;

import org.springframework.amqp.core.Message;

import com.consumer.model.MessageWorker;

class StubMessageWorker implements MessageWorker {

	public void handleMessage(Message message) {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}

		byte[] messageContent = message.getBody();
		String stringMessage = new String(messageContent);

		long threadId = Thread.currentThread().getId();

		System.out.println(String.format("Thread: %s - Timestamp: %s - Message receied: '%s'", threadId, System.currentTimeMillis(), stringMessage));
	}
}
