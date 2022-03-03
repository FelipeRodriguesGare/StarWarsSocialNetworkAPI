package com.starwars.StarWarsAPI.dto;

import com.starwars.StarWarsAPI.model.Localizacao;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RebeldeRequest {
    String nome;
    int idade;
    String genero;
    LocalizacaoRequest localizacao;
}
