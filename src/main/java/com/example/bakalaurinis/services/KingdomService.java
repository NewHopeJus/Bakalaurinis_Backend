package com.example.bakalaurinis.services;

import com.example.bakalaurinis.model.Kingdom;
import com.example.bakalaurinis.model.ShopItem;
import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.model.dtos.KingdomDto;
import com.example.bakalaurinis.model.dtos.KingdomsResponse;
import com.example.bakalaurinis.repository.KingdomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KingdomService {
    private final KingdomRepository kingdomRepository;
    private final UserService userService;

    @Autowired
    public KingdomService(KingdomRepository kingdomRepository, UserService userService) {
        this.kingdomRepository = kingdomRepository;
        this.userService = userService;
    }

    public boolean hasKingdoms() { //nes bootstrape reikia patikrinti ar yra klausimu, jei ne tai uzkrauti
        return kingdomRepository.count() > 0;
    }


    public List<Kingdom> saveKingdoms(List<Kingdom> kingdoms) {
        for (Kingdom kingdom : kingdoms) {
            for (ShopItem item : kingdom.getShopItems()) {
                item.setKingdom(kingdom);
            }

        }
        return kingdomRepository.saveAll(kingdoms);
    }

    public KingdomsResponse getKingdomsForUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); //getting the username of the logged-in user
        User user = userService.findUserByUsername(username);
        KingdomsResponse kingdomsResponse = new KingdomsResponse();
        List<Kingdom> openedKingdoms = kingdomRepository.getOpenedKingdoms(user.getId());
        List<Kingdom> closedKingdoms = kingdomRepository.getClosedKingdoms(user.getId());
        if (openedKingdoms != null && closedKingdoms != null) {
            for (Kingdom k : openedKingdoms) {
                kingdomsResponse.getOpenedKingdoms().add(new KingdomDto(k, true));
            }
            for (Kingdom k : closedKingdoms) {
                kingdomsResponse.getClosedKingdoms().add(new KingdomDto(k, false));

            }
        }
        return kingdomsResponse;
    }

    public Optional<Kingdom> getKingdomById(Long id) {
        return kingdomRepository.findById(id);
    }
}
