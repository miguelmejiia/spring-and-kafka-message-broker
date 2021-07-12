package com.migsoft.kafka.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaSpringMsbrApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(KafkaSpringMsbrApplication.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@KafkaListener(topics="java-topic", groupId = "java-group")
	public void listen(String message){
		log.info("Message received {} ", message);
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaSpringMsbrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		kafkaTemplate.send("java-topic", "Sample message");
	}
}
