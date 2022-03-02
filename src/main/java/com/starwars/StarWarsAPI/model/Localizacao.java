package com.starwars.StarWarsAPI.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Localizacao {
    UUID id;
    long latitude;
    long longitude;
    String nomeDaGalaxia;

    public Localizacao(String nomeDaGalaxia) {
        this.id = UUID.randomUUID();
        this.latitude = gerarCoordenadas();
        this.longitude = gerarCoordenadas();
        this.nomeDaGalaxia = nomeDaGalaxia;
    }

    private long gerarCoordenadas(){
        return (long) (1000000000000L + Math.random() * 8999999999999L);
    }
}
