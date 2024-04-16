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

            List<ShopItem> myCastleItems = new ArrayList<>();
            List<ShopItem> flowerKingdomItems = new ArrayList<>();
            List<ShopItem> elfKingdomItems = new ArrayList<>();
            List<ShopItem> mushroomKingdomItems = new ArrayList<>();
            List<ShopItem> underwaterKingdomItems = new ArrayList<>();
            List<ShopItem> sweetsKingdomItems = new ArrayList<>();
            List<ShopItem> dwarfKingdomItems = new ArrayList<>();




            List<ShopItem> itemListEmpty = new ArrayList<>();

            ShopItem elfuNamas = new ShopItem(null, "Elfų namas", "imageViewElfuNamasElfuKaralyste", "elf_home", 1, null);
            ShopItem drakonas = new ShopItem(null, "Drakonas", "imageViewDrakonasElfuKaralyste", "drakonas", 1, null);
            ShopItem elfas = new ShopItem(null, "Elfas Jonas",  "imageViewElfasJonasElfuKaralyste", "elfas", 1, null);
            ShopItem elfeLiepa = new ShopItem(null, "Elfė Liepa", "imageViewElfeLiepa", "elf_girl", 1, null);
            ShopItem stebuklingasPaukstis = new ShopItem(null, "Stebuklingas Paukštis", "imageViewStebuklingasPaukstis", "bird_magestic", 1, null);
            ShopItem VandensLelija = new ShopItem(null, "Vandens Lelija", "imageViewVandensLelija", "vandens_lelija", 1, null);
            ShopItem magiskaGele = new ShopItem(null, "Magiška gėlė", "imageViewMagiskaGele", "gele_elfu_karalyste", 1, null);


            elfKingdomItems.add(drakonas);
            elfKingdomItems.add(elfas);
            elfKingdomItems.add(elfuNamas);
            elfKingdomItems.add(elfeLiepa);
            elfKingdomItems.add(stebuklingasPaukstis);
            elfKingdomItems.add(VandensLelija);
            elfKingdomItems.add(magiskaGele);


            ShopItem gyvenamasisGrybas = new ShopItem(null, "Gyvenamasis Grybas", "imageViewGyvenamasisGrybasGrybuKaralyste","gyvenamasis_grybas", 1, null);
            ShopItem grybejaDidzioji = new ShopItem(null, "Grybėja Didžioji", "imageViewgrybejaDidziojiGrybuKaralyste", "grybeja_didzioji", 1, null);
            ShopItem zaliojiGudryte = new ShopItem(null, "Žalioji Gudrytė", "imageViewzaliojiGudryteGrybuKaralyste","zalioji_gudryte", 1, null);
            ShopItem linksmasisRaudonikis = new ShopItem(null, "Linksmasis Raudonikis", "imageViewlinksmasisRaudonikisGrybuKaralyste", "linksmasis_raudonikis",1, null);
            ShopItem voveryte = new ShopItem(null, "Voverytė", "imageViewVoveryteGrybuKaralyste", "voveryte",1, null );
            ShopItem stirnyte = new ShopItem(null, "Stirnytė", "imageViewStirnyteGrybuKaralyste", "stirnyte",1, null);
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


            ShopItem sviesiojiPilis = new ShopItem(null, "Šviesioji Pilis", "imageViewSviesiojiPilisGeliuKaralyste","sviesioji_pilis", 10, null);
            ShopItem princeseRozyte = new ShopItem(null, "Princesė Rožytė", "imageViewPrinceseRozyteGeliuKaralyste", "princese_rozyte", 10, null);
            ShopItem pauksciukasSvelnute = new ShopItem(null, "Paukščiukas Švelnutė", "imageViewPauksciukasSvelnuteGeliuKaralyste","pauksciukas_svelnute", 10, null);
            ShopItem kiskisPukuotasSmalsuolis = new ShopItem(null, "Pūkuotas Smalsuolis", "imageViewPukuotasSmalsuolis","bunny_flower_kingdom", 10, null);
            ShopItem kiskisLaukoTykotojas = new ShopItem(null, "Lauko Tykotojas", "imageViewLaukoTykotojas","bunny_2_flower_kingdom", 10, null);
            ShopItem ziedai = new ShopItem(null, "Medžio žiedai", "imageViewZiedai","blossom_flower_kingdom", 10, null);
            ShopItem pauksciukasMelsvasisDziaugsmas = new ShopItem(null, "Paukščiukas melsvasis džiaugsmas", "imageViewPauksciukasMelsvasisDziaugsmas","bird_2_flower_kingdom", 10, null);
            ShopItem suoliukas = new ShopItem(null, "Suoliukas", "imageViewSuoliukas","suoliukas_flower_kingdom", 10, null);
            ShopItem zydintisMedis = new ShopItem(null, "Žydintis medis", "imageViewZydintisMedis","tree_flower_kingdom", 10, null);
            ShopItem elnias = new ShopItem(null, "Elnias", "imageViewElnias","dear_flower_kingdom", 10, null);
            ShopItem roze = new ShopItem(null, "Rožė", "imageViewRoze","rose_flower_kingdom", 10, null);
            ShopItem drugelis = new ShopItem(null, "Drugelis", "imageViewDrugelis","butterfly_flower_kingdom", 10, null);



            flowerKingdomItems.add(princeseRozyte);
            flowerKingdomItems.add(sviesiojiPilis);
            flowerKingdomItems.add(pauksciukasSvelnute);
            flowerKingdomItems.add(kiskisPukuotasSmalsuolis);
            flowerKingdomItems.add(kiskisLaukoTykotojas);
            flowerKingdomItems.add(ziedai);
            flowerKingdomItems.add(pauksciukasMelsvasisDziaugsmas);
            flowerKingdomItems.add(suoliukas);
            flowerKingdomItems.add(zydintisMedis);
            flowerKingdomItems.add(elnias);
            flowerKingdomItems.add(roze);
            flowerKingdomItems.add(drugelis);



            ShopItem pilis = new ShopItem(null, "Pilis", "imageViewPagrinidinePilis", "pilis", 10, null);
            ShopItem manoPilisDrakonas = new ShopItem(null, "Drakonas Debesų Raitelis", "imageViewFlyingDragon", "flying_dragon", 10, null);
            ShopItem oroBalionas = new ShopItem(null, "Oro balionas", "imageViewBaloon", "baloon", 10, null);
            ShopItem vejoKariai= new ShopItem(null, "Vėjo Kariai", "imageViewVejoKariai", "army", 10, null);
            ShopItem melynujuSarvuRiteriai = new ShopItem(null, "Mėlynųjų Šarvų Riteriai", "imageViewMelynujuSarvuRiteriai", "army_bigger", 10, null);
            ShopItem draugiskasisDivdyris = new ShopItem(null, "Draugiškasis Didvyris", "imageViewDraugiskasisDidvyris", "kinght_second", 10, null);
            ShopItem garsusisPabaisuNugaletojas = new ShopItem(null, "Garsusis Pabaisų Nugalėtojas", "imageViewGarsusisPabaisuNugaletojas", "knight_cute", 10, null);
            ShopItem laivasSuGinkluote = new ShopItem(null, "Laivas su ginkluote", "imageViewLaivasSuGinkluote", "ship", 10, null);
            ShopItem patranka = new ShopItem(null, "Patranka", "imageViewPatranka", "cannon", 10, null);
            ShopItem katapulta = new ShopItem(null, "Katapulta", "imageViewKatapulta", "catapult", 10, null);


            myCastleItems.add(pilis);
            myCastleItems.add(manoPilisDrakonas);
            myCastleItems.add(oroBalionas);
            myCastleItems.add(vejoKariai);
            myCastleItems.add(melynujuSarvuRiteriai);
            myCastleItems.add(draugiskasisDivdyris);
            myCastleItems.add(garsusisPabaisuNugaletojas);
            myCastleItems.add(laivasSuGinkluote);
            myCastleItems.add(patranka);
            myCastleItems.add(katapulta);


            ShopItem pilisPovandenineKaralyste = new ShopItem(null, "Povandeninė Pilis", "imageViewPilisPovandenineKaralyste", "castle_povandenine_karalyste_6", 1, null);
            ShopItem undine = new ShopItem(null, "Undinė", "imageViewUndine", "mermaid_little", 1, null);
            ShopItem juruArkliukas = new ShopItem(null, "Jūrų arkliukas", "imageViewJuruArkliukas", "sea_horse", 1, null);
            ShopItem moliuskas = new ShopItem(null, "Moliuskas", "imageViewMoliuskas", "shell", 1, null);
            ShopItem krabas = new ShopItem(null, "Krabas", "imageViewKrabas", "crab", 1, null);
            ShopItem zuvyteRaudonasisNeonas = new ShopItem(null, "Žuvytė Raudonasis Neonas", "imageViewZuvyteRaudonasisNeonas", "orange_colorful_fish", 1, null);
            ShopItem zuvyteMelynojiGupija = new ShopItem(null, "Žuvytė Mėlynoji Gupija", "imageViewZuvyteMelynojiGupija", "blue_purple_fish", 1, null);
            ShopItem auksineZuvele = new ShopItem(null, "Auksinė Žuvelė", "imageViewAuksineZuvele", "golden_fish", 1, null);
            ShopItem zuvytePurpurineTetra = new ShopItem(null, "Žuvytė Purpurinė Tetra", "imageViewZuvytePurpurineTetra", "colorful_fish", 1, null);
            ShopItem astuonkojis = new ShopItem(null, "Aštunkojis", "imageViewAstunkojis", "cute_creature_povandenine_karalyste", 1, null);
            ShopItem jurosGeles = new ShopItem(null, "Jūros Gėlės", "imageViewJurosGeles", "colorful_plant", 1, null);
            ShopItem anacharisAugalas = new ShopItem(null, "Anacharis Augalas", "imageViewAnacharisAugalas", "papartis", 1, null);
            ShopItem gorgonaceaAugalas = new ShopItem(null, "Gorgonacea Augalas", "imageViewGorgonaceaAugalas", "purple_sea_plant", 1, null);


            underwaterKingdomItems.add(undine);
            underwaterKingdomItems.add(juruArkliukas);
            underwaterKingdomItems.add(moliuskas);
            underwaterKingdomItems.add(krabas);
            underwaterKingdomItems.add(zuvyteRaudonasisNeonas);
            underwaterKingdomItems.add(zuvyteMelynojiGupija);
            underwaterKingdomItems.add(auksineZuvele);
            underwaterKingdomItems.add(zuvytePurpurineTetra);
            underwaterKingdomItems.add(astuonkojis);
            underwaterKingdomItems.add(jurosGeles);
            underwaterKingdomItems.add(anacharisAugalas);
            underwaterKingdomItems.add(gorgonaceaAugalas);
            underwaterKingdomItems.add(pilisPovandenineKaralyste);


            ShopItem saldumynuPilis = new ShopItem(null, "Saldumynų pilis", "imageViewSaldumynuPilis", "candy_kingdom_castle", 1, null);
            ShopItem saldainiuPaukstis = new ShopItem(null, "Saldainių paukštis", "imageViewSaldainiuPaukstis", "sweets_bird", 1, null);
            ShopItem ledinukas = new ShopItem(null, "Ledinukas", "imageViewLedinukas", "lolipop", 1, null);
            ShopItem saldumynuFeja = new ShopItem(null, "Saldumynų fėja", "imageViewSaldumynuFeja", "feja", 1, null);
            ShopItem saldainis = new ShopItem(null, "Saldainis", "imageViewSaldainis", "candy", 1, null);
            ShopItem cukrausDrugelis = new ShopItem(null, "Cukraus Drugelis", "imageViewCukrausDrugelis", "drugelis", 1, null);
            ShopItem spalvotiLedai = new ShopItem(null, "Spalvoti Ledai", "imageViewSpalvotiLedai", "ice_cream", 1, null);
            ShopItem ledinukasSirdute = new ShopItem(null, "Ledinukas Širdutė", "imageViewLedinukasSirdute", "lolipop_heart", 1, null);
            ShopItem vaikstantisSausainis = new ShopItem(null, "Vaikštantis Sausainis", "imageViewSausainis", "cookie", 1, null);
            ShopItem saldainiuTriusiukas = new ShopItem(null, "Saldainių Triušiukas", "imageViewSaldainiuTriusiukas", "bunny", 1, null);
            ShopItem vaikstantisPyragas= new ShopItem(null, "Vaikštantis Pyragas", "imageViewVaikstantisPyragas", "pyragas", 1, null);
            ShopItem katinas = new ShopItem(null, "Katinas", "imageViewKatinas", "cat", 1, null);
            ShopItem katinoKaruna = new ShopItem(null, "Katino šokoladinė karūna", "imageViewKaruna", "crown", 1, null);
            ShopItem keksiukas = new ShopItem(null, "Keksiukas", "imageViewKeksiukas", "cupcake", 1, null);
            ShopItem bitute = new ShopItem(null, "Bitutė", "imageViewBitute", "bee_saldumynu", 1, null);

            sweetsKingdomItems.add(saldumynuPilis);
            sweetsKingdomItems.add(saldainiuPaukstis);
            sweetsKingdomItems.add(ledinukas);
            sweetsKingdomItems.add(saldumynuFeja);
            sweetsKingdomItems.add(saldainis);
            sweetsKingdomItems.add(cukrausDrugelis);
            sweetsKingdomItems.add(spalvotiLedai);
            sweetsKingdomItems.add(ledinukasSirdute);
            sweetsKingdomItems.add(vaikstantisSausainis);
            sweetsKingdomItems.add(saldainiuTriusiukas);
            sweetsKingdomItems.add(vaikstantisPyragas);
            sweetsKingdomItems.add(katinas);
            sweetsKingdomItems.add(katinoKaruna);
            sweetsKingdomItems.add(keksiukas);
            sweetsKingdomItems.add(bitute);

            ShopItem nykstukuNamas = new ShopItem(null, "Nykštukų Namas", "imageViewNykstukuNamas", "nykstuku_namas", 1, null);
            ShopItem boruzele = new ShopItem(null, "Boružėlė", "imageViewBoruzele", "boruzele", 1, null);
            ShopItem viksras = new ShopItem(null, "Vikšras", "imageViewViksras", "cetepillar", 1, null);
            ShopItem zaliasisPukutis = new ShopItem(null, "Žaliasis Pūkutis", "imageViewZaliasisPukutis", "cute_creature", 1, null);
            ShopItem raudonasVabzdys = new ShopItem(null, "Raudonas Vabzdys", "imageViewRaudonasVabzdys", "bug", 1, null);
            ShopItem laukineBitute = new ShopItem(null, "Laukinė Bitutė", "imageViewLaukineBitute", "bee", 1, null);
            ShopItem pauksciukasCvirpukas = new ShopItem(null, "Paukščiukas Cvirpukas", "imageViewPauksciukasCvirpukas", "bird_gnom_kingdom", 1, null);
            ShopItem nykstukasBasutis = new ShopItem(null, "Nykštukas Basutis", "imageViewNykstukasBasutis", "nykstukas_naujas", 1, null);
            ShopItem nykstukasIsmincius = new ShopItem(null, "Nykštukas Išminčius", "imageViewNykstukasIsmincius", "nykstykas_didesnis", 1, null);
            ShopItem isminciausDarzoves = new ShopItem(null, "Išminčiaus užaugintos daržovės", "imageViewDarzoves", "darzoves", 1, null);
            ShopItem varlyte = new ShopItem(null, "Varlytė", "imageViewVarlyte", "frog", 1, null);
            ShopItem stebuklingaZuvyte = new ShopItem(null, "Stebuklinga Žuvytė", "imageViewStebuklingaZuvyte", "goldfish_little", 1, null);
            ShopItem linksmasisVabaliukas = new ShopItem(null, "Linksmasis Vabaliukas", "imageViewLinksmasisVabaliukas", "cute_bug", 1, null);

            dwarfKingdomItems.add(nykstukuNamas);
            dwarfKingdomItems.add(boruzele);
            dwarfKingdomItems.add(viksras);
            dwarfKingdomItems.add(zaliasisPukutis);
            dwarfKingdomItems.add(raudonasVabzdys);
            dwarfKingdomItems.add(laukineBitute);
            dwarfKingdomItems.add(pauksciukasCvirpukas);
            dwarfKingdomItems.add(nykstukasBasutis);
            dwarfKingdomItems.add(nykstukasIsmincius);
            dwarfKingdomItems.add(isminciausDarzoves);
            dwarfKingdomItems.add(varlyte);
            dwarfKingdomItems.add(stebuklingaZuvyte);
            dwarfKingdomItems.add(linksmasisVabaliukas);


            List<Kingdom> kingdomList = new ArrayList<>();


            Kingdom kingdom0 = new Kingdom(null, "Mano pilis", "mano_pilis", myCastleItems);
            Kingdom kingdom1 = new Kingdom(null, "Gėlių karalystė", "geliu_karalyste", flowerKingdomItems);
            Kingdom kingdom4 = new Kingdom(null, "Povandeninė karalystė", "povandenine_karalyste", underwaterKingdomItems);
            Kingdom kingdom3 = new Kingdom(null, "Grybų karalystė", "grybu_karalyste", mushroomKingdomItems);
            Kingdom kingdom2 = new Kingdom(null, "Elfų karalystė", "elfu_karalyste", elfKingdomItems);
            Kingdom kingdom5 = new Kingdom(null, "Saldumynų karalystė", "saldumynu_karalyste", sweetsKingdomItems);
            Kingdom kingdom6 = new Kingdom(null, "Nykštukų karalystė", "nykstuku_karalyste", dwarfKingdomItems);

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
