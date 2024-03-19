package com.example.bakalaurinis.repository;

import com.example.bakalaurinis.model.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ShopItem, Long> {

    List<ShopItem> getShopItemsByKingdom_Id(Long kingdomId);

    ShopItem getShopItemById(Long id);


    @Query(value = "SELECT * from items where id IN (SELECT bought_items_id from users_bought_items where user_id = ?) and kingdom_id = ?",
            nativeQuery = true)
    List<ShopItem> getBoughtShopItemsForUser(Long userId, Long kingdomId);


}
