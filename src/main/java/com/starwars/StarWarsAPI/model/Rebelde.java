package com.starwars.StarWarsAPI.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Rebelde {
    private UUID id;
    String nome;
    int idade;
    String genero;
    Boolean traidor;
    Localizacao localizacao;

    int contReportTraidor;

    public Rebelde(String nome, int idade, String genero, Localizacao localizacao) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.traidor = false;
        this.localizacao = localizacao;
    }

    private void reportTraidor(){
        this.contReportTraidor++;
        if(this.contReportTraidor >= 3){
            this.traidor = true;
        }
    }

}
