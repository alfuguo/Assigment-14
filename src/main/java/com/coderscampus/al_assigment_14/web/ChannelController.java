package com.coderscampus.al_assigment_14.web;

import com.coderscampus.al_assigment_14.domain.Channel;
import com.coderscampus.al_assigment_14.domain.Message;
import com.coderscampus.al_assigment_14.domain.User;
import com.coderscampus.al_assigment_14.repository.ChannelRepository;
import com.coderscampus.al_assigment_14.repository.UserRepository;
import com.coderscampus.al_assigment_14.service.ChannelService;
import com.coderscampus.al_assigment_14.service.MessageService;
import com.coderscampus.al_assigment_14.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/channels")
public class ChannelController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/{channelId}")
    public String channel(@PathVariable Long channelId, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/";
        }
        User user = userService.getUserById(userId);
        Channel channel = channelService.getChannelById(channelId);
        model.addAttribute("user", user);
        model.addAttribute("channel", channel);
        return "channel";
    }

    @PostMapping("/{channelId}/messages")
    @ResponseBody
    public ResponseEntity<Message> sendMessage(@PathVariable Long channelId, @RequestBody Message message, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        User user = userService.getUserById(userId);
        message.setUser(user);
        message.setChannelId(channelId);
        Message savedMessage = messageService.createMessage(message);
        return ResponseEntity.ok(savedMessage);
    }

    @GetMapping("/{channelId}/messages")
    @ResponseBody
    public ResponseEntity<List<Message>> getMessages(@PathVariable Long channelId) {
        List<Message> messages = messageService.getMessagesByChannelId(channelId);
        return ResponseEntity.ok(messages);
    }
}