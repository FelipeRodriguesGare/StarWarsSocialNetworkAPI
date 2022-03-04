package com.starwars.StarWarsAPI.controller;

import com.starwars.StarWarsAPI.StarWarsApiApplication;
import com.starwars.StarWarsAPI.dto.LocalizacaoRequest;
import com.starwars.StarWarsAPI.dto.RebeldeRequest;
import com.starwars.StarWarsAPI.dto.RebeldeResponse;
import com.starwars.StarWarsAPI.model.Localizacao;
import com.starwars.StarWarsAPI.model.Rebelde;
import com.starwars.StarWarsAPI.service.RebeldeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/rebeldes")
public class RebeldeController {
    RebeldeService rebeldeService = new RebeldeService();
    RebeldeResponse rebeldeResponse = new RebeldeResponse();

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<RebeldeResponse>> buscarRebeldes(){
        return ResponseEntity.ok(rebeldeResponse.toResponse(StarWarsApiApplication.bdRebeldes.listarRebeldes()));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<RebeldeResponse> criaRebelde(@RequestBody RebeldeRequest rebeldeRequest, UriComponentsBuilder uriComponentsBuilder){
        Rebelde rebelde = rebeldeService.criaRebelde(rebeldeRequest);
        URI uri = uriComponentsBuilder.path("/rebeldes/{id}").buildAndExpand(rebelde.getId()).toUri();
        return ResponseEntity.created(uri).body(new RebeldeResponse(rebelde));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RebeldeResponse> buscaRebelde(@PathVariable UUID id) throws Exception {
        Rebelde rebelde = StarWarsApiApplication.bdRebeldes.buscaRebelde(id);
        return ResponseEntity.accepted().body(new RebeldeResponse(rebelde));
    }

    @PatchMapping("/atualizarlocalizacao/{id}")
    @ResponseBody
    public ResponseEntity<RebeldeResponse> atualizarLocalizacao(@PathVariable UUID id, @RequestBody LocalizacaoRequest localizacaoRequest) throws Exception {
        ;
       Rebelde rebelde =  rebeldeService.atualizarLocalizacao(localizacaoRequest,id);
       return ResponseEntity.ok().body(new RebeldeResponse(rebelde));
    }
}
