package com.example.bakalaurinis.controller;

import com.example.bakalaurinis.model.Question;
import com.example.bakalaurinis.model.dtos.AnswerSubmitRequest;
import com.example.bakalaurinis.model.dtos.AnswerSubmitResponse;
import com.example.bakalaurinis.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        Optional<Question> question = questionService.getQuestionById(id);
        if (question.isPresent()) {
            return ResponseEntity.ok(question.get());
        }
        return ResponseEntity.badRequest().body("Question not found");
    }

    @GetMapping("/{level}/{topic}")
    public ResponseEntity<?> getQuestionForUserByLevelAndTopic(@PathVariable String level, @PathVariable String topic) throws InterruptedException {
        Optional<Question> questions = questionService.getQuestionForUserByLevelAndTopic(level, topic);
        if (questions.isPresent()) {
            return ResponseEntity.ok(questions.get());
        }
        return ResponseEntity.badRequest().body("Question not found");

    }

    @PostMapping("/answerSubmit")
    public ResponseEntity<?> submitAnswer(@RequestBody AnswerSubmitRequest answerSubmitRequest) {
        try {
            AnswerSubmitResponse question = questionService.submitAnswer(answerSubmitRequest);
            return ResponseEntity.ok(question);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
