package com.example.bakalaurinis.controller;

import com.example.bakalaurinis.model.dtos.StatisticsResponse;
import com.example.bakalaurinis.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("")
    public ResponseEntity<?> getUserStatistics() {
        Optional<StatisticsResponse> statisticsResponse = statisticsService.getStatisticsForUser();
        if(statisticsResponse.isPresent()){
            return ResponseEntity.ok(statisticsResponse.get());
        }
        return ResponseEntity.badRequest().body("Statistics not found for user");
    }
}
