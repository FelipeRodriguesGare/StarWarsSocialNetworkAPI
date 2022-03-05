package com.starwars.StarWarsAPI.dto;

import com.starwars.StarWarsAPI.model.Inventario;
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
    private String nome;
    private int idade;
    private String genero;
    private Boolean traidor;
    private Localizacao localizacao;
    private Inventario Inventario;

    public RebeldeResponse(Rebelde rebelde){
        this.id = rebelde.getId();
        this.nome = rebelde.getNome();
        this.idade = rebelde.getIdade();
        this.genero = rebelde.getGenero();
        this.traidor = rebelde.getTraidor();
        this.localizacao = rebelde.getLocalizacao();
        this.Inventario = rebelde.getInventario();
    }

    public List<RebeldeResponse> toResponse(List<Rebelde> rebelde) {
        return rebelde.stream().map(RebeldeResponse::new).collect(Collectors.toList());
    }
}
