package com.konstantakis.messages.service;

import com.konstantakis.messages.model.Message;
import com.konstantakis.messages.model.dto.MessageDTO;
import com.konstantakis.messages.repository.MessageRepository;
import com.konstantakis.messages.service.mapstruck.MessagesMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


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
                MessageDTO.builder().id(123L)
                        .content("test-message")
                        .createdOn(LocalDate.of(2022, 6, 24))
                        .changedOn(LocalDate.of(2022, 6, 25))
                        .build(),
                MessageDTO.builder().id(321L)
                        .content("test-message-2")
                        .createdOn(LocalDate.of(2022, 6, 25))
                        .changedOn(LocalDate.of(2022, 6, 26))
                        .build()
        );
        List<Message> expectedMessageList = List.of(
                Message.builder().id(123L)
                        .content("test-message")
                        .createdOn(LocalDate.of(2022, 6, 24))
                        .changedOn(LocalDate.of(2022, 6, 25))
                        .build(),
                Message.builder().id(321L)
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

        verify(messageRepository).findAll();
        verify(messagesMapper).messageDTOToMessage(messageDTOs.get(0));
        verify(messagesMapper).messageDTOToMessage(messageDTOs.get(1));
        verifyNoMoreInteractions(messageRepository);
        verifyNoMoreInteractions(messagesMapper);

    }

    @Test
    @DisplayName("SHOULD set the createdOn fields, call the repository mapper layer and return list of messages WHEN repository and mapper is working properly")
    void createMessage_happyFlow_test() {
        // given
        Message inputMessage = Message.builder()
                .content("test-message")
                .build();
        MessageDTO mapperOutput = MessageDTO.builder()
                .content("test-message")
                .build();
        MessageDTO repositoryOutput = MessageDTO.builder()
                .content("test-message")
                .createdOn(LocalDate.now())
                .build();
        Message expectedMessage = Message.builder()
                .content("test-message")
                .createdOn(LocalDate.now())
                .build();


        given(messagesMapper.messageToMessageDTO(inputMessage)).willReturn(mapperOutput);
        given(messageRepository.save(any())).willReturn(repositoryOutput);
        given(messagesMapper.messageDTOToMessage(repositoryOutput)).willReturn(expectedMessage);

        // when
        Message actualMessage = underTest.createMessage(inputMessage);

        // then
        assertNotNull(actualMessage);
        assertEquals(expectedMessage, actualMessage);

        ArgumentCaptor<MessageDTO> messageDTOCaptor = ArgumentCaptor.forClass(MessageDTO.class);
        verify(messageRepository).save(messageDTOCaptor.capture());
        verifyNoMoreInteractions(messageRepository);
        MessageDTO repositoryInput = messageDTOCaptor.getValue();
        assertNotNull(repositoryInput);
        assertNull(repositoryInput.getId());
        assertEquals(mapperOutput.getContent(), repositoryInput.getContent());
        assertEquals(LocalDate.now(), repositoryInput.getCreatedOn());
        assertNull(repositoryInput.getChangedOn());

        verify(messagesMapper).messageToMessageDTO(inputMessage);
        verify(messagesMapper).messageDTOToMessage(repositoryOutput);
        verifyNoMoreInteractions(messagesMapper);
    }
}