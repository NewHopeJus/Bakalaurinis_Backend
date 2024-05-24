package com.example.bakalaurinis.controller;

import com.example.bakalaurinis.model.dtos.BuyItemResponse;
import com.example.bakalaurinis.model.dtos.ShopItemListDto;
import com.example.bakalaurinis.services.ShopItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ShopItemController {
    private final ShopItemService shopItemService;

    @Autowired
    public ShopItemController(ShopItemService shopItemService) {
        this.shopItemService = shopItemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemsByKingdomId(@PathVariable Long id) {
        ShopItemListDto shopItems = shopItemService.getItemsByKingdomId(id);
        if (shopItems != null) {
            return ResponseEntity.ok(shopItems);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/buy/{id}")
    public ResponseEntity<?> buyItem(@PathVariable Long id) {
        BuyItemResponse buyItemResponse = shopItemService.buyItem(id);
        if (buyItemResponse != null) {
            return ResponseEntity.ok(buyItemResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getBoughtItems/{id}")
    public ResponseEntity<?> getBoughtItemsByKingdomId(@PathVariable Long id) {
        ShopItemListDto shopItems = shopItemService.getBoughtItemsByKingdomId(id);
        if (shopItems != null) {
            return ResponseEntity.ok(shopItems);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
