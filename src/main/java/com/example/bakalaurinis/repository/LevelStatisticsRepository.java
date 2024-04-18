package com.example.bakalaurinis.repository;

import com.example.bakalaurinis.model.LevelStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelStatisticsRepository extends JpaRepository<LevelStatistics, Long> {
}
