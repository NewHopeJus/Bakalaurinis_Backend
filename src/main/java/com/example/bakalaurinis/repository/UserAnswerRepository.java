package com.example.bakalaurinis.repository;

import com.example.bakalaurinis.model.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
}
