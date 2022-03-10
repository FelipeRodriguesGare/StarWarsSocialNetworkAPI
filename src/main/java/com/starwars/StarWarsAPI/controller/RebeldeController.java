package com.starwars.StarWarsAPI.controller;

import com.starwars.StarWarsAPI.StarWarsApiApplication;
import com.starwars.StarWarsAPI.dto.LocalizacaoRequest;
import com.starwars.StarWarsAPI.dto.RebeldeRequest;
import com.starwars.StarWarsAPI.dto.RebeldeResponse;
import com.starwars.StarWarsAPI.model.Localizacao;
import com.starwars.StarWarsAPI.model.Rebelde;
import com.starwars.StarWarsAPI.service.RebeldeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/rebeldes")
public class RebeldeController {
    RebeldeService rebeldeService = new RebeldeService();
    RebeldeResponse rebeldeResponse = new RebeldeResponse();

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<RebeldeResponse>> buscarRebeldes(){
        List<RebeldeResponse> rebeldeResponse2 = rebeldeResponse.toResponse(StarWarsApiApplication.bdRebeldes.listarRebeldes());
        if(rebeldeResponse2.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(rebeldeResponse2);
        }
        return ResponseEntity.ok().body(rebeldeResponse2);
    }

    @PostMapping
    public ResponseEntity<RebeldeResponse> criaRebelde(@RequestBody @Valid RebeldeRequest rebeldeRequest, UriComponentsBuilder uriComponentsBuilder){
        try{
            Rebelde rebelde = rebeldeService.criaRebelde(rebeldeRequest);
            URI uri = uriComponentsBuilder.path("/rebeldes/{id}").buildAndExpand(rebelde.getId()).toUri();
            return ResponseEntity.created(uri).body(new RebeldeResponse(rebelde));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<RebeldeResponse> buscaRebelde(@PathVariable UUID id) throws Exception {
        try{
            Rebelde rebelde = StarWarsApiApplication.bdRebeldes.buscaRebelde(id);
            return ResponseEntity.accepted().body(new RebeldeResponse(rebelde));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("/atualizarlocalizacao/{id}")
    @ResponseBody
    public ResponseEntity<RebeldeResponse> atualizarLocalizacao(@PathVariable UUID id, @RequestBody LocalizacaoRequest localizacaoRequest) throws Exception {
       try{
           Rebelde rebelde =  rebeldeService.atualizarLocalizacao(localizacaoRequest,id);
           return ResponseEntity.ok().body(new RebeldeResponse(rebelde));
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }

    }

    @PatchMapping("/traidor/{id}")
    @ResponseBody
    public ResponseEntity<RebeldeResponse> reportarTraidor(@PathVariable UUID id) throws Exception {
        Rebelde rebelde =  rebeldeService.reportarTraidor(id);
        return ResponseEntity.ok().body(new RebeldeResponse(rebelde));
    }
}
