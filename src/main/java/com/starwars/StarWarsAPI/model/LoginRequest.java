package com.starwars.StarWarsAPI.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {
    String username;
    String senha;
}
