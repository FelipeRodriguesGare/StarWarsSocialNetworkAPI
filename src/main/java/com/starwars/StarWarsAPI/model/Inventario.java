package com.starwars.StarWarsAPI.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class Inventario {
    Map<String, Item> items= new HashMap<>();

    public Inventario () {
        this.items.put("arma", new Item(4));
        this.items.put("municao", new Item(3));
        this.items.put("agua", new Item(2));
        this.items.put("comida", new Item(1));
    }
}