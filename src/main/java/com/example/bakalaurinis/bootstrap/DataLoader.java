package com.example.bakalaurinis.bootstrap;

import com.example.bakalaurinis.model.*;
import com.example.bakalaurinis.services.KingdomService;
import com.example.bakalaurinis.services.QuestionService;
import com.example.bakalaurinis.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    private UserService userService;
    private QuestionService questionService;

    private KingdomService kingdomService;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public DataLoader(UserService userService, QuestionService questionService,
                      KingdomService kingdomService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.questionService = questionService;
        this.kingdomService = kingdomService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (!questionService.hasQuestions()) {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<Question>> typeReference = new TypeReference<>() {};
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

            ShopItem elfuNamas = new ShopItem(null, "Elfų namas", "imageViewElfuNamasElfuKaralyste", "elf_home", 20, null);
            ShopItem drakonas = new ShopItem(null, "Drakonas", "imageViewDrakonasElfuKaralyste", "drakonas", 10, null);
            ShopItem elfas = new ShopItem(null, "Elfas Jonas",  "imageViewElfasJonasElfuKaralyste", "elfas", 10, null);
            ShopItem elfeLiepa = new ShopItem(null, "Elfė Liepa", "imageViewElfeLiepa", "elf_girl", 10, null);
            ShopItem stebuklingasPaukstis = new ShopItem(null, "Stebuklingas Paukštis", "imageViewStebuklingasPaukstis", "bird_magestic", 10, null);
            ShopItem VandensLelija = new ShopItem(null, "Vandens Lelija", "imageViewVandensLelija", "vandens_lelija", 5, null);
            ShopItem magiskaGele = new ShopItem(null, "Magiška Gėlė", "imageViewMagiskaGele", "gele_elfu_karalyste", 5, null);


            elfKingdomItems.add(drakonas);
            elfKingdomItems.add(elfas);
            elfKingdomItems.add(elfuNamas);
            elfKingdomItems.add(elfeLiepa);
            elfKingdomItems.add(stebuklingasPaukstis);
            elfKingdomItems.add(VandensLelija);
            elfKingdomItems.add(magiskaGele);


            ShopItem gyvenamasisGrybas = new ShopItem(null, "Gyvenamasis Grybas", "imageViewGyvenamasisGrybasGrybuKaralyste","gyvenamasis_grybas", 15, null);
            ShopItem grybejaDidzioji = new ShopItem(null, "Grybėja Didžioji", "imageViewgrybejaDidziojiGrybuKaralyste", "grybeja_didzioji", 20, null);
            ShopItem zaliojiGudryte = new ShopItem(null, "Žalioji Gudrytė", "imageViewzaliojiGudryteGrybuKaralyste","zalioji_gudryte", 20, null);
            ShopItem linksmasisRaudonikis = new ShopItem(null, "Linksmasis Raudonikis", "imageViewlinksmasisRaudonikisGrybuKaralyste", "linksmasis_raudonikis",20, null);
            ShopItem voveryte = new ShopItem(null, "Voverytė", "imageViewVoveryteGrybuKaralyste", "voveryte",5, null );
            ShopItem stirnyte = new ShopItem(null, "Stirnytė", "imageViewStirnyteGrybuKaralyste", "stirnyte",5, null);
            ShopItem pauksciukasSpalvingas = new ShopItem(null, "Paukščiukas Spalvingas", "imageViewPauksciukasSpalvingasGrybuKaralyste", "pauksciukas_spalvingas",5, null);
            ShopItem pauksciukasDziugeselis = new ShopItem(null, "Paukščiukas Džiugesėlis", "imageViewPauksciukasDziugeselisGrybuKaralyste", "pauksciukas_dziugeselis",5, null);
            ShopItem pauksciukasSviesele = new ShopItem(null, "Paukščiukas Švieselė", "imageViewPauksciukasSvieseleGrybuKaralyste","pauksciukas_sviesele", 5, null);

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
            ShopItem princeseRozyte = new ShopItem(null, "Princesė Rožytė", "imageViewPrinceseRozyteGeliuKaralyste", "princese_rozyte", 5, null);
            ShopItem pauksciukasSvelnute = new ShopItem(null, "Paukščiukas Švelnutė", "imageViewPauksciukasSvelnuteGeliuKaralyste","pauksciukas_svelnute", 3, null);
            ShopItem kiskisPukuotasSmalsuolis = new ShopItem(null, "Pūkuotas Smalsuolis", "imageViewPukuotasSmalsuolis","bunny_flower_kingdom", 3, null);
            ShopItem kiskisLaukoTykotojas = new ShopItem(null, "Lauko Tykotojas", "imageViewLaukoTykotojas","bunny_2_flower_kingdom", 3, null);
            ShopItem ziedai = new ShopItem(null, "Medžio žiedai", "imageViewZiedai","blossom_flower_kingdom", 3, null);
            ShopItem pauksciukasMelsvasisDziaugsmas = new ShopItem(null, "Melsvasis Džiaugsmas", "imageViewPauksciukasMelsvasisDziaugsmas","bird_2_flower_kingdom", 3, null);
            ShopItem suoliukas = new ShopItem(null, "Suoliukas", "imageViewSuoliukas","suoliukas_flower_kingdom", 2, null);
            ShopItem zydintisMedis = new ShopItem(null, "Žydintis medis", "imageViewZydintisMedis","tree_flower_kingdom", 3, null);
            ShopItem elnias = new ShopItem(null, "Elnias", "imageViewElnias","dear_flower_kingdom", 5, null);
            ShopItem roze = new ShopItem(null, "Rožė", "imageViewRoze","rose_flower_kingdom", 5, null);
            ShopItem drugelis = new ShopItem(null, "Drugelis", "imageViewDrugelis","butterfly_flower_kingdom", 5, null);



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
            ShopItem manoPilisDrakonas = new ShopItem(null, "Drakonas Debesų Raitelis", "imageViewFlyingDragon", "flying_dragon", 5, null);
            ShopItem oroBalionas = new ShopItem(null, "Oro balionas", "imageViewBaloon", "baloon", 5, null);
            ShopItem vejoKariai= new ShopItem(null, "Vėjo Kariai", "imageViewVejoKariai", "army", 5, null);
            ShopItem melynujuSarvuRiteriai = new ShopItem(null, "Mėlynųjų Šarvų Riteriai", "imageViewMelynujuSarvuRiteriai", "army_bigger", 5, null);
            ShopItem draugiskasisDivdyris = new ShopItem(null, "Draugiškasis Didvyris", "imageViewDraugiskasisDidvyris", "kinght_second", 2, null);
            ShopItem garsusisPabaisuNugaletojas = new ShopItem(null, "Garsusis Pabaisų Nugalėtojas", "imageViewGarsusisPabaisuNugaletojas", "knight_cute", 2, null);
            ShopItem laivasSuGinkluote = new ShopItem(null, "Laivas su ginkluote", "imageViewLaivasSuGinkluote", "ship", 6, null);
            ShopItem patranka = new ShopItem(null, "Patranka", "imageViewPatranka", "cannon", 5, null);
            ShopItem katapulta = new ShopItem(null, "Katapulta", "imageViewKatapulta", "catapult", 5, null);


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


            ShopItem pilisPovandenineKaralyste = new ShopItem(null, "Povandeninė Pilis", "imageViewPilisPovandenineKaralyste", "castle_povandenine_karalyste_6", 30, null);
            ShopItem undine = new ShopItem(null, "Undinė", "imageViewUndine", "mermaid_little", 30, null);
            ShopItem juruArkliukas = new ShopItem(null, "Jūrų arkliukas", "imageViewJuruArkliukas", "sea_horse", 20, null);
            ShopItem moliuskas = new ShopItem(null, "Moliuskas", "imageViewMoliuskas", "shell", 10, null);
            ShopItem krabas = new ShopItem(null, "Krabas", "imageViewKrabas", "crab", 20, null);
            ShopItem zuvyteRaudonasisNeonas = new ShopItem(null, "Žuvytė Raudonasis Neonas", "imageViewZuvyteRaudonasisNeonas", "orange_colorful_fish", 10, null);
            ShopItem zuvyteMelynojiGupija = new ShopItem(null, "Žuvytė Mėlynoji Gupija", "imageViewZuvyteMelynojiGupija", "blue_purple_fish", 10, null);
            ShopItem auksineZuvele = new ShopItem(null, "Auksinė Žuvelė", "imageViewAuksineZuvele", "golden_fish", 10, null);
            ShopItem zuvytePurpurineTetra = new ShopItem(null, "Žuvytė Purpurinė Tetra", "imageViewZuvytePurpurineTetra", "colorful_fish", 10, null);
            ShopItem astuonkojis = new ShopItem(null, "Aštunkojis", "imageViewAstunkojis", "cute_creature_povandenine_karalyste", 15, null);
            ShopItem jurosGeles = new ShopItem(null, "Jūros Gėlės", "imageViewJurosGeles", "colorful_plant", 5, null);
            ShopItem anacharisAugalas = new ShopItem(null, "Anacharis Augalas", "imageViewAnacharisAugalas", "papartis", 5, null);
            ShopItem gorgonaceaAugalas = new ShopItem(null, "Gorgonacea Augalas", "imageViewGorgonaceaAugalas", "purple_sea_plant", 5, null);


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


            ShopItem saldumynuPilis = new ShopItem(null, "Saldumynų pilis", "imageViewSaldumynuPilis", "candy_kingdom_castle", 40, null);
            ShopItem saldainiuPaukstis = new ShopItem(null, "Saldainių paukštis", "imageViewSaldainiuPaukstis", "sweets_bird", 20, null);
            ShopItem ledinukas = new ShopItem(null, "Ledinukas", "imageViewLedinukas", "lolipop", 10, null);
            ShopItem saldumynuFeja = new ShopItem(null, "Saldumynų fėja", "imageViewSaldumynuFeja", "feja", 30, null);
            ShopItem saldainis = new ShopItem(null, "Saldainis", "imageViewSaldainis", "candy", 10, null);
            ShopItem cukrausDrugelis = new ShopItem(null, "Cukraus Drugelis", "imageViewCukrausDrugelis", "drugelis", 20, null);
            ShopItem spalvotiLedai = new ShopItem(null, "Spalvoti Ledai", "imageViewSpalvotiLedai", "ice_cream", 10, null);
            ShopItem ledinukasSirdute = new ShopItem(null, "Ledinukas Širdutė", "imageViewLedinukasSirdute", "lolipop_heart", 10, null);
            ShopItem vaikstantisSausainis = new ShopItem(null, "Vaikštantis Sausainis", "imageViewSausainis", "cookie", 10, null);
            ShopItem saldainiuTriusiukas = new ShopItem(null, "Saldainių Triušiukas", "imageViewSaldainiuTriusiukas", "bunny", 20, null);
            ShopItem vaikstantisPyragas= new ShopItem(null, "Vaikštantis Pyragas", "imageViewVaikstantisPyragas", "pyragas", 10, null);
            ShopItem katinas = new ShopItem(null, "Katinas", "imageViewKatinas", "cat", 20, null);
            ShopItem katinoKaruna = new ShopItem(null, "Katino šokoladinė karūna", "imageViewKaruna", "crown", 10, null);
            ShopItem keksiukas = new ShopItem(null, "Keksiukas", "imageViewKeksiukas", "cupcake", 10, null);
            ShopItem bitute = new ShopItem(null, "Bitutė", "imageViewBitute", "bee_saldumynu", 10, null);

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

            ShopItem nykstukuNamas = new ShopItem(null, "Nykštukų Namas", "imageViewNykstukuNamas", "nykstuku_namas", 25, null);
            ShopItem boruzele = new ShopItem(null, "Boružėlė", "imageViewBoruzele", "boruzele", 10, null);
            ShopItem viksras = new ShopItem(null, "Vikšras", "imageViewViksras", "cetepillar", 10, null);
            ShopItem zaliasisPukutis = new ShopItem(null, "Žaliasis Pūkutis", "imageViewZaliasisPukutis", "cute_creature", 20, null);
            ShopItem raudonasVabzdys = new ShopItem(null, "Raudonas Vabzdys", "imageViewRaudonasVabzdys", "bug", 10, null);
            ShopItem laukineBitute = new ShopItem(null, "Laukinė Bitutė", "imageViewLaukineBitute", "bee", 10, null);
            ShopItem pauksciukasCvirpukas = new ShopItem(null, "Paukščiukas Cvirpukas", "imageViewPauksciukasCvirpukas", "bird_gnom_kingdom", 20, null);
            ShopItem nykstukasBasutis = new ShopItem(null, "Nykštukas Basutis", "imageViewNykstukasBasutis", "nykstukas_naujas", 20, null);
            ShopItem nykstukasIsmincius = new ShopItem(null, "Nykštukas Išminčius", "imageViewNykstukasIsmincius", "nykstykas_didesnis", 20, null);
            ShopItem isminciausDarzoves = new ShopItem(null, "Išminčiaus užaugintos daržovės", "imageViewDarzoves", "darzoves", 20, null);
            ShopItem varlyte = new ShopItem(null, "Varlytė", "imageViewVarlyte", "frog", 20, null);
            ShopItem stebuklingaZuvyte = new ShopItem(null, "Stebuklinga Žuvytė", "imageViewStebuklingaZuvyte", "goldfish_little", 20, null);
            ShopItem linksmasisVabaliukas = new ShopItem(null, "Linksmasis Vabaliukas", "imageViewLinksmasisVabaliukas", "cute_bug", 20, null);

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

        if (userService.findUserByUsername("admin")==null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setUserCoins(0);
            admin.setUserExperience(0);
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of(Role.ROLE_ADMIN));
            userService.saveUser(admin);
        }


    }

}
