package com.example.bakalaurinis.security;


import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * User Details Service is used to retrieve users authentication and authorization information
 * Intended to allow you to define how to fetch user details given a username (or email)
 *   Most applications store user information in a custom manner,
 *  such as in a relational database, NoSQL database, or even an external service.
 *  Implementing UserDetailsService allows you to integrate Spring Security with whatever user
 *  storage solution
 *  your application uses.
 *
 * */

@Service
public class MyUserDetailsService implements UserDetailsService {
    UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new UserDetailsModel(user);
    }
}
