package com.example.bakalaurinis.repository;

import com.example.bakalaurinis.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    //find first question that matches level and topic
 //Optional<Question> findTopByQuestionLevelAndQuestionTopic(String level, String topic);

    @Query(value = "SELECT * from question where question_level = ? and question_topic=? and question.id NOT IN" +
            "(SELECT answered_question_id from user_answer where answering_user_id = ? and correctly_answered = true) ORDER BY rand() limit 1", nativeQuery = true)
    Optional<Question> getQuestionForUserByLevelAndTopic(String level, String topic, Long userId);

}
