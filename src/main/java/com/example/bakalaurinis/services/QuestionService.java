package com.example.bakalaurinis.services;

import com.example.bakalaurinis.model.Option;
import com.example.bakalaurinis.model.Question;
import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.model.dtos.AnswerSubmitRequest;
import com.example.bakalaurinis.model.dtos.AnswerSubmitResponse;
import com.example.bakalaurinis.repository.QuestionRepository;
import com.example.bakalaurinis.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public Collection<Question> findAll() {
        return questionRepository.findAll();
    }

    @Transactional
    public Question saveQuestion(Question question) {
        for (Option option : question.getOptions()) {
            option.setQuestion(question);
        }
        return questionRepository.save(question);
    }

    public List<Question> saveQuestions(List<Question> questions) {
        for (Question question : questions) {
            for (Option option : question.getOptions()) {
                option.setQuestion(question);
            }

        }
        return questionRepository.saveAll(questions);
    }

    public boolean hasQuestions() { //nes bootstrape reikia patikrinti ar yra klausimu, jei ne tai uzkrauti
        return questionRepository.count() > 0;
    }


    //Sitoj vietoj kai turesiu useri reikes prideti userio id ir tikrina ar neatsakes
    public Optional<Question> getQuestionByLevelAndTopic(String level, String topic) {
        return questionRepository.findTopByQuestionLevelAndQuestionTopic(level, topic);
    }

    @Transactional
    public AnswerSubmitResponse submitAnswer(AnswerSubmitRequest answerSubmitRequest) {

        Question question = questionRepository.findById(answerSubmitRequest.getQuestionId()).orElseThrow(() -> new NoSuchElementException("Question not found"));

        boolean correctlyAnswered = false;

        AnswerSubmitResponse answerSubmitResponse = new AnswerSubmitResponse();
        List<Option> options = question.getOptions();
        for (Option option : options) {
            if (option.getId().equals(answerSubmitRequest.getSelectedOptionId())) {
                if (option.getIsCorrect()) {
                    correctlyAnswered = true;
                    answerSubmitResponse.setAnswerCorrect(true);
                }
                else {
                    answerSubmitResponse.setAnswerCorrect(false);
                }
                break;
            }
        }



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); //gaunam username vartotojo prisijungusio
        User user = userRepository.findByUsername(username);
        user.setUserExperience(user.getUserExperience() + question.getExperience());
        if(correctlyAnswered){
            user.setUserCoins(user.getUserCoins() + question.getCoins());
        }
        userRepository.save(user);


        return answerSubmitResponse;
    }


}
