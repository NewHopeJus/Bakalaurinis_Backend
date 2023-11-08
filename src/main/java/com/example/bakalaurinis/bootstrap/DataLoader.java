package com.example.bakalaurinis.bootstrap;

import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.model.UserRole;
import com.example.bakalaurinis.repository.UserRepository;
import com.example.bakalaurinis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner  {
    UserRepository userRepository;
    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

//        User user = new User("Justyna", "just2@gmail.com", "123", UserRole.USER);
//        userRepository.save(user);
    }
}
