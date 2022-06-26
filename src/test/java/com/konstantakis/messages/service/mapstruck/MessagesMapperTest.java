package com.konstantakis.messages.service.mapstruck;

import com.konstantakis.messages.model.Message;
import com.konstantakis.messages.model.MessageRequestBody;
import com.konstantakis.messages.model.dto.MessageDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MessagesMapperImpl.class)
class MessagesMapperTest {

    @Autowired
    MessagesMapper underTest;

    @Test
    @DisplayName("SHOULD translate to Message object WHEN passing MessageDTO")
    void messageDTOToMessage_test() {
        // given
        MessageDTO input = MessageDTO.builder()
                .id(123L)
                .content("test-message")
                .createdOn(LocalDate.of(2022, 6, 24))
                .changedOn(LocalDate.of(2022, 6, 25))
                .build();

        // when
        Message output = underTest.messageDTOToMessage(input);

        // then
        assertNotNull(output);
        assertEquals(input.getId(), output.getId());
        assertEquals(input.getContent(), output.getContent());
        assertEquals(input.getCreatedOn(), output.getCreatedOn());
        assertEquals(input.getChangedOn(), output.getChangedOn());
    }

    @Test
    @DisplayName("SHOULD translate to MessageDTO object WHEN passing Message")
    void messageToMessageDTO_test() {
        // given
        MessageRequestBody input = MessageRequestBody.builder()
                .content("test-message")
                .build();

        // when
        MessageDTO output = underTest.messageRequestBodyToMessageDTO(input);

        // then
        assertNotNull(output);
        assertEquals(input.getContent(), output.getContent());
    }

}