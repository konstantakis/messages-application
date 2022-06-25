package com.konstantakis.messages.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.konstantakis.messages.model.Message;
import com.konstantakis.messages.service.MessageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageController.class)
class MessageControllerTest {

    @MockBean
    MessageService messageService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("SHOULD return 200 with message list WHEN calling get all messages endpoint")
    void getMessages_happyFlow_test() throws Exception {
        // given
        String expectedResponse = "[\n" +
                "    {\n" +
                "        \"id\": 123,\n" +
                "        \"content\": \"test-message\",\n" +
                "        \"createdOn\": \"24-06-2022\",\n" +
                "        \"changedOn\": \"25-06-2022\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 321,\n" +
                "        \"content\": \"test-message-2\",\n" +
                "        \"createdOn\": \"25-06-2022\",\n" +
                "        \"changedOn\": \"26-06-2022\"\n" +
                "    }\n" +
                "]";
        List<Message> messageList = List.of(
                Message.builder().id(123)
                        .content("test-message")
                        .createdOn(LocalDate.of(2022, 6, 24))
                        .changedOn(LocalDate.of(2022, 6, 25))
                        .build(),
                Message.builder().id(321)
                        .content("test-message-2")
                        .createdOn(LocalDate.of(2022, 6, 25))
                        .changedOn(LocalDate.of(2022, 6, 26))
                        .build()
        );


        given(messageService.getAllMessages()).willReturn(messageList);

        // when-then
        mockMvc.perform(get("/messages"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }
}