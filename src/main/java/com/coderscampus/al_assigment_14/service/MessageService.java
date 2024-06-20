package com.coderscampus.al_assigment_14.service;

import com.coderscampus.al_assigment_14.domain.Message;
import com.coderscampus.al_assigment_14.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(Message message) {
        messageRepository.save(message);
        return message;
    }
    public List<Message> getByChanneId(Long channeId) {
        return messageRepository.findByChannelId(channeId);
    }
}
