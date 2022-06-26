package com.konstantakis.messages.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.konstantakis.messages.model.ErrorResponse;
import com.konstantakis.messages.model.Message;
import com.konstantakis.messages.service.MessageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageController.class)
@AutoConfigureMockMvc
class MessageControllerTest {

    @MockBean
    MessageService messageService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("SHOULD return 200 with message list WHEN get all messages endpoint is called")
    void getMessages_happyFlow_test() throws Exception {
        // given
        String expectedResponse = "[\n" +
                "    {\n" +
                "        \"id\": 123,\n" +
                "        \"content\": \"test-message\",\n" +
                "        \"createdOn\": \"2022-06-24\",\n" +
                "        \"changedOn\": \"2022-06-25\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 321,\n" +
                "        \"content\": \"test-message-2\",\n" +
                "        \"createdOn\": \"2022-06-25\",\n" +
                "        \"changedOn\": \"2022-06-26\"\n" +
                "    }\n" +
                "]";
        List<Message> messageList = List.of(
                Message.builder()
                        .id(123)
                        .content("test-message")
                        .createdOn(LocalDate.of(2022, 6, 24))
                        .changedOn(LocalDate.of(2022, 6, 25))
                        .build(),
                Message.builder()
                        .id(321)
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

        verify(messageService).getAllMessages();
        verifyNoMoreInteractions(messageService);
    }

    @Test
    @DisplayName("SHOULD return 201 with the created message and today created date WHEN post message endpoint is called")
    void postMessages_happyFlow_test() throws Exception {
        // given
        String localDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String expectedResponse = "{\n" +
                "   \"id\":1,\n" +
                "   \"content\":\"Hello World\",\n" +
                "   \"createdOn\":" + localDate + ",\n" +
                "   \"changedOn\":null\n" +
                "}";

        given(messageService.createMessage(any())).willReturn(Message.builder()
                .id(1)
                .content("Hello World")
                .createdOn(LocalDate.now())
                .changedOn(null)
                .build());

        // when-then
        mockMvc.perform(post("/messages")
                        //.content(asJsonString(body))
                        .content("{\n" +
                                "    \"content\": \"Hello World\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedResponse));

        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
        verify(messageService).createMessage(messageCaptor.capture());
        verifyNoMoreInteractions(messageService);
        Message actualRequestBody = messageCaptor.getValue();
        assertNotNull(actualRequestBody);
        assertNull(actualRequestBody.getId());
        assertEquals("Hello World", actualRequestBody.getContent());
        assertNull(actualRequestBody.getCreatedOn());
        assertNull(actualRequestBody.getChangedOn());
    }

    @Test
    @DisplayName("SHOULD return 400 with error WHEN post message endpoint is called with missing content")
    void postMessages_badRequest_test() throws Exception {
        // given-when
        MockHttpServletResponse response = mockMvc.perform(post("/messages")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();


        // then
        assertNotNull(response.getStatus());
        assertEquals(400, response.getStatus());
        ObjectMapper mapper = new ObjectMapper();
        ErrorResponse responseBody = mapper.readValue(response.getContentAsString(), ErrorResponse.class);
        assertNotNull(responseBody);
        assertEquals("Bad Request", responseBody.getError());
        assertTrue(responseBody.getMessage().contains("Content can't be empty"));
        assertTrue(responseBody.getMessage().contains("Content can't be null"));
        assertEquals("", responseBody.getTraceId());
    }
}