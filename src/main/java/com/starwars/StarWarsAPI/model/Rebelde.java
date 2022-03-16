package com.starwars.StarWarsAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
public class Rebelde {
    private UUID id;
    private String username;
    private String senha;
    private String nome;
    private String avatar;
    private int idade;
    private String genero;
    private Boolean traidor;
    private Localizacao localizacao;
    private Inventario inventario;

    int contReportTraidor;

    public Rebelde(String nome, String username, String senha, String avatar,int idade, String genero, Localizacao localizacao) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.senha = senha;
        this.avatar = avatar;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.traidor = false;
        this.localizacao = localizacao;
        this.inventario = new Inventario();
        this.contReportTraidor = 0;
    }

    public Rebelde(UUID id, String username, String senha, String nome, String avatar, int idade, String genero, boolean traidor, Localizacao localizacao, Inventario inventario, int contReportTraidor){
        this.id=id;
        this.username=username;
        this.senha=senha;
        this.nome=nome;
        this.avatar=avatar;
        this.idade=idade;
        this.genero=genero;
        this.traidor=traidor;
        this.localizacao=localizacao;
        this.inventario=inventario;
        this.contReportTraidor = contReportTraidor;
    }

    public void reportTraidor(){
        this.contReportTraidor++;
        if(this.contReportTraidor >= 3){
            this.traidor = true;
        }
    }

}
