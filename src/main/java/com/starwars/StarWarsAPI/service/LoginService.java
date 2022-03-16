package com.starwars.StarWarsAPI.service;

import com.starwars.StarWarsAPI.data_base.RebeldesDAO;
import com.starwars.StarWarsAPI.model.LoginRequest;
import com.starwars.StarWarsAPI.model.Rebelde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    RebeldesDAO rebeldesDAO;

    public Rebelde login(LoginRequest loginRequest) throws Exception {
        return rebeldesDAO.login(loginRequest);
    }
}
