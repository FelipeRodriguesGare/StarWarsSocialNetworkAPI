package com.starwars.StarWarsAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Item {
    private int quantidade;
    protected int pontos;

    public Item(int pontos){
        this.pontos = pontos;
        this.quantidade = (int) (Math.random() * 9);
    }
}