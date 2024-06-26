package com.example.bakalaurinis.services;

import com.example.bakalaurinis.model.ShopItem;
import com.example.bakalaurinis.model.User;
import com.example.bakalaurinis.model.dtos.BuyItemResponse;
import com.example.bakalaurinis.model.dtos.ShopItemListDto;
import com.example.bakalaurinis.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ShopItemService {
    private final ItemRepository itemRepository;
    private final UserService userService;


    @Autowired
    public ShopItemService(ItemRepository itemRepository, UserService userService) {
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

    public ShopItemListDto getItemsByKingdomId(Long id) {
        ShopItemListDto shopItemListDto = new ShopItemListDto();
        shopItemListDto.setShopItems(itemRepository.getShopItemsByKingdom_Id(id));

        return shopItemListDto;
    }

    public BuyItemResponse buyItem(Long id) {
        ShopItem shopItem = itemRepository.getShopItemById(id);
        if (shopItem == null) {
            return null;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); //gaunam username vartotojo prisijungusio
        User user = userService.findUserByUsername(username);


        if (user.getUserCoins() < shopItem.getPrice()) {
            return new BuyItemResponse(false, "Nepakankamas monetų skaičius");
        } else if (user.isItemBought(shopItem)) {
            return new BuyItemResponse(false, "Šis karalystės elementas jau yra nupirktas");
        }

        user.addBoughtItem(shopItem);
        user.subtractCoins(shopItem.getPrice());
        userService.saveUser(user);
        return new BuyItemResponse(true, "Elementas sėkmingai pridėtas į karalystę");

    }

    public ShopItemListDto getBoughtItemsByKingdomId(Long kingdomId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); //gaunam username vartotojo prisijungusio
        User user = userService.findUserByUsername(username);

        ShopItemListDto shopItemListDto = new ShopItemListDto();
        shopItemListDto.setShopItems(itemRepository.getBoughtShopItemsForUser(user.getId(), kingdomId));

        return shopItemListDto;
    }
}
