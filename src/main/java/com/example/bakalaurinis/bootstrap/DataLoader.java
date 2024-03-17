package com.example.bakalaurinis.bootstrap;

import com.example.bakalaurinis.model.Kingdom;
import com.example.bakalaurinis.model.Question;
import com.example.bakalaurinis.model.ShopItem;
import com.example.bakalaurinis.services.KingdomService;
import com.example.bakalaurinis.services.QuestionService;
import com.example.bakalaurinis.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner  {
    private UserService userService;
    private QuestionService questionService;

    private KingdomService kingdomService;
    @Autowired
    public DataLoader(UserService userService, QuestionService questionService,
                      KingdomService kingdomService) {
        this.userService = userService;
        this.questionService = questionService;
        this.kingdomService = kingdomService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (!questionService.hasQuestions()) { //nes nereikia per nauja krauti jei jau uzkrautas, todel tikrinu ar is viso yra klausimu
            // ObjectMapper klase yra Jackson bibliotekos dalis
            //Naudojama serializuoti ir deserelizuoti JSON i Java objektus

            ObjectMapper objectMapper = new ObjectMapper();


            //TypeReference is a class provided by the Jackson library, and
            // it is used in scenarios where you need to provide generic type information
            // for JSON parsing in Java. Due to Java's type erasure mechanism, generic type
            // information is not available at runtime.
            // This is where TypeReference comes into play


            TypeReference<List<Question>> typeReference = new TypeReference<>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/questions.json");

            try {
                List<Question> questions = objectMapper.readValue(inputStream, typeReference);
                questionService.saveQuestions(questions);
                System.out.println("Questions Saved!");
            } catch (IOException e) {
                System.out.println("Unable to save questions: " + e.getMessage());
            }
        }

        if(!kingdomService.hasKingdoms()){

            List<ShopItem> items = new ArrayList<>();

            List<ShopItem> itemListEmpty = new ArrayList<>();

            ShopItem drakonas = new ShopItem(null, "Drakonas", "drakonas", 10, null);
            ShopItem elfas = new ShopItem(null, "Elfas Jonas", "elfas", 10, null);
            ShopItem elfuPilis = new ShopItem(null, "Elfų pilis", "elf_home", 10, null);
            items.add(drakonas);
            items.add(elfas);
            items.add(elfuPilis);

            List<Kingdom> kingdomList = new ArrayList<>();



            Kingdom kingdom0 = new Kingdom(null, "Mano pilis", "mano_pilis", itemListEmpty);
            Kingdom kingdom1 = new Kingdom(null, "Gėlių karalystė", "geliu_karalyste", itemListEmpty);
            Kingdom kingdom4 = new Kingdom(null, "Povandeninė karalystė", "povandenine_karalyste", itemListEmpty);
            Kingdom kingdom3 = new Kingdom(null, "Grybų karalystė", "grybu_karalyste", itemListEmpty);
            Kingdom kingdom2 = new Kingdom(null, "Elfų karalystė", "elfu_karalyste", items);
            Kingdom kingdom5 = new Kingdom(null, "Saldumynų karalystė", "saldumynu_karalyste", itemListEmpty);
            Kingdom kingdom6 = new Kingdom(null, "Nykštukų karalystė", "nykstuku_karalyste", itemListEmpty);

            kingdomList.add(kingdom0);
            kingdomList.add(kingdom1);
            kingdomList.add(kingdom2);
            kingdomList.add(kingdom3);
            kingdomList.add(kingdom4);
            kingdomList.add(kingdom5);
            kingdomList.add(kingdom6);

            kingdomService.saveKingdoms(kingdomList);
            System.out.println("Kingdoms Saved!");



        }


    }


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
