package com.coderscampus.al_assigment_14.repository;

import com.coderscampus.al_assigment_14.domain.Channel;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ChannelRepository {
    private final Map<Long, Channel> channels = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Channel save(Channel channel) {
        if (channel.getId() == null) {
            channel.setId(idGenerator.incrementAndGet());
        }
        channels.put(channel.getId(), channel);
        return channel;
    }

    public Channel findById(Long id) {
        return channels.get(id);
    }

    public List<Channel> findAll() {
        return new ArrayList<>(channels.values());
    }
    public void delete(Channel channel) {
        channels.remove(channel.getId());
    }
}



