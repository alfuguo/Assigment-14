package com.coderscampus.al_assigment_14.service;

import com.coderscampus.al_assigment_14.domain.Message;
import com.coderscampus.al_assigment_14.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByChannelId(Long channelId) {
        return messageRepository.findByChannelId(channelId);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
