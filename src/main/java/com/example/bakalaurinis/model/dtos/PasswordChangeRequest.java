package com.example.bakalaurinis.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeRequest {
    String oldPassword;
    String newPassword;
}
