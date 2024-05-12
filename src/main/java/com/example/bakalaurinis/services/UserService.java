package com.example.bakalaurinis.services;

import com.example.bakalaurinis.exceptions.CustomValidationException;
import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.model.dtos.AccountDeleteRequest;
import com.example.bakalaurinis.model.dtos.PasswordChangeRequest;
import com.example.bakalaurinis.repository.UserRepository;
import com.example.bakalaurinis.security.dtos.LoginRegisterUserRequest;
import com.example.bakalaurinis.security.dtos.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

// @Autowired is used to inject the UserRepository dependency into the UserService class.
@Service
public class UserService {
    private final UserRepository userRepository;


    private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;



    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;

    }


    public User registerUser(LoginRegisterUserRequest input) {
        User user = new User(input.getUsername(), passwordEncoder.encode(input.getPassword()));
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

    public Optional<UserInfoResponse> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); //gaunam username vartotojo prisijungusio
        User user = userRepository.findByUsername(username);
        return Optional.of(new UserInfoResponse(user.getUserExperience(), user.getUserCoins(),
                user.getUsername()));
    }


    public User updateUsername(LoginRegisterUserRequest loginRegisterUserRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); //gaunam username vartotojo prisijungusio
        User user = userRepository.findByUsername(username);
        if (user!=null && passwordEncoder.matches(loginRegisterUserRequest.getPassword(), user.getPassword())) {
            user.setUsername(loginRegisterUserRequest.getUsername());
            return userRepository.save(user);

        } else {
            throw new CustomValidationException("Password validation failed or user not found.");
        }

    }

    public User updatePassword(PasswordChangeRequest passwordChangeRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); //gaunam username vartotojo prisijungusio
        User user = userRepository.findByUsername(username);
        if (user!=null && passwordEncoder.matches(passwordChangeRequest.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
            return userRepository.save(user);

        } else {
            throw new CustomValidationException("Password validation failed or user not found.");
        }

    }

    public void deleteUser(AccountDeleteRequest accountDeleteRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); //gaunam username vartotojo prisijungusio
        User user = userRepository.findByUsername(username);
        if (user!=null && passwordEncoder.matches(accountDeleteRequest.getPsw(), user.getPassword())) {
            user.getOpenedKingdoms().clear();
             userRepository.delete(user);

        } else {
            throw new CustomValidationException("User not found.");
        }
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }
}
