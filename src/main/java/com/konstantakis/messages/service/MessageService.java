package com.konstantakis.messages.service;

import com.konstantakis.messages.model.Message;

import java.util.List;

/**
 * TODO
 * add
 *  javadoc
 */
public interface MessageService {

    Message createMessage(Message message);

    List<Message> getAllMessages();

    Message getMessage(String id);

    Message updateMessage(String id, Message message);

    Message deleteMessage(String id);


}
