package com.example.bakalaurinis.model.dtos;

import com.example.bakalaurinis.model.ShopItem;

import java.util.ArrayList;
import java.util.List;

public class ShopItemListDto {
    private List<ShopItem> shopItems;

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
