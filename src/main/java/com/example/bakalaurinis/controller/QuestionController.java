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

    //Response entity is a wrapper that can contain both the data (Quesion)
    // and HTTP status information
    //If the Opional<Question> is empty it creates a Response entity
    // with 404 Not found status
    @GetMapping("/{id}")
    public ResponseEntity <?> getQuestionById(@PathVariable Long id) {
        Optional<Question> question = questionService.getQuestionById(id);
        if (question.isPresent()) {

            //returnina http status 200 ir questiona
            return ResponseEntity.ok(question.get());
        }
        //jei nesurado returnina 404 not found
        return ResponseEntity.badRequest().body("Question not found");
    }


    @GetMapping("/{level}/{topic}")
    public ResponseEntity<?> getQuestionForUserByLevelAndTopic(@PathVariable String level, @PathVariable String topic) {
      Optional<Question> questions = questionService.getQuestionForUserByLevelAndTopic(level, topic);
      if(questions.isPresent()){
          return ResponseEntity.ok(questions.get());
      }
      return ResponseEntity.badRequest().body("Question not found");

    }


//    @PostMapping("/add")
//    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
//        Question savedQuestion = questionService.saveQuestion(question);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestion);
//    }
//

@PostMapping ( "/answerSubmit")
    public ResponseEntity<?> submitAnswer(@RequestBody AnswerSubmitRequest answerSubmitRequest) {
       try {
           AnswerSubmitResponse question = questionService.submitAnswer(answerSubmitRequest);
               return ResponseEntity.ok(question);
       }
       catch (NoSuchElementException e){
           //jei nesurado returnina 404 not found
           return ResponseEntity.badRequest().body(e.getMessage());
       }

    }


}
