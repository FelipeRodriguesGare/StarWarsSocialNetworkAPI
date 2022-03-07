package com.starwars.StarWarsAPI.dto;

import com.starwars.StarWarsAPI.model.Localizacao;
import com.starwars.StarWarsAPI.utils.Genero;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class RebeldeRequest {
    @NotNull @NotEmpty @Length(min=2)
    private String nome;
    @NotNull @NotEmpty
    private int idade;
    @Genero
    private String genero;
    private LocalizacaoRequest localizacao;
}
