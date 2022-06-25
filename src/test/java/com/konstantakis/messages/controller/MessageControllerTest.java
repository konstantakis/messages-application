package com.konstantakis.messages.controller;

import com.konstantakis.messages.service.MessageService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(MessageController.class)
class MessageControllerTest {

    @MockBean
    private MessageService messageServiceMock;

}