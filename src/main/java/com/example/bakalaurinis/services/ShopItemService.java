package com.example.bakalaurinis.services;

import com.example.bakalaurinis.repository.ItemRepository;
import com.example.bakalaurinis.security.dtos.ShopItemListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopItemService {
    private final ItemRepository itemRepository;


    @Autowired
    public ShopItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ShopItemListDto getItemsByKingdomId(Long id) {
        ShopItemListDto shopItemListDto = new ShopItemListDto();
        shopItemListDto.setShopItems(itemRepository.getShopItemsByKingdom_Id(id));

        return shopItemListDto;
    }

}
