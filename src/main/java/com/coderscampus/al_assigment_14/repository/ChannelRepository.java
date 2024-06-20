package com.coderscampus.al_assigment_14.repository;

import com.coderscampus.al_assigment_14.domain.Channel;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class ChannelRepository {
    private final Map<Long, Channel> channelMap = new ConcurrentHashMap<>();
    private final MessageRepository messageRepository;

    public ChannelRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Channel save(Channel channel) {
        if (channel.getId() == null) {
            channel.setId(generateId());
        }
        channelMap.put(channel.getId(), channel);
        return channel;
    }
    private long idGenerator = 1;
    private synchronized long generateId() {
        return idGenerator++;
    }

    public Channel findById(long id) {
        return channelMap.get(id);
    }

    public List<Channel> findAll() {
        return new ArrayList<>(channelMap.values());
    }
}


