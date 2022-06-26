package com.konstantakis.messages.service;

import com.konstantakis.messages.model.Message;

import java.util.List;

/**
 * Message service
 */
public interface MessageService {

    /**
     * Method to create a new message
     * @param message message to create
     * @return message that was created
     */
    Message createMessage(Message message);

    /**
     * Method to return all the existing messages
     * @return list of all the existing messages
     */
    List<Message> getAllMessages();

    /**
     * Method to return an existing message with specific id
     * @param id id of the message
     * @return message with the specific id
     */
    Message getMessage(Long id);

    /**
     * Method to update an existing message with specific id
     * @param id id of the message
     * @param message message to edit
     * @return message that was edited
     */
    Message updateMessage(String id, Message message);

    /**
     * Method to delete an existing message with specific id
     * @param id id of the message
     * @return message that was deleted
     */
    Message deleteMessage(String id);


}
