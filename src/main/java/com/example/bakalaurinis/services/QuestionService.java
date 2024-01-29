package com.example.bakalaurinis.services;

import com.example.bakalaurinis.model.Option;
import com.example.bakalaurinis.model.Question;
import com.example.bakalaurinis.model.QuestionType;
import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.repository.QuestionRepository;
import com.example.bakalaurinis.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService( QuestionRepository questionRepository ) {
        this.questionRepository = questionRepository;
    }

    public Optional<Question> getQuestionById(Long id ){
        return questionRepository.findById(id);
    }

    public Collection<Question> findAll(){
        return questionRepository.findAll();
    }

    @Transactional
    public Question saveQuestion(Question question ){
        for (Option option : question.getOptions()) {
            option.setQuestion(question);
        }
        return questionRepository.save(question);
    }
    public List<Question> saveQuestions(List<Question> questions){
        for(Question question: questions){
            for (Option option : question.getOptions()) {
                option.setQuestion(question);
            }

        }
        return questionRepository.saveAll(questions);
    }
}
