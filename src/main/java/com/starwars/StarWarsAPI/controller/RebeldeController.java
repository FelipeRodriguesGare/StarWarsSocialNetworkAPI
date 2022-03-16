package com.starwars.StarWarsAPI.controller;

import com.starwars.StarWarsAPI.dto.LocalizacaoRequest;
import com.starwars.StarWarsAPI.dto.NegociarRequest;
import com.starwars.StarWarsAPI.dto.RebeldeRequest;
import com.starwars.StarWarsAPI.dto.RebeldeResponse;
import com.starwars.StarWarsAPI.exceptions.ResponseMessege;
import com.starwars.StarWarsAPI.model.Rebelde;
import com.starwars.StarWarsAPI.service.NegociarService;
import com.starwars.StarWarsAPI.service.RebeldeService;
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
    @Autowired
    RebeldeService rebeldeService;
    RebeldeResponse rebeldeResponse = new RebeldeResponse();
    @Autowired
    NegociarService negociarService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Object> buscarRebeldes(){
        List<RebeldeResponse> rebeldeResponse2 = rebeldeResponse.toResponse(rebeldeService.listarRebeldes());
        if(rebeldeResponse2.isEmpty()){
            return new ResponseEntity<>(new ResponseMessege("Não há registro."), HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok().body(rebeldeResponse2);
    }

    @PostMapping
    public ResponseEntity<Object> criaRebelde(@RequestBody @Valid RebeldeRequest rebeldeRequest, UriComponentsBuilder uriComponentsBuilder){
        try{
            Rebelde rebelde = rebeldeService.criaRebelde(rebeldeRequest);
            URI uri = uriComponentsBuilder.path("/rebeldes/{id}").buildAndExpand(rebelde.getId()).toUri();
            return ResponseEntity.created(uri).body(new RebeldeResponse(rebelde));
        } catch (Exception e){
            return new ResponseEntity<>(new ResponseMessege("Não foi possível criar um novo rebelde."), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> buscaRebelde(@PathVariable UUID id)  {
        try{
            Rebelde rebelde = rebeldeService.buscaRebelde(id);
            return ResponseEntity.accepted().body(new RebeldeResponse(rebelde));
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessege("Registro não encontrado."), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> removeRebelde(@PathVariable UUID id) throws Exception {
        rebeldeService.removeRebelde(id);
        return new ResponseEntity<>(new ResponseMessege("Registro Deletado."), HttpStatus.OK);
    }

    @PatchMapping("/atualizarlocalizacao/{id}")
    @ResponseBody
    public ResponseEntity<Object> atualizarLocalizacao(@PathVariable UUID id, @RequestBody LocalizacaoRequest localizacaoRequest) {
       try{
           Rebelde rebelde =  rebeldeService.atualizarLocalizacao(localizacaoRequest,id);
           return ResponseEntity.ok().body(new RebeldeResponse(rebelde));
       }catch (Exception e){
           return new ResponseEntity<>(new ResponseMessege("Registro não encontrado."), HttpStatus.NOT_FOUND);
       }

    }

    @PatchMapping("/traidor/{id}")
    @ResponseBody
    public ResponseEntity<Object> reportarTraidor(@PathVariable UUID id) throws Exception {
        Rebelde rebelde =  rebeldeService.reportarTraidor(id);
        return this.buscaRebelde(id).getStatusCode().is2xxSuccessful()? ResponseEntity.ok().body(new RebeldeResponse(rebelde)):new ResponseEntity<>(new ResponseMessege("Rebelde não encontrado para ser reportado."), HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/negociar")
    @ResponseBody
    public ResponseEntity<Object> negociar(@RequestBody NegociarRequest negociarRequest) throws Exception {
        String message = negociarService.negociar(negociarRequest);
        return message.contains("ERRO")? new ResponseEntity<>(new ResponseMessege("Registro não encontrado."), HttpStatus.BAD_REQUEST):ResponseEntity.ok().body(message);
    }
}
