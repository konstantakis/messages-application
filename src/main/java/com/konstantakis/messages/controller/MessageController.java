package com.konstantakis.messages.controller;

import com.konstantakis.messages.model.Message;
import com.konstantakis.messages.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


/**
 * Message API controller
 */
@RestController
@AllArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private MessageService messageService;

    /**
     * Post endpoint to create a new message
     * @param message new message to create
     * @return message that was created
     */
    @ResponseStatus(HttpStatus.CREATED)
    @Validated
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
    public Message postMessages(@Valid @RequestBody Message message) {
        return messageService.createMessage(message);
    }

    /**
     * Get endpoint to retrieve all the existing messages
     * @return message list of existing messages
     */
    @GetMapping()
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    /**
     * Get endpoint to retrieve an existing message
     * @param id of the message to retrieve
     * @return the existing message with id
     */
    @GetMapping("/{id}")
    public Message getMessage(@PathVariable("id") Long id) {
        return messageService.getMessage(id);
    }

    /**
     * Put endpoint to edit an existing message
     * @param id of the message to edit
     * @return the message that was edited
     */
    @PutMapping("/{id}")
    public Message putMessage(@PathVariable("id") Integer id) {
        return null;
    }

    /**
     * Delete endpoint to remove an existing message
     * @param id of the message to remove
     * @return the message that was removed
     */
    @DeleteMapping("/{id}")
    public Message deleteMessage(@PathVariable("id") Integer id) {
        return null;
    }
}
