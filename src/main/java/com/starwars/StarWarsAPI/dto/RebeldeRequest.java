package com.starwars.StarWarsAPI.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RebeldeRequest {
    String nome;
    int idade;
    String genero;
    String nomeDaGalaxia;
}
