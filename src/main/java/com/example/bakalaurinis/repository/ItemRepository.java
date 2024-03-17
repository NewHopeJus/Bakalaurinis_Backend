package com.example.bakalaurinis.repository;

import com.example.bakalaurinis.model.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ShopItem, Long> {

    List<ShopItem> getShopItemsByKingdom_Id(Long kingdomId);
}
