package com.example.bakalaurinis.repository;

import com.example.bakalaurinis.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    //find first question that matches level and topic
    Optional<Question> findTopByQuestionLevelAndQuestionTopic(String level, String topic);


}
