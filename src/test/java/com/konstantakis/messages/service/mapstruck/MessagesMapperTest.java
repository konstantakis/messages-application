package com.konstantakis.messages.service.mapstruck;

import com.konstantakis.messages.model.Message;
import com.konstantakis.messages.model.dto.MessageDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        MessageDTO input = MessageDTO.builder().id(123)
                .content("test-message")
                .createdOn(LocalDate.of(2022, 6, 24))
                .changedOn(LocalDate.of(2022, 6, 25))
                .dbRecordChangedOn(LocalDateTime.of(2022, 6, 24, 9, 30))
                .dbRecordChangedOn(LocalDateTime.of(2022, 6, 25, 10, 40))
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

}