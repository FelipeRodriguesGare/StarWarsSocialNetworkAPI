package com.starwars.StarWarsAPI;

import com.starwars.StarWarsAPI.data_base.RebeldesDAO;
import com.starwars.StarWarsAPI.model.BDRebeldes;
import com.starwars.StarWarsAPI.model.Rebelde;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootApplication
public class StarWarsApiApplication {
	public static BDRebeldes bdRebeldes = new BDRebeldes();
	public static void main(String[] args) {
		SpringApplication.run(StarWarsApiApplication.class, args);
	}
}
