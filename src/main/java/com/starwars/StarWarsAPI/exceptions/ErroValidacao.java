package com.starwars.StarWarsAPI.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ErroValidacao {
    private String filed;
    private String message;
}
