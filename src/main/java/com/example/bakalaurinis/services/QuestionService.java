package com.example.bakalaurinis.services;

import com.example.bakalaurinis.model.*;
import com.example.bakalaurinis.model.dtos.AnswerSubmitRequest;
import com.example.bakalaurinis.model.dtos.AnswerSubmitResponse;
import com.example.bakalaurinis.repository.QuestionRepository;
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
    private final UserService userService;

    private RankingService rankingService;
    private KingdomService kingdomService;
    private StatisticsService statisticsService;


    @Autowired
    public QuestionService(QuestionRepository questionRepository, UserService userService,
                           RankingService rankingService, KingdomService kingdomService,
                           StatisticsService statisticsService) {
        this.questionRepository = questionRepository;
        this.userService = userService;
        this.rankingService = rankingService;
        this.kingdomService = kingdomService;
        this.statisticsService = statisticsService;
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


    public Optional<Question> getQuestionForUserByLevelAndTopic(String level, String topic) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); //gaunam username vartotojo prisijungusio
        User user = userService.findUserByUsername(username);
        return questionRepository.getQuestionForUserByLevelAndTopic(level, topic, user.getId());
    }

    @Transactional
    public AnswerSubmitResponse submitAnswer(AnswerSubmitRequest answerSubmitRequest) {

        Question question = questionRepository.findById(answerSubmitRequest.getQuestionId()).orElseThrow(() -> new NoSuchElementException("Question not found"));

        boolean correctlyAnswered = false;
        AnswerSubmitResponse answerSubmitResponse = new AnswerSubmitResponse();
        List<Option> options = question.getOptions();
        switch (question.getQuestionType().toString()) {
            case "ONE_ANSWER":
                for (Option option : options) {
                    if (option.getId().equals(answerSubmitRequest.getSelectedOptionId())) {
                        if (option.getIsCorrect()) {
                            correctlyAnswered = true;
                        }
                        break;
                    }
                }
                break;
            case "OPEN_ANSWER":
                String userAnswer = answerSubmitRequest.getUserAnswer();
                if (userAnswer.equalsIgnoreCase(options.get(0).getText())) {
                    correctlyAnswered = true;
                }
                break;

        }

        if (correctlyAnswered) {
            answerSubmitResponse.setAnswerCorrect(true);

        } else {
            answerSubmitResponse.setAnswerCorrect(false);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); //gaunam username vartotojo prisijungusio
        User user = userService.findUserByUsername(username);
        Integer experience = user.getUserExperience() + question.getExperience();
        user.setUserExperience(experience);
        Integer coins = user.getUserCoins() + question.getCoins();

        int openedKingdomsSize = user.getOpenedKingdoms().size();
        if (experience >= 250 && experience <= 500 && openedKingdomsSize < 1) {
            Optional<Kingdom> myCastle = kingdomService.getKingdomById(1L);

            if (myCastle.isPresent()) {
                user.getOpenedKingdoms().add(myCastle.get());
                answerSubmitResponse.setHasOpenedKingdom(true);
                answerSubmitResponse.setOpenedKingdomText("Sveikiname! Surinkote " + experience + " patirties taškų ir nuo Monstro pabaisos išlaisvinote savo Pilį!");
            }
        } else if (experience >= 500 && experience <= 1425 && openedKingdomsSize < 2) {
            Optional<Kingdom> flowerKingdom = kingdomService.getKingdomById(2L);

            if (flowerKingdom.isPresent()) {
                user.getOpenedKingdoms().add(flowerKingdom.get());
                answerSubmitResponse.setHasOpenedKingdom(true);
                answerSubmitResponse.setOpenedKingdomText("Sveikiname! Surinkote " + experience + " patirties taškų ir nuo Monstro pabaisos išlaisvinote Gėlių karalystę!");
            }
        } else if (experience >= 1425 && experience <= 1850 && openedKingdomsSize < 3) {
            Optional<Kingdom> elfKingdom = kingdomService.getKingdomById(3L);

            if (elfKingdom.isPresent()) {
                user.getOpenedKingdoms().add(elfKingdom.get());
                answerSubmitResponse.setHasOpenedKingdom(true);
                answerSubmitResponse.setOpenedKingdomText("Sveikiname! Surinkote " + experience + " patirties taškų ir nuo Monstro pabaisos išlaisvinote Elfų karalystę!");
            }
        } else if (experience >= 1850 && experience <= 3600 && openedKingdomsSize < 4) {
            Optional<Kingdom> mushroomKingdom = kingdomService.getKingdomById(4L);

            if (mushroomKingdom.isPresent()) {
                user.getOpenedKingdoms().add(mushroomKingdom.get());
                answerSubmitResponse.setHasOpenedKingdom(true);
                answerSubmitResponse.setOpenedKingdomText("Sveikiname! Surinkote " + experience + " patirties taškų ir nuo Monstro pabaisos išlaisvinote Grybų karalystę!");
            }
        } else if (experience >= 3600 && experience <= 5700 && openedKingdomsSize < 5) {
            Optional<Kingdom> mushroomKingdom = kingdomService.getKingdomById(5L);

            if (mushroomKingdom.isPresent()) {
                user.getOpenedKingdoms().add(mushroomKingdom.get());
                answerSubmitResponse.setHasOpenedKingdom(true);
                answerSubmitResponse.setOpenedKingdomText("Sveikiname! Surinkote " + experience + " patirties taškų ir nuo Monstro pabaisos išlaisvinote Povandeninę karalystę!");
            }
        } else if (experience >= 5700 && experience <= 8025 && openedKingdomsSize < 6) {
            Optional<Kingdom> mushroomKingdom = kingdomService.getKingdomById(6L);

            if (mushroomKingdom.isPresent()) {
                user.getOpenedKingdoms().add(mushroomKingdom.get());
                answerSubmitResponse.setHasOpenedKingdom(true);
                answerSubmitResponse.setOpenedKingdomText("Sveikiname! Surinkote " + experience + " patirties taškų ir nuo Monstro pabaisos išlaisvinote Saldumynų karalystę!");
            }
        } else if (experience >= 8025 && experience <= 9150 && openedKingdomsSize < 7) {
            Optional<Kingdom> mushroomKingdom = kingdomService.getKingdomById(7L);

            if (mushroomKingdom.isPresent()) {
                user.getOpenedKingdoms().add(mushroomKingdom.get());
                answerSubmitResponse.setHasOpenedKingdom(true);
                answerSubmitResponse.setOpenedKingdomText("Sveikiname! Surinkote " + experience + " patirties taškų ir nuo Monstro pabaisos išlaisvinote Nykštukų karalystę!");
            }
        } else {
            answerSubmitResponse.setHasOpenedKingdom(false);
            answerSubmitResponse.setOpenedKingdomText("");
        }


        if (correctlyAnswered) {
            user.setUserCoins(coins);
        }

        LevelStatistics existingStat = null;

        for (LevelStatistics l : user.getLevelStatistics()) {
            if (l.getLevelName().equals(answerSubmitRequest.getLevelName())) {
                existingStat = l;
            }
        }
        if (existingStat == null) {
            existingStat = new LevelStatistics(null, answerSubmitRequest.getLevelName(), 0, 0, user);
            user.getLevelStatistics().add(existingStat);
        }

        existingStat.setTotalAnswered(existingStat.getTotalAnswered() + 1);
        if (correctlyAnswered) {
            existingStat.setCorrectlyAnswered(existingStat.getCorrectlyAnswered() + 1);
        }
        userService.saveUser(user);

        answerSubmitResponse.setUpdatedCoins(coins);
        answerSubmitResponse.setUpdatedExperience(experience);
        answerSubmitResponse.setCorrectAnswerText(question.getCorrectOptionText());

        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setAnsweredQuestion(question);
        userAnswer.setAnsweringUser(user);
        userAnswer.setCorrectlyAnswered(correctlyAnswered);
        userAnswer.setDateTimeAnswered(LocalDateTime.now());
        rankingService.saveUserAnswer(userAnswer);

        return answerSubmitResponse;
    }

}
