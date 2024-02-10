package com.example.bakalaurinis.services;

import com.example.bakalaurinis.model.Option;
import com.example.bakalaurinis.model.Question;
import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.model.UserAnswer;
import com.example.bakalaurinis.model.dtos.AnswerSubmitRequest;
import com.example.bakalaurinis.model.dtos.AnswerSubmitResponse;
import com.example.bakalaurinis.repository.QuestionRepository;
import com.example.bakalaurinis.repository.UserAnswerRepository;
import com.example.bakalaurinis.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private UserAnswerRepository userAnswerRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository,
                           UserAnswerRepository userAnswerRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.userAnswerRepository = userAnswerRepository;
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

    public Optional<Question> getQuestionForUserByLevelAndTopic(String level, String topic) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); //gaunam username vartotojo prisijungusio
        User user = userRepository.findByUsername(username);
        return questionRepository.getQuestionForUserByLevelAndTopic(level, topic, user.getId());
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
                } else {
                    answerSubmitResponse.setAnswerCorrect(false);
                }
                break;
            }
        }


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); //gaunam username vartotojo prisijungusio
        User user = userRepository.findByUsername(username);
        Integer experience = user.getUserExperience() + question.getExperience();
        user.setUserExperience(experience);
        Integer coins = user.getUserCoins() + question.getCoins();
        if (correctlyAnswered) {
            user.setUserCoins(coins);
            //user.addCorrectlyAnsweredQuestion(question.getId());
        }
        userRepository.save(user);

        answerSubmitResponse.setUpdatedCoins(coins);
        answerSubmitResponse.setUpdatedExperience(experience);
        answerSubmitResponse.setCorrectAnswerText(question.getCorrectOptionText());

        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setAnsweredQuestion(question);
        userAnswer.setAnsweringUser(user);
        userAnswer.setCorrectlyAnswered(correctlyAnswered);
        userAnswer.setDateTimeAnswered(LocalDateTime.now());
        userAnswerRepository.save(userAnswer);


        return answerSubmitResponse;
    }


}
