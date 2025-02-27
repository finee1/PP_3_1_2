package org.kata.springbootapp.controller;

import org.kata.springbootapp.model.User;
import org.kata.springbootapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> allUsers = userService.listUsers();
        model.addAttribute("allUsers", allUsers);
        return "allUsers";
    }

    @RequestMapping("addNewUser")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "addNewUser";
    }

    @RequestMapping("saveUser")
    public String addNewUser(@ModelAttribute("newUser") User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @RequestMapping("updateUser")
    public String updateUser(@RequestParam("userId") int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("newUser", user);
        return "addNewUser";
    }

    @RequestMapping("deleteUser")
    public String deleteUser(@RequestParam("userId") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
