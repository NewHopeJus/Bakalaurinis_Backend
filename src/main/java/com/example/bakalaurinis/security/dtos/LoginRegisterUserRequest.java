package com.example.bakalaurinis.security.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRegisterUserRequest {
    String username;
    String password;
}
