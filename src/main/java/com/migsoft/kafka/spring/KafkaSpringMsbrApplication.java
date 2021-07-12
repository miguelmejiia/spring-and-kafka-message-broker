package com.migsoft.kafka.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class KafkaSpringMsbrApplication {

	private static final Logger log = LoggerFactory.getLogger(KafkaSpringMsbrApplication.class);

	@KafkaListener(topics="java-topic", groupId = "java-group")
	public void listen(String message){
		log.info("Message received {} ", message);
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaSpringMsbrApplication.class, args);
	}

}
