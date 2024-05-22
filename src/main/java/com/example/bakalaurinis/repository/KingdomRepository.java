package com.example.bakalaurinis.repository;

import com.example.bakalaurinis.model.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KingdomRepository extends JpaRepository<Kingdom, Long> {
    @Query(value = "SELECT * from kingdoms where id  NOT IN (SELECT opened_kingdoms_id from users_opened_kingdoms where user_id = ?)",
            nativeQuery = true)
    List<Kingdom> getClosedKingdoms(Long userId);

    @Query(value = "SELECT * from kingdoms where id IN (SELECT opened_kingdoms_id from users_opened_kingdoms where user_id = ?)",
            nativeQuery = true)
    List<Kingdom> getOpenedKingdoms(Long userId);
}
