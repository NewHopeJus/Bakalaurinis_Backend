package com.example.bakalaurinis.services;

import com.example.bakalaurinis.model.LevelStatistics;
import com.example.bakalaurinis.model.TopicStatistics;
import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
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
       List<LevelStatistics> levelStatistics = user.getLevelStatistics();
        for (LevelStatistics level: levelStatistics) {
            level.setUser(user);
            List<TopicStatistics> topicStatistics = level.getTopicStatistics();
            for (TopicStatistics topic: topicStatistics){
                topic.setLevelStatistics(level);
            }
        }
        return userRepository.save(user);
    }
}
