package com.starwars.StarWarsAPI;

import com.starwars.StarWarsAPI.service.RebeldeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StarWarsApiApplication {
    public static RebeldeService rebeldesDAO;

    public static void main(String[] args) {
		SpringApplication.run(StarWarsApiApplication.class, args);
	}
}
