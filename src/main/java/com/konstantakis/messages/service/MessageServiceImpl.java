package com.konstantakis.messages.service;

import com.konstantakis.messages.exception.MessageNotFoundException;
import com.konstantakis.messages.model.Message;
import com.konstantakis.messages.model.dto.MessageDTO;
import com.konstantakis.messages.repository.MessageRepository;
import com.konstantakis.messages.service.mapstruck.MessagesMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@Transactional
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
    public Message getMessage(Long id) {
        MessageDTO messageDTO = messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        return messagesMapper.messageDTOToMessage(messageDTO);
    }

    @Override
    public Message updateMessage(Long id, Message message) {
        MessageDTO existingMessageDTO = messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        MessageDTO newMessageDTO = messagesMapper.messageToMessageDTO(message);
        existingMessageDTO.setContent(newMessageDTO.getContent());
        existingMessageDTO.setChangedOn(LocalDate.now());
        return messagesMapper.messageDTOToMessage(messageRepository.save(existingMessageDTO));
    }

    @Override
    public Message deleteMessage(Long id) {
        MessageDTO existingMessageDTO = messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        messageRepository.deleteById(id);
        return messagesMapper.messageDTOToMessage(existingMessageDTO);
    }
}
