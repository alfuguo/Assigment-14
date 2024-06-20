package com.coderscampus.al_assigment_14.repository;

import java.util.concurrent.CopyOnWriteArrayList;
import com.coderscampus.al_assigment_14.domain.Message;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class MessageRepository {
    private final List<Message> messages = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Message save(Message message) {
        if (message.getId() == null) {
            message.setId(idGenerator.incrementAndGet());
        }
        messages.add(message);
        return message;
    }

    public List<Message> findByChannelId(Long channelId) {
        return messages.stream()
                .filter(m -> m.getChannelId().equals(channelId))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        messages.removeIf(message -> message.getId().equals(id));
    }
}

