package com.konstantakis.messages.controller;

import com.konstantakis.messages.model.Message;
import com.konstantakis.messages.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * TODO
 * add
 *  javadoc
 *  implementation
 *  tests
 */
@RestController
@AllArgsConstructor
public class MessageController {

    private MessageService messageService;

    @PostMapping("/messages")
    public Message postMessages() {
        return null;
    }

    @GetMapping("/messages")
    public Message getMessages() {
        return null;
    }

    @GetMapping("/messages/{id}")
    public Message getMessage(@PathVariable("id") Integer id) {
        return null;
    }

    @PutMapping("/messages/{id}")
    public Message putMessage(@PathVariable("id") Integer id) {
        return null;
    }

    @DeleteMapping("/messages/{id}")
    public Message deleteMessage(@PathVariable("id") Integer id) {
        return null;
    }
}