package com.coderscampus.al_assigment_14.web;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChannelController {
    @GetMapping("/channel")
    public String getCh吧annel() {
        return "redirect:/welcomepage";
    }


    @GetMapping("/channel/{channelId}")
        public String getChannelId (@PathVariable Long channelId) {
            return "channel";

    }
}
