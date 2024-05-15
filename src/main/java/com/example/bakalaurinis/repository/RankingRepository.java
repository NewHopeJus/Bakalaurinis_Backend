package com.example.bakalaurinis.repository;

import com.example.bakalaurinis.model.UserAnswer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RankingRepository extends JpaRepository<UserAnswer, Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user_answer WHERE DATE(date_time_answered) < DATE(?1)", nativeQuery = true)
    int deleteOlderThanWeek(LocalDateTime cutoffDate);

    @Query(value = "SELECT u.username, COUNT(*) AS correct_count " +
            "FROM user_answer a " +
            "JOIN users u ON u.id = a.answering_user_id " +
            "WHERE a.correctly_answered = TRUE " +
            "GROUP BY a.answering_user_id, u.username " +
            "ORDER BY correct_count DESC " +
            "LIMIT 10", nativeQuery = true)
    List<Object[]> findTopPerformers();
}
