package com.example.bakalaurinis.services;

import com.example.bakalaurinis.security.dtos.LoginRegisterUserRequest;
import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// @Autowired is used to inject the UserRepository dependency into the UserService class.
@Service
public class UserService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService( UserRepository userRepository, PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


//    public User saveUser(User user ){
//       List<LevelStatistics> levelStatistics = user.getLevelStatistics();
//        for (LevelStatistics level: levelStatistics) {
//            level.setUser(user);
//            List<TopicStatistics> topicStatistics = level.getTopicStatistics();
//            for (TopicStatistics topic: topicStatistics){
//                topic.setLevelStatistics(level);
//            }
//        }
//        return userRepository.save(user);
//    }


    public User registerUser(LoginRegisterUserRequest input){
        User user = new User(input.getUsername(), passwordEncoder.encode(input.getPassword()));
//        user.setUserCoins(0);
//        user.setUserExperience(0);


//        List<LevelStatistics> levelStatistics =
//
//                user.getLevelStatistics();
//        for (LevelStatistics level: levelStatistics) {
//            level.setUser(user);
//            List<TopicStatistics> topicStatistics = level.getTopicStatistics();
//            for (TopicStatistics topic: topicStatistics){
//                topic.setLevelStatistics(level);
//            }
//        }
        return userRepository.save(user);
    }

    public User authenticate(LoginRegisterUserRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername());
    }
}
