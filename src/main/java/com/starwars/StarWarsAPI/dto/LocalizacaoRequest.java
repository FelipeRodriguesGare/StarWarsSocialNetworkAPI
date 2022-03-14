package com.starwars.StarWarsAPI.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
public class LocalizacaoRequest {
    private String nomeDaGalaxia;
    private Long latitude;
    private Long longitude;
}
