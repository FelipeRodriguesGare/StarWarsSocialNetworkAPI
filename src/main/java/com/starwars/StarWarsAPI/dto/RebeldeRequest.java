package com.starwars.StarWarsAPI.dto;

import com.starwars.StarWarsAPI.model.Localizacao;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RebeldeRequest {
    private String nome;
    private int idade;
    private String genero;
    private LocalizacaoRequest localizacao;
}
