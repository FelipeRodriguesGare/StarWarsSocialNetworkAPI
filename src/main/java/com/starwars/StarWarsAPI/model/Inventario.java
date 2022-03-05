package com.starwars.StarWarsAPI.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Inventario {
    private Item arma = new Item(4);
    private Item municao = new Item(3);
    private Item agua = new Item(2);
    private Item comida = new Item(1);
}