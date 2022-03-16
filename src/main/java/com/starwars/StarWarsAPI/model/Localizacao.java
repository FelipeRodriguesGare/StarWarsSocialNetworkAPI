package com.starwars.StarWarsAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
public class Localizacao {
    private long latitude;
    private long longitude;
    private String nomeDaGalaxia;

    public Localizacao(String nomeDaGalaxia) {
        this.latitude = gerarCoordenadas();
        this.longitude = gerarCoordenadas();
        this.nomeDaGalaxia = nomeDaGalaxia;
    }

    private long gerarCoordenadas(){
        return (long) (1000000000000L + Math.random() * 8999999999999L);
    }
}
