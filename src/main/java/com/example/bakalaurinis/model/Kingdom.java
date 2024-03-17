package com.example.bakalaurinis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kingdoms")
public class Kingdom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String img;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kingdom", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShopItem> shopItems;

}
