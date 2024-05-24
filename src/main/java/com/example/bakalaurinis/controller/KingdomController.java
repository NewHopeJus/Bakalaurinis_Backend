package com.example.bakalaurinis.controller;

import com.example.bakalaurinis.model.dtos.KingdomsResponse;
import com.example.bakalaurinis.services.KingdomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kingdoms")
public class KingdomController {
    private final KingdomService kingdomService;

    @Autowired
    public KingdomController(KingdomService kingdomService) {
        this.kingdomService = kingdomService;
    }

    @GetMapping("/getKingdoms")
    public ResponseEntity<?> getKingdomsForUser() {
        KingdomsResponse kingdoms = kingdomService.getKingdomsForUser();
        if (kingdoms != null) {
            return ResponseEntity.ok(kingdoms);
        }
        return ResponseEntity.badRequest().body("Kingdoms not found");
    }
}
