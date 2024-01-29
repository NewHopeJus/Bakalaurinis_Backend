package com.example.bakalaurinis.bootstrap;

import com.example.bakalaurinis.model.*;
import com.example.bakalaurinis.repository.QuestionRepository;
import com.example.bakalaurinis.repository.UserRepository;
import com.example.bakalaurinis.services.QuestionService;
import com.example.bakalaurinis.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner  {
    UserService userService;
    QuestionService questionService;
    @Autowired
    public DataLoader(UserService userService, QuestionService questionService) {
        this.userService = userService;
        this.questionService = questionService;
    }

    @Override
    public void run(String... args) throws Exception {

        // ObjectMapper klase yra Jackson bibliotekos dalis
        //Naudojama serializuoti ir deserelizuoti JSON i Java objektus

        ObjectMapper objectMapper = new ObjectMapper();


        //TypeReference is a class provided by the Jackson library, and
        // it is used in scenarios where you need to provide generic type information
        // for JSON parsing in Java. Due to Java's type erasure mechanism, generic type
        // information is not available at runtime.
        // This is where TypeReference comes into play


        TypeReference<List<Question>> typeReference = new TypeReference<List<Question>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/questions.json");

        try {
            List<Question> questions = objectMapper.readValue(inputStream,typeReference);
            questionService.saveQuestions(questions);
            System.out.println("Questions Saved!");
        } catch (IOException e){
            System.out.println("Unable to save questions: " + e.getMessage());
        }
    };





//        User user = new User("Justyna", "just2@gmail.com", "123", UserRole.USER);
//        userRepository.save(user);


//        Question question = new Question();
//        question.setQuestionType(QuestionType.ONE_ANSWER);
//        question.setDescription("5 – 4 = 1");
//
//        Option option1 = new Option(null, question, "3", false);
//        Option option2 = new Option(null, question, "2", false);
//        Option option3 = new Option(null, question, "1", true);
//        Option option4 = new Option(null, question, "9", false);
//
//        List<Option> options = Arrays.asList(option1, option2, option3, option4);
//        question.setOptions(options);
//
//        questionRepository.save(question);




}
