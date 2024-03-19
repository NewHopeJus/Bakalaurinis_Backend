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
public class DataLoader implements CommandLineRunner {
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

        if (!kingdomService.hasKingdoms()) {

            List<ShopItem> elfKingdomItems = new ArrayList<>();
            List<ShopItem> mushroomKingdomItems = new ArrayList<>();
            List<ShopItem> flowerKingdomItems = new ArrayList<>();


            List<ShopItem> itemListEmpty = new ArrayList<>();

            ShopItem drakonas = new ShopItem(null, "Drakonas", "imageViewDrakonasElfuKaralyste", "drakonas", 1, null);
            ShopItem elfas = new ShopItem(null, "Elfas Jonas",  "imageViewElfasJonasElfuKaralyste", "elfas", 1, null);
            ShopItem elfuNamas = new ShopItem(null, "Elfų namas", "imageViewElfuNamasElfuKaralyste", "elf_home", 1, null);
            elfKingdomItems.add(drakonas);
            elfKingdomItems.add(elfas);
            elfKingdomItems.add(elfuNamas);


            ShopItem grybejaDidzioji = new ShopItem(null, "Grybėja Didžioji", "imageViewgrybejaDidziojiGrybuKaralyste", "grybeja_didzioji", 1, null);
            ShopItem zaliojiGudryte = new ShopItem(null, "Žalioji Gudrytė", "imageViewzaliojiGudryteGrybuKaralyste","zalioji_gudryte", 1, null);
            ShopItem linksmasisRaudonikis = new ShopItem(null, "Linksmasis Raudonikis", "imageViewlinksmasisRaudonikisGrybuKaralyste", "linksmasis_raudonikis",1, null);
            ShopItem voveryte = new ShopItem(null, "Voverytė", "imageViewVoveryteGrybuKaralyste", "voveryte",1, null );
            ShopItem stirnyte = new ShopItem(null, "Stirnytė", "imageViewStirnyteGrybuKaralyste", "stirnyte",1, null);
            ShopItem gyvenamasisGrybas = new ShopItem(null, "Gyvenamasis Grybas", "imageViewGyvenamasisGrybasGrybuKaralyste","gyvenamasis_grybas", 1, null);
            ShopItem pauksciukasSpalvingas = new ShopItem(null, "Paukščiukas Spalvingas", "imageViewPauksciukasSpalvingasGrybuKaralyste", "pauksciukas_spalvingas",1, null);
            ShopItem pauksciukasDziugeselis = new ShopItem(null, "Paukščiukas Džiugesėlis", "imageViewPauksciukasDziugeselisGrybuKaralyste", "pauksciukas_dziugeselis",1, null);
            ShopItem pauksciukasSviesele = new ShopItem(null, "Paukščiukas Švieselė", "imageViewPauksciukasSvieseleGrybuKaralyste","pauksciukas_sviesele", 1, null);

            mushroomKingdomItems.add(grybejaDidzioji);
            mushroomKingdomItems.add(zaliojiGudryte);
            mushroomKingdomItems.add(linksmasisRaudonikis);
            mushroomKingdomItems.add(voveryte);
            mushroomKingdomItems.add(stirnyte);
            mushroomKingdomItems.add(pauksciukasSpalvingas);
            mushroomKingdomItems.add(pauksciukasDziugeselis);
            mushroomKingdomItems.add(gyvenamasisGrybas);
            mushroomKingdomItems.add(pauksciukasSviesele);


            ShopItem princeseRozyte = new ShopItem(null, "Princesė Rožytė", "imageViewPrinceseRozyteGeliuKaralyste", "princese_rozyte", 10, null);
            ShopItem sviesiojiPilis = new ShopItem(null, "Šviesioji Pilis", "imageViewSviesiojiPilisGeliuKaralyste","sviesioji_pilis", 10, null);
            ShopItem fontanas = new ShopItem(null, "Fontanas", "imageViewFontanasGeliuKaralyste","fontanas", 10, null);
            ShopItem zydintisMedis = new ShopItem(null, "Žydintis Medis", "imageViewZydintisMedisGeliuKaralyste","zydintis_medis", 10, null);
            ShopItem pauksciukasSvelnute = new ShopItem(null, "Paukščiukas Švelnutė", "imageViewPauksciukasSvelnuteGeliuKaralyste","pauksciukas_svelnute", 10, null);

            flowerKingdomItems.add(princeseRozyte);
            flowerKingdomItems.add(sviesiojiPilis);
            flowerKingdomItems.add(fontanas);
            flowerKingdomItems.add(zydintisMedis);
            flowerKingdomItems.add(pauksciukasSvelnute);



            List<Kingdom> kingdomList = new ArrayList<>();


            Kingdom kingdom0 = new Kingdom(null, "Mano pilis", "mano_pilis", itemListEmpty);
            Kingdom kingdom1 = new Kingdom(null, "Gėlių karalystė", "geliu_karalyste", flowerKingdomItems);
            Kingdom kingdom4 = new Kingdom(null, "Povandeninė karalystė", "povandenine_karalyste", itemListEmpty);
            Kingdom kingdom3 = new Kingdom(null, "Grybų karalystė", "grybu_karalyste", mushroomKingdomItems);
            Kingdom kingdom2 = new Kingdom(null, "Elfų karalystė", "elfu_karalyste", elfKingdomItems);
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
