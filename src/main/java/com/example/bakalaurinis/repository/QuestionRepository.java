package com.example.bakalaurinis.repository;

import com.example.bakalaurinis.model.Question;
import com.example.bakalaurinis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
