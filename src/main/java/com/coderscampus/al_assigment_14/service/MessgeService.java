package com.coderscampus.al_assigment_14.service;

import com.coderscampus.al_assigment_14.domain.Message;
import com.coderscampus.al_assigment_14.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessgeService {
    private final MessageRepository messageRepository;
    @Autowired
    public MessgeService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message save(Message message) {
        messageRepository.save(message);
        return message;
    }
}
