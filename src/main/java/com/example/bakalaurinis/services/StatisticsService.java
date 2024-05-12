package com.example.bakalaurinis.services;

import com.example.bakalaurinis.model.LevelStatistics;
import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.model.dtos.LevelStatisticsDto;
import com.example.bakalaurinis.model.dtos.StatisticsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticsService {
    private UserService userService;

    @Autowired
    public StatisticsService( UserService userService) {
        this.userService = userService;
    }
        public Optional<StatisticsResponse> getStatisticsForUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); //gaunam username vartotojo prisijungusio
        User user = userService.findUserByUsername(username);
        List<LevelStatistics> levelStatistics = user.getLevelStatistics();
        Integer totalAnswered = 0;
        Integer correctlyAnswered = 0;
        List<LevelStatisticsDto> levelStatisticsDtos = new ArrayList<>();
        for (LevelStatistics l: levelStatistics){
            totalAnswered+= l.getTotalAnswered();
            correctlyAnswered+= l.getCorrectlyAnswered();
            levelStatisticsDtos.add(new LevelStatisticsDto(l.getLevelName(),
                    l.getCorrectlyAnswered(), l.getTotalAnswered()));
        }

        return Optional.of(new StatisticsResponse(correctlyAnswered, totalAnswered,
                levelStatisticsDtos));
    }

}
