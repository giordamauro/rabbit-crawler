package com.consumer.model;

import java.lang.Thread.UncaughtExceptionHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class WorkerMessageListener implements MessageListener {

	private static final Log logger = LogFactory.getLog(WorkerMessageListener.class);

	private final MessageWorker messageWorker;

	private static final UncaughtExceptionHandler exceptionLogger = new UncaughtExceptionHandler() {

		@Override
		public void uncaughtException(Thread thread, Throwable e) {
			logger.warn(String.format("Exception in thread name: '%s' id '%s'", thread.getName(), thread.getId()), e);
		}
	};

	public WorkerMessageListener(MessageWorker messageWorker) {
		if (messageWorker == null) {
			throw new IllegalArgumentException("MessageWorker cannot be null");
		}

		logger.info(String.format("Registering messageWorker class %s", messageWorker.getClass()));

		this.messageWorker = messageWorker;
	}

	public void onMessage(final Message message) {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				messageWorker.handleMessage(message);
			}
		};

		Thread thread = new Thread(runnable);
		thread.setUncaughtExceptionHandler(exceptionLogger);

		logger.info(String.format("Starting worker thread %s", thread.getId()));

		thread.start();
	}

}