package com.coderscampus.al_assigment_14.web;

import ch.qos.logback.core.model.Model;
import com.coderscampus.al_assigment_14.domain.User;
import com.coderscampus.al_assigment_14.repository.ChannelRepository;
import com.coderscampus.al_assigment_14.repository.UserRepository;
import com.coderscampus.al_assigment_14.service.ChannelService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {
    private final UserRepository userRepository;

    private final ChannelRepository channelRepository;
    private final ChannelService channelService;

    @Autowired
    public WelcomeController(UserRepository userRepository, ChannelRepository channelRepository, ChannelService channelService) {
        this.userRepository = userRepository;
        this.channelRepository = channelRepository;
        this.channelService = channelService;
    }


    @GetMapping("/")
    public String getWelcomeMessage(HttpSession session, ModelMap model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null) {
            model.addAttribute("channels", channelService.getAllChannels());
            return "welcome";
        }
        return "redirect:/channels";

    }
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, HttpSession session) {
        User newUser = new User();
        userRepository.addUser(newUser);
        session.setAttribute("currentUser", newUser);
        return "redirect:/channels";
    }

    @GetMapping("/welcomepage")
    public String welcomepage() {
        return "welcomepage";
    }
}
