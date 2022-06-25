package com.konstantakis.messages;

import com.konstantakis.messages.controller.MessageController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MessagesApplicationTests {

	@Autowired
	private MessageController controller;

	@Test
	void contextLoads() {
		assertNotNull(controller);
	}

}
