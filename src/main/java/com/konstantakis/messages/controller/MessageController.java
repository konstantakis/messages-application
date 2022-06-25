package com.konstantakis.messages.controller;

import com.konstantakis.messages.model.Message;
import com.konstantakis.messages.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * TODO
 * add
 *  javadoc
 *  implementation
 *  tests
 */
@RestController
@AllArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private MessageService messageService;

    @PostMapping()
    public Message postMessages() {
        return null;
    }

    @GetMapping()
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public Message getMessage(@PathVariable("id") Integer id) {
        return null;
    }

    @PutMapping("/{id}")
    public Message putMessage(@PathVariable("id") Integer id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public Message deleteMessage(@PathVariable("id") Integer id) {
        return null;
    }
}
