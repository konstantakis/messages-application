package com.konstantakis.messages.service;

import com.konstantakis.messages.model.Message;
import com.konstantakis.messages.model.dto.MessageDTO;
import com.konstantakis.messages.repository.MessageRepository;
import com.konstantakis.messages.service.mapstruck.MessagesMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * add
 *  javadoc
 *  implementation
 *  tests
 */
@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    private MessagesMapper messagesMapper;

    @Override
    public Message createMessage(Message message) {
        MessageDTO messageDTO = messagesMapper.messageToMessageDTO(message);
        messageDTO.setId(null);
        messageDTO.setCreatedOn(LocalDate.now());
        return messagesMapper.messageDTOToMessage(messageRepository.save(messageDTO));
    }

    @Override
    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        messageRepository.findAll().forEach(
                (MessageDTO messageDTO) -> messages.add(messagesMapper.messageDTOToMessage(messageDTO)));
        return messages;
    }

    @Override
    public Message getMessage(String id) {
        return null;
    }

    @Override
    public Message updateMessage(String id, Message message) {
        return null;
    }

    @Override
    public Message deleteMessage(String id) {
        return null;
    }
}
