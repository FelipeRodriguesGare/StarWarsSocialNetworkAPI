package com.starwars.StarWarsAPI.service;

import com.starwars.StarWarsAPI.StarWarsApiApplication;
import com.starwars.StarWarsAPI.dto.LocalizacaoRequest;
import com.starwars.StarWarsAPI.dto.RebeldeRequest;
import com.starwars.StarWarsAPI.model.BDRebeldes;
import com.starwars.StarWarsAPI.model.Localizacao;
import com.starwars.StarWarsAPI.model.Rebelde;

import java.util.UUID;

public class RebeldeService {

    public Rebelde criaRebelde(RebeldeRequest rebeldeRequest) {
        Localizacao localizacao = new Localizacao(rebeldeRequest.getLocalizacao().getNomeDaGalaxia());
        Rebelde rebelde = new Rebelde(rebeldeRequest.getNome(), rebeldeRequest.getIdade(), rebeldeRequest.getGenero(), localizacao);
        StarWarsApiApplication.bdRebeldes.addRebelde(rebelde);
        return rebelde;
    }

    public Rebelde atualizarLocalizacao(LocalizacaoRequest localizacaoRequest, UUID id) throws Exception {
        Rebelde rebelde = StarWarsApiApplication.bdRebeldes.buscaRebelde(id);
        Localizacao localizacaoAtual = rebelde.getLocalizacao();

        localizacaoAtual.setLongitude(localizacaoRequest.getLongitude());
        localizacaoAtual.setLatitude(localizacaoRequest.getLatitude());
        localizacaoAtual.setNomeDaGalaxia(localizacaoRequest.getNomeDaGalaxia());

        return rebelde;
    }
}
