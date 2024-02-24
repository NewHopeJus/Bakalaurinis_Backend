package com.example.bakalaurinis.controller;

import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.security.JwtUtil;
import com.example.bakalaurinis.security.dtos.LoginRegisterUserRequest;
import com.example.bakalaurinis.security.dtos.LoginResponse;
import com.example.bakalaurinis.security.dtos.UserInfoResponse;
import com.example.bakalaurinis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * The controller handles the HTTP requests and responses and manages
 * the flow of data between the client(e.g., a web or mobile app)
 * and the repository
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    private UserDetailsService userDetailsService;

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
        try{
            User authenticatedUser = userService.authenticate(loginRegisterUserRequest);

            String jwtToken = jwtUtil.generateToken(authenticatedUser);
            return ResponseEntity.ok(new LoginResponse(jwtToken));
        }
        catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/userInfo")
    public ResponseEntity<?> getUserInfo(){
        Optional<UserInfoResponse> userInfoResponse = userService.getUserInfo();
        if(userInfoResponse.isPresent()){
            return ResponseEntity.ok(userInfoResponse.get());
        }
        return ResponseEntity.badRequest().body("User info not found");

    }
}
