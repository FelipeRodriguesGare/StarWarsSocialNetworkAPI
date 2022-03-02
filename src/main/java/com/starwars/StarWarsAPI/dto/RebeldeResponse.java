package com.starwars.StarWarsAPI.dto;

import com.starwars.StarWarsAPI.model.Localizacao;
import com.starwars.StarWarsAPI.model.Rebelde;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter @Setter @NoArgsConstructor
public class RebeldeResponse {
    private UUID id;
    String nome;
    int idade;
    String genero;
    Boolean traidor;
    Localizacao localizacao;

    public RebeldeResponse(Rebelde rebelde){
        this.id = rebelde.getId();
        this.nome = rebelde.getNome();
        this.idade = rebelde.getIdade();
        this.genero = rebelde.getGenero();
        this.traidor = rebelde.getTraidor();
        this.localizacao = rebelde.getLocalizacao();
    }

    public List<RebeldeResponse> toResponse(List<Rebelde> rebelde) {
        return rebelde.stream().map(rebelde1 -> new RebeldeResponse(rebelde1)).collect(Collectors.toList());
    }
}
