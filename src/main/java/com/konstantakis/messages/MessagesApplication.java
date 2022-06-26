package com.konstantakis.messages;

import com.konstantakis.messages.model.dto.MessageDTO;
import com.konstantakis.messages.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class MessagesApplication {

	@Autowired
	private MessageRepository messageRepository;
	public static void main(String[] args) {
		SpringApplication.run(MessagesApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(MessageRepository repo) {
		return args -> repo.save(MessageDTO.builder().content("hello").createdOn(LocalDate.now()).build());
	}

}
