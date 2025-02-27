package org.kata.springbootapp.controller;

import org.kata.springbootapp.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String defaultPage() {
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String getUserInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "user";
    }
}
