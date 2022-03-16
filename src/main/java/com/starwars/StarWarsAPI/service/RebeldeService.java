package com.starwars.StarWarsAPI.service;

import com.starwars.StarWarsAPI.data_base.RebeldesDAO;
import com.starwars.StarWarsAPI.dto.LocalizacaoRequest;
import com.starwars.StarWarsAPI.dto.RebeldeRequest;
import com.starwars.StarWarsAPI.model.Inventario;
import com.starwars.StarWarsAPI.model.Localizacao;
import com.starwars.StarWarsAPI.model.Rebelde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RebeldeService {

    @Autowired
    @Qualifier("mysql")
    private RebeldesDAO rebeldesDAO;

    public Rebelde criaRebelde(RebeldeRequest rebeldeRequest) {
        Localizacao localizacao = new Localizacao(rebeldeRequest.getLocalizacao().getNomeDaGalaxia());

        Rebelde rebelde = new Rebelde(
                rebeldeRequest.getNome(),
                rebeldeRequest.getUsername(),
                rebeldeRequest.getSenha(),
                rebeldeRequest.getAvatar(),
                rebeldeRequest.getIdade(),
                rebeldeRequest.getGenero(),
                localizacao
        );

        rebeldesDAO.writeToDB(rebelde);
        return rebelde;
    }

    public Rebelde atualizarLocalizacao(LocalizacaoRequest localizacaoRequest, UUID id) throws Exception {
        Rebelde rebelde = rebeldesDAO.getRebeldeByID(id);
        Localizacao localizacaoAtual = rebelde.getLocalizacao();

        localizacaoAtual.setLongitude(localizacaoRequest.getLongitude());
        localizacaoAtual.setLatitude(localizacaoRequest.getLatitude());
        localizacaoAtual.setNomeDaGalaxia(localizacaoRequest.getNomeDaGalaxia());
        rebeldesDAO.updateRebelde(rebelde);
        return rebelde;
    }

    public Rebelde reportarTraidor(UUID id) throws Exception{
        Rebelde rebelde = rebeldesDAO.getRebeldeByID(id);
        rebelde.reportTraidor();
        rebeldesDAO.updateRebelde(rebelde);
        return rebelde;
    }

    public List<Rebelde> listarRebeldes(){
        return rebeldesDAO.getAll();
    }

    public Rebelde buscaRebelde(UUID id){
        return rebeldesDAO.getRebeldeByID(id);
    }

    public void removeRebelde(UUID id){
        rebeldesDAO.removeRebelde(id);
    }

}