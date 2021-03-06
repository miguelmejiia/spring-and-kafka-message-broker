package com.migsoft.kafka.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

@SpringBootApplication
public class KafkaSpringMsbrApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(KafkaSpringMsbrApplication.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@KafkaListener(topics="java-topic", containerFactory = "listenerContainerFactory", groupId = "java-group",
		properties = {"max.poll.interval.ms:4000", "max.poll.records:10"})
	public void listen(List<String> messages){
		log.info("Start reading messages");
		for (String message: messages) {
			log.info("Message received {}", message);
		}
		log.info("Batch complete");
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaSpringMsbrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*ListenableFuture<SendResult <String, String>> future =  kafkaTemplate.send("java-topic", "Sample message");
		future.addCallback(new KafkaSendCallback<String, String>() {

			@Override
			public void onSuccess(SendResult<String, String> stringStringSendResult) {
				log.info("Message sent ", stringStringSendResult.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("Error sending message ", ex);
			}

			@Override
			public void onFailure(KafkaProducerException e) {
				log.error("Error sending message ", e);
			}
		});*/
		for (int i = 0; i < 100; i++) {
			kafkaTemplate.send("java-topic", String.format("Sample message %d", i));
		}
	}
}
