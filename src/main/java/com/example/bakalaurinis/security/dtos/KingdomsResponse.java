package com.example.bakalaurinis.security.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class KingdomsResponse {
    private List<KingdomDto> openedKingdoms;
    private List<KingdomDto> closedKingdoms;

    public KingdomsResponse() {
        this.openedKingdoms = new ArrayList<>();
        this.closedKingdoms = new ArrayList<>();
    }
}
