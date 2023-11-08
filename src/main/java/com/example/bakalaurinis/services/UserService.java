package com.example.bakalaurinis.services;

import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

// @Autowired is used to inject the UserRepository dependency into the UserService class.
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(Long id ){
        return userRepository.findById(id);
    }

    public Collection<User> findAll(){
        return userRepository.findAll();
    }
    public User saveUser(User user ){
        return userRepository.save(user);
    }
}
