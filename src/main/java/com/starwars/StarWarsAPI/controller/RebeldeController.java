package com.starwars.StarWarsAPI.controller;

import com.starwars.StarWarsAPI.StarWarsApiApplication;
import com.starwars.StarWarsAPI.dto.LocalizacaoRequest;
import com.starwars.StarWarsAPI.dto.NegociarRequest;
import com.starwars.StarWarsAPI.dto.RebeldeRequest;
import com.starwars.StarWarsAPI.dto.RebeldeResponse;
import com.starwars.StarWarsAPI.exceptions.ResponseMessege;
import com.starwars.StarWarsAPI.model.Rebelde;
import com.starwars.StarWarsAPI.service.NegociarService;
import com.starwars.StarWarsAPI.service.RebeldeService;
import com.starwars.StarWarsAPI.service.relatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/rebeldes")
public class RebeldeController {
    RebeldeService rebeldeService = new RebeldeService();
    RebeldeResponse rebeldeResponse = new RebeldeResponse();
    @Autowired
    NegociarService negociarService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Object> buscarRebeldes(){
        List<RebeldeResponse> rebeldeResponse2 = rebeldeResponse.toResponse(StarWarsApiApplication.bdRebeldes.listarRebeldes());
        if(rebeldeResponse2.isEmpty()){
            return new ResponseEntity<>(new ResponseMessege("Não há registro."), HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok().body(rebeldeResponse2);
    }

    @PostMapping
    public ResponseEntity<RebeldeResponse> criaRebelde(@RequestBody @Valid RebeldeRequest rebeldeRequest, UriComponentsBuilder uriComponentsBuilder){
        Rebelde rebelde = rebeldeService.criaRebelde(rebeldeRequest);
        URI uri = uriComponentsBuilder.path("/rebeldes/{id}").buildAndExpand(rebelde.getId()).toUri();
        return ResponseEntity.created(uri).body(new RebeldeResponse(rebelde));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> buscaRebelde(@PathVariable UUID id) throws Exception {
        try{
            Rebelde rebelde = StarWarsApiApplication.bdRebeldes.buscaRebelde(id);
            return ResponseEntity.accepted().body(new RebeldeResponse(rebelde));
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessege("Registro não encontrado."), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/atualizarlocalizacao/{id}")
    @ResponseBody
    public ResponseEntity<Object> atualizarLocalizacao(@PathVariable UUID id, @RequestBody LocalizacaoRequest localizacaoRequest) throws Exception {
       try{
           Rebelde rebelde =  rebeldeService.atualizarLocalizacao(localizacaoRequest,id);
           return ResponseEntity.ok().body(new RebeldeResponse(rebelde));
       }catch (Exception e){
           return new ResponseEntity<>(new ResponseMessege("Registro não encontrado."), HttpStatus.NOT_FOUND);
       }

    }

    @PatchMapping("/traidor/{id}")
    @ResponseBody
    public ResponseEntity<RebeldeResponse> reportarTraidor(@PathVariable UUID id) throws Exception {
        Rebelde rebelde =  rebeldeService.reportarTraidor(id);
        return ResponseEntity.ok().body(new RebeldeResponse(rebelde));
    }

    @PatchMapping("/negociar")
    @ResponseBody
    public ResponseEntity<String> negociar(@RequestBody NegociarRequest negociarRequest) throws Exception {
        return ResponseEntity.ok().body(negociarService.negociar(negociarRequest));
    }

    @GetMapping("/traidores")
    @ResponseBody
    public String traidores(){
        return relatorioService.traitorsReport();
    }
}
