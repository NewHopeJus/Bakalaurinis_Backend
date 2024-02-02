package com.example.bakalaurinis.controller;

import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.repository.UserRepository;
import com.example.bakalaurinis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

/**
 * The controller handles the HTTP requests and responses and manages
 * the flow of data between the client(e.g., a web or mobile app)
 * and the repository
 *
 * */
@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/all")
    public Collection<User> getAllUsers() {
        return userService.findAll();
    }
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
