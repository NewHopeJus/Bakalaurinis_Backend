package com.example.bakalaurinis.security.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRegisterUserRequest {
    private String username;
    private String password;
}
