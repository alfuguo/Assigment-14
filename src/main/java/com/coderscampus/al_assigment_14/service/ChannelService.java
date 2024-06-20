package com.coderscampus.al_assigment_14.service;

import com.coderscampus.al_assigment_14.domain.Channel;
import com.coderscampus.al_assigment_14.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;

    @Autowired
    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
        initializeChannels();
    }
    private void initializeChannels() {
        if (getAllChannels().isEmpty()) {
            Channel general = new Channel(null, "General", new ArrayList<>());
            createChannel(general);

            Channel random = new Channel(null, "Random", new ArrayList<>());
            createChannel(random);

            System.out.println("Initial channels created.");
        }
    }
    public Channel createChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public Channel getChannelById(Long id) {
        return channelRepository.findById(id);
    }

    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

}