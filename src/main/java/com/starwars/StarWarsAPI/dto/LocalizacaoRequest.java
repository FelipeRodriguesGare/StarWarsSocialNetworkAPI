package com.starwars.StarWarsAPI.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class LocalizacaoRequest {
    String nomeDaGalaxia;
    Long latitude;
    Long longitude;
}
