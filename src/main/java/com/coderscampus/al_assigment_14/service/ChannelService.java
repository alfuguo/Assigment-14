package com.coderscampus.al_assigment_14.service;

import com.coderscampus.al_assigment_14.domain.Channel;
import com.coderscampus.al_assigment_14.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {
    private final ChannelRepository channelRepository;
    @Autowired
    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public Channel createChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public Channel saveChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public List<Channel> getAllChannels() {
        return channelRepository.findAll();

    }
    public Optional<Channel> getChannelById(Long id) {
        return channelRepository.findById(id);
    }
}
