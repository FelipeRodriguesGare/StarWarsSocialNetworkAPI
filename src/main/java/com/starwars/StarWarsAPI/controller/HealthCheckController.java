package com.starwars.StarWarsAPI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/health-check")
public class HealthCheckController {

    @GetMapping()
    @ResponseBody
    public String healthCheck(){
        return "Server Running!";
    }

}