package com.example.bakalaurinis.controller;

import com.example.bakalaurinis.security.dtos.LoginRegisterUserRequest;
import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.security.JwtUtil;
import com.example.bakalaurinis.security.dtos.LoginResponse;
import com.example.bakalaurinis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/{id}")
//    public Optional<User> getUserById(@PathVariable Long id) {
//        return userService.getUserById(id);
//    }

//    @GetMapping("/all")
//    public Collection<User> getAllUsers() {
//        return userService.findAll();
//    }
//    @PostMapping("/add")
//    public User addUser(@RequestBody User user) {
//        return userService.saveUser(user);
//    }

//    @PostMapping("/register")
//    public User addUser(@RequestBody User user) {
//        return userService.saveUser(user);
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity<?> loginAndGetToken(@RequestBody LoginRequest loginRequest) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
//            );
//        }
//        catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
//        }
//
//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(loginRequest.getEmail());
//
//        final String jwt = jwtService.generateToken(userDetails);
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//
//    }

    @PostMapping("/register")
//@PreAuthorize("permitAll()")

    public ResponseEntity<User> register(@RequestBody LoginRegisterUserRequest loginRegisterUserRequest) {
        User registeredUser = userService.registerUser(loginRegisterUserRequest);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRegisterUserRequest loginRegisterUserRequest) {
        User authenticatedUser = userService.authenticate(loginRegisterUserRequest);

        String jwtToken = jwtUtil.generateToken(authenticatedUser);
        return ResponseEntity.ok(new LoginResponse(jwtToken));
    }


}
