package com.starwars.StarWarsAPI.model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter @Setter
public class BDRebeldes {
    private static List<Rebelde> listRebeldes = new ArrayList<>();

    public List<Rebelde> listarRebeldes(){
        return listRebeldes;
    }

    public void addRebelde(Rebelde rebelde){
        listRebeldes.add(rebelde);
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
