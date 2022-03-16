package com.starwars.StarWarsAPI.dto;

import com.starwars.StarWarsAPI.utils.Genero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
@AllArgsConstructor
public class RebeldeRequest {
    @NotNull @NotEmpty @Length(min=2)
    private String username;
    private String senha;
    private String nome;
    private String avatar;
    private int idade;
    @Genero
    private String genero;
    private LocalizacaoRequest localizacao;
}
