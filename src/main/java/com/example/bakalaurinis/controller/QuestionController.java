package com.example.bakalaurinis.controller;

import com.example.bakalaurinis.model.Question;
import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.services.QuestionService;
import com.example.bakalaurinis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    public Optional<Question> getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/all")
    public Collection<Question> getAllUsers() {
        return questionService.findAll();
    }
    @PostMapping("/add")
    public Question addQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
    }
}
