package com.example.bakalaurinis.controller;

import com.example.bakalaurinis.model.dtos.RankingDto;
import com.example.bakalaurinis.services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
public class RankingController {
    private RankingService rankingService;
    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("")
    public ResponseEntity<?> getRankingList() {
        List<RankingDto> rankingDto = rankingService.getRankingList();
        if(!rankingDto.isEmpty()){
            return ResponseEntity.ok(rankingDto);

        }
        return ResponseEntity.badRequest().body("Top performers not found");
    }
}
