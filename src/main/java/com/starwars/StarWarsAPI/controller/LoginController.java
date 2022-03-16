package com.starwars.StarWarsAPI.controller;

import com.starwars.StarWarsAPI.dto.RebeldeResponse;
import com.starwars.StarWarsAPI.exceptions.ResponseMessege;
import com.starwars.StarWarsAPI.model.LoginRequest;
import com.starwars.StarWarsAPI.model.Rebelde;
import com.starwars.StarWarsAPI.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping
    public ResponseEntity<Object> loginRebelde(@RequestBody @Valid LoginRequest loginRequest) throws Exception {
        Rebelde rebelde = loginService.login(loginRequest);
        if (rebelde != null){
            return ResponseEntity.ok().body(new RebeldeResponse(rebelde));
        }
        else {
            return new ResponseEntity<>(new ResponseMessege("Informações Inválidas."), HttpStatus.NOT_FOUND);
        }
    }
}
