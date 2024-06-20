package com.coderscampus.al_assigment_14.web;

import com.coderscampus.al_assigment_14.domain.Message;
import com.coderscampus.al_assigment_14.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    private final MessageService messageService;
    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/messages/{channelId}")
    public List<Message> getMessage(@PathVariable Long channelId) {
        List<Message> messages = messageService.getByChanneId(channelId);
        return messages;

    }
    @PostMapping("/messages")
    public Message postMessage(@RequestBody Message message) {
        messageService.saveMessage(message);
        return message;
    }
}
