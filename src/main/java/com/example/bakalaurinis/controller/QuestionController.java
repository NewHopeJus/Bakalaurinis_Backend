package com.example.bakalaurinis.controller;

import com.example.bakalaurinis.model.Question;
import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.services.QuestionService;
import com.example.bakalaurinis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //Response entity is a wrapper that can contain both the data (Quesion)
    // and HTTP status information
    //If the Opional<Question> is empty it creates a Response entity
    // with 404 Not found status
    @GetMapping("/{id}")
    public ResponseEntity <Question> getQuestionById(@PathVariable Long id) {
        Optional<Question> question = questionService.getQuestionById(id);
        if (question.isPresent()) {

            //returnina http status 200 ir questiona
            return ResponseEntity.ok(question.get());
        }
        //jei nesurado returnina 404 not found
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/{level}/{topic}")
    public ResponseEntity<Question> getQuestionByLevelAndTopic(@PathVariable String level, @PathVariable String topic) {
      Optional<Question> questions = questionService.getQuestionByLevelAndTopic(level, topic);
      if(questions.isPresent()){
          return ResponseEntity.ok(questions.get());
      }
      return ResponseEntity.notFound().build();

    }


    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.saveQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestion);
    }
}
