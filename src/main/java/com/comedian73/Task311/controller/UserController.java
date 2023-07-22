package com.comedian73.Task311.controller;

import com.comedian73.Task311.model.User;
import com.comedian73.Task311.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/user")
    public String user(Model model) {

        Iterable<User> list = userRepository.findAll();
        model.addAttribute("list", list);
        return "user";
    }

    @PostMapping(value = "/user")
    public String button(@RequestParam(name = "name") String name,
                         @RequestParam(name = "last_name") String lastName,
                         @RequestParam(name = "email") String email, Model model) {

        userRepository.save(new User(name, lastName, email));
        return this.user(model);
    }

    @PostMapping (value = "/delete")
    public String deleteUser(@RequestParam(name = "del") long id) {

        userRepository.deleteById(id);
        return "delete";
    }

    @GetMapping(value = "/edit")
    public String edit (@RequestParam(name = "edit") long id, Model model) {

        User user = userRepository.findById(id).orElse(new User());
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "/edit")
    public String saveEdit (@RequestParam(name = "id") long id,
                            @RequestParam(name = "name") String name,
                            @RequestParam(name = "last_name") String lastName,
                            @RequestParam(name = "email") String email, Model model) {

        User user = userRepository.findById(id).orElse(new User());
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        userRepository.save(user);
        return this.user(model);
    }
}
