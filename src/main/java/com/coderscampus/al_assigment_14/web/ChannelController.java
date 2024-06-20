package com.coderscampus.al_assigment_14.web;

import com.coderscampus.al_assigment_14.domain.Channel;
import com.coderscampus.al_assigment_14.domain.Message;
import com.coderscampus.al_assigment_14.service.ChannelService;
import com.coderscampus.al_assigment_14.service.MessageService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ChannelController {

    private final MessageService messageService;
    private final ChannelService channelService;
    @Autowired
    public ChannelController(MessageService messageService, ChannelService channelService) {
        this.messageService = messageService;
        this.channelService = channelService;
    }



    @GetMapping("/channel")
    public String getChannel() {
        return "welcome";
    }


    @GetMapping("/channel/{channelId}")
        public String getChannelId (@PathVariable Long channelId, ModelMap model) {
        List<Message> message = messageService.getByChanneId(channelId);
        model.addAttribute("message", message);
        Channel channel = channelService.getChannelById(channelId);
        model.addAttribute("channel", channel);
        return "channel";

    }
    @PostMapping ("/channel/create")

    public String postChannel(Channel channel) {
        channelService.saveChannel(channel);
        return "redirect:/channel/" + channel.getId();
    }
}
