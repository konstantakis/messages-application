package com.konstantakis.messages.service;

import com.konstantakis.messages.model.Message;
import com.konstantakis.messages.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 * add
 *  javadoc
 *  implementation
 *  tests
 */
@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    @Override
    public Message createMessage(Message message) {
        return null;
    }

    @Override
    public List<Message> getAllMessages() {
        return null;
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
