package com.example.bakalaurinis.controller;

import com.example.bakalaurinis.security.dtos.ShopItemListDto;
import com.example.bakalaurinis.services.ShopItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
public class ShopItemController {
    private ShopItemService shopItemService;

    @Autowired
    public ShopItemController(ShopItemService shopItemService) {
        this.shopItemService = shopItemService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemsByKingdomIdById(@PathVariable Long id) {
        ShopItemListDto shopItems = shopItemService.getItemsByKingdomId(id);
        if (shopItems != null) {
            return ResponseEntity.ok(shopItems);
        } else {
            // Jeigu nerado grazina HTTP status 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }
}
