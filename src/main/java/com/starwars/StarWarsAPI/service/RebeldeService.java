package com.starwars.StarWarsAPI.service;

import com.starwars.StarWarsAPI.StarWarsApiApplication;
import com.starwars.StarWarsAPI.dto.LocalizacaoRequest;
import com.starwars.StarWarsAPI.dto.NegociarRequest;
import com.starwars.StarWarsAPI.dto.RebeldeRequest;
import com.starwars.StarWarsAPI.model.BDRebeldes;
import com.starwars.StarWarsAPI.model.Localizacao;
import com.starwars.StarWarsAPI.model.Rebelde;

import java.util.HashMap;
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

    public Rebelde reportarTraidor(UUID id) throws Exception{
        Rebelde rebelde = StarWarsApiApplication.bdRebeldes.buscaRebelde(id);
        rebelde.reportTraidor();
        return rebelde;
    }

    public void negociar(NegociarRequest negociarRequest) throws Exception{
        Rebelde rebeldeRemetente = StarWarsApiApplication.bdRebeldes.buscaRebelde(negociarRequest.getIdRemetente());
        Rebelde rebeldeDestinatario = StarWarsApiApplication.bdRebeldes.buscaRebelde(negociarRequest.getIdDestinatario());

        HashMap<String,Integer> itensRemetente = new HashMap<>();
        for(int i = 0;i<negociarRequest.getItemRemetente().size();i++){
            itensRemetente.put(negociarRequest.getItemRemetente().get(i),negociarRequest.getQtdItemRemetente().get(i));
        }

        HashMap<String,Integer> itensDestinatario = new HashMap<>();
        for(int i = 0;i<negociarRequest.getItemDestinatario().size();i++){
            itensDestinatario.put(negociarRequest.getItemDestinatario().get(i),negociarRequest.getQtdItemDestinatario().get(i));
        }


    }
}

