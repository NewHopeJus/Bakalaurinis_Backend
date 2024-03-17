package com.example.bakalaurinis.security.dtos;

import com.example.bakalaurinis.model.ShopItem;

import java.util.ArrayList;
import java.util.List;

public class ShopItemListDto {
    List<ShopItem> shopItems;

    public ShopItemListDto() {
        this.shopItems = new ArrayList<>();
    }

    public List<ShopItem> getShopItems() {
        return shopItems;
    }

    public void setShopItems(List<ShopItem> shopItems) {
        this.shopItems = shopItems;
    }
}
