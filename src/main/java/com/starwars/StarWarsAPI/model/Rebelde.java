package com.starwars.StarWarsAPI.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class Rebelde {
    private UUID id;
    private String nome;
    private int idade;
    private String genero;
    private Boolean traidor;
    private Localizacao localizacao;
    private Inventario inventario;

    int contReportTraidor;

    public Rebelde(String nome, int idade, String genero, Localizacao localizacao) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.traidor = false;
        this.localizacao = localizacao;
        this.inventario = new Inventario();
    }

    public void reportTraidor(){
        this.contReportTraidor++;
        if(this.contReportTraidor >= 3){
            this.traidor = true;
        }
    }

}
