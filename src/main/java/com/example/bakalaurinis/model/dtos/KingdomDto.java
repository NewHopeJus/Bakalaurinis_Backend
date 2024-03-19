package com.example.bakalaurinis.model.dtos;

import com.example.bakalaurinis.model.Kingdom;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KingdomDto {
    private Long id;
    private String name;
    private String img;
    private Boolean isOpened;

    public KingdomDto(Kingdom k, Boolean isOpened) {
        this.id = k.getId();
        this.name = k.getName();
        this.img = k.getImg();
        this.isOpened = isOpened;
    }
}
