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

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

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

        User user = userRepository.findByUsername(input.getUsername());

        if (user != null && user.getIsBlocked()) {
            throw new IllegalStateException("User account is blocked.");
        }

        return user;

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
        if (user != null && passwordEncoder.matches(loginRegisterUserRequest.getPassword(), user.getPassword())) {
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
        if (user != null && passwordEncoder.matches(passwordChangeRequest.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
            return userRepository.save(user);

        } else {
            throw new CustomValidationException("Password validation failed or user not found.");
        }
    }

    public User changePassword(Long userId, String newPassword) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(user);
        } else {
            throw new CustomValidationException("User with ID " + userId + " not found.");
        }
    }

    public void deleteUser(AccountDeleteRequest accountDeleteRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); //gaunam username vartotojo prisijungusio
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(accountDeleteRequest.getPsw(), user.getPassword())) {
            user.getOpenedKingdoms().clear();
            userRepository.delete(user);

        } else {
            throw new CustomValidationException("User not found.");
        }
    }

    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().getOpenedKingdoms().clear();
            userRepository.delete(user.get());
        } else {
            throw new CustomValidationException("User not found.");
        }
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void blockUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomValidationException("User with ID " + userId + " not found."));
        user.setIsBlocked(true);
        userRepository.save(user);
    }
}
