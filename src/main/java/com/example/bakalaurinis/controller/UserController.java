package com.example.bakalaurinis.controller;

import com.example.bakalaurinis.exceptions.CustomValidationException;
import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.model.dtos.AccountDeleteRequest;
import com.example.bakalaurinis.model.dtos.PasswordChangeRequest;
import com.example.bakalaurinis.security.JwtUtil;
import com.example.bakalaurinis.security.dtos.LoginRegisterUserRequest;
import com.example.bakalaurinis.security.dtos.LoginResponse;
import com.example.bakalaurinis.security.dtos.UserInfoResponse;
import com.example.bakalaurinis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserDetailsService userDetailsService;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager,
                          JwtUtil jwtService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody LoginRegisterUserRequest loginRegisterUserRequest) {
        User registeredUser = userService.registerUser(loginRegisterUserRequest);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRegisterUserRequest loginRegisterUserRequest) {
        try {
            User authenticatedUser = userService.authenticate(loginRegisterUserRequest);

            if (authenticatedUser == null) {
                throw new UsernameNotFoundException("User not found.");
            }
            String jwtToken = jwtUtil.generateToken(authenticatedUser);
            return ResponseEntity.ok(new LoginResponse(jwtToken));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Invalid username or password.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/userInfo")
    public ResponseEntity<?> getUserInfo() {
        Optional<UserInfoResponse> userInfoResponse = userService.getUserInfo();
        if (userInfoResponse.isPresent()) {
            return ResponseEntity.ok(userInfoResponse.get());
        }
        return ResponseEntity.badRequest().body("User info not found");

    }

    @PostMapping("/update/username")
    public ResponseEntity<?> updateUsername(@RequestBody LoginRegisterUserRequest loginRegisterUserRequest) {
        try {
            User authenticatedUser = userService.updateUsername(loginRegisterUserRequest);
            String jwtToken = jwtUtil.generateToken(authenticatedUser);
            return ResponseEntity.ok(new LoginResponse(jwtToken));

        } catch (CustomValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/update/password")
    public ResponseEntity<?> updatePassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
        try {
            User authenticatedUser = userService.updatePassword(passwordChangeRequest);
            String jwtToken = jwtUtil.generateToken(authenticatedUser);
            return ResponseEntity.ok(new LoginResponse(jwtToken));

        } catch (CustomValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody AccountDeleteRequest accountDeleteRequest) {
        try {
            userService.deleteUser(accountDeleteRequest);
            return ResponseEntity.ok("User account deleted successfully.");
        } catch (CustomValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
