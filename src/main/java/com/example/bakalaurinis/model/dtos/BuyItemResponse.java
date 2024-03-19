package com.example.bakalaurinis.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BuyItemResponse {
    private Boolean success;
    private String message;
}
