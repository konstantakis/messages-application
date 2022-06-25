package com.konstantakis.messages.service;

import com.konstantakis.messages.model.Message;
import com.konstantakis.messages.model.dto.MessageDTO;
import com.konstantakis.messages.repository.MessageRepository;
import com.konstantakis.messages.service.mapstruck.MessagesMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @Mock
    MessageRepository messageRepository;

    @Mock
    MessagesMapper messagesMapper;

    @InjectMocks
    MessageServiceImpl underTest;

    @Test
    @DisplayName("SHOULD call the repository, mapper and return list of messages WHEN repository and mapper is working properly")
    void getAllMessages_happyFlow_test() {
        // given
        List<MessageDTO> messageDTOs = List.of(
                MessageDTO.builder().id(123)
                        .content("test-message")
                        .createdOn(LocalDate.of(2022, 6, 24))
                        .changedOn(LocalDate.of(2022, 6, 25))
                        .dbRecordChangedOn(LocalDateTime.of(2022, 6, 24, 9, 30))
                        .dbRecordChangedOn(LocalDateTime.of(2022, 6, 25, 10, 40))
                        .build(),
                MessageDTO.builder().id(321)
                        .content("test-message-2")
                        .createdOn(LocalDate.of(2022, 6, 25))
                        .changedOn(LocalDate.of(2022, 6, 26))
                        .dbRecordChangedOn(LocalDateTime.of(2022, 6, 25, 11, 45))
                        .dbRecordChangedOn(LocalDateTime.of(2022, 6, 26, 13, 58))
                        .build()
        );
        List<Message> expectedMessageList = List.of(
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
        given(messageRepository.findAll()).willReturn(messageDTOs);
        given(messagesMapper.messageDTOToMessage(messageDTOs.get(0))).willReturn(expectedMessageList.get(0));
        given(messagesMapper.messageDTOToMessage(messageDTOs.get(1))).willReturn(expectedMessageList.get(1));

        // when
        List<Message> actualMessageList = underTest.getAllMessages();

        // then
        assertNotNull(actualMessageList);
        assertEquals(2, actualMessageList.size());
        assertEquals(expectedMessageList.get(0), expectedMessageList.get(0));
        assertEquals(expectedMessageList.get(1), expectedMessageList.get(1));
    }

}