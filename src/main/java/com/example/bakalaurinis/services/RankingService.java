package com.example.bakalaurinis.services;

import com.example.bakalaurinis.model.UserAnswer;
import com.example.bakalaurinis.model.dtos.RankingDto;
import com.example.bakalaurinis.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankingService {
    private RankingRepository rankingRepository;

    @Autowired
    public RankingService(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    public List<RankingDto> getRankingList() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneWeekAgo = now.minusDays(7);

        rankingRepository.deleteOlderThanWeek(oneWeekAgo);

        List<Object[]> results = rankingRepository.findTopPerformers();
        return results.stream()
                .map(result -> new RankingDto((String) result[0], ((Number) result[1]).intValue()))
                .collect(Collectors.toList());
    }

    public UserAnswer saveUserAnswer(UserAnswer userAnswer){
        return rankingRepository.save(userAnswer);
    }

}
