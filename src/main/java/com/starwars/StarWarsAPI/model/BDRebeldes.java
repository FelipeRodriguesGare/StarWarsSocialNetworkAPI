package com.starwars.StarWarsAPI.model;

import com.starwars.StarWarsAPI.data_base.RebeldesDAO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Getter @Setter
public class BDRebeldes {
    private static List<Rebelde> listRebeldes = new ArrayList<>();

    @Autowired
    @Qualifier("mysql")
    private RebeldesDAO rebeldesDAO;

    public List<Rebelde> listarRebeldes(){
        return listRebeldes;
    }

    public void addRebelde(Rebelde rebelde){
        listRebeldes.add(rebelde);
        rebeldesDAO.writeToDB(rebelde);
    }

    public Rebelde buscaRebelde(UUID id) throws Exception {
        Optional<Rebelde> rebeldeResult = listRebeldes.stream().filter(rebelde -> Objects.equals(rebelde.getId(), id)).findAny();
        if(rebeldeResult.isPresent()){
            return rebeldeResult.get();
        }else {
            throw new Exception("Rebelde não encontrado");
        }
    }
}
