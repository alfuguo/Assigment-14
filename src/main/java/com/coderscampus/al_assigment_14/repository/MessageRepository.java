package com.coderscampus.al_assigment_14.repository;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import com.coderscampus.al_assigment_14.domain.Message;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MessageRepository{
    private final List<Message> messages;

    public MessageRepository() {
        this.messages = new CopyOnWriteArrayList<>();
    }

    public synchronized void save(Message message) {
        if (message.getId() == null) {
            message.setId(generateId());
        }
        messages.add(message);
    }
    private long idGenerator = 1;
    private synchronized long generateId() {
        return idGenerator++;
    }

    public List<Message> findByChannelId(long channelId) {
        List<Message> messagesByChannelId = new ArrayList<>();
        for (Message message : messages) {
            if (message.getChannelId() == channelId) {
                messagesByChannelId.add(message);
            }
        }
        return messagesByChannelId;
    }
}

