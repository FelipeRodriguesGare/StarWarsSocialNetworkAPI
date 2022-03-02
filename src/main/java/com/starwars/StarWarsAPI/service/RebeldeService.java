package com.starwars.StarWarsAPI.service;

import com.starwars.StarWarsAPI.StarWarsApiApplication;
import com.starwars.StarWarsAPI.dto.RebeldeRequest;
import com.starwars.StarWarsAPI.model.BDRebeldes;
import com.starwars.StarWarsAPI.model.Localizacao;
import com.starwars.StarWarsAPI.model.Rebelde;

public class RebeldeService {

    public Rebelde criaRebelde(RebeldeRequest rebeldeRequest) {
        Localizacao localizacao = new Localizacao(rebeldeRequest.getNomeDaGalaxia());
        Rebelde rebelde = new Rebelde(rebeldeRequest.getNome(), rebeldeRequest.getIdade(), rebeldeRequest.getGenero(), localizacao);
        StarWarsApiApplication.bdRebeldes.addRebelde(rebelde);
        return rebelde;
    }
}
