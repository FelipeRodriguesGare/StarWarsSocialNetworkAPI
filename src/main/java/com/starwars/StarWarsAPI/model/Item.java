package com.starwars.StarWarsAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Item {
    protected int pontos;
    private int quantidade;

    public Item(int pontos){
        this.pontos = pontos;
        this.quantidade = (int) (Math.random() * 9);
    }
}
