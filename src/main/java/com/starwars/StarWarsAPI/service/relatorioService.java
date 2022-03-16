package com.starwars.StarWarsAPI.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.starwars.StarWarsAPI.StarWarsApiApplication;
import com.starwars.StarWarsAPI.model.Rebelde;

@Service
public class relatorioService {

    public static String traitorsReport() {
        List<Rebelde> listaRebeldes = StarWarsApiApplication.bdRebeldes.listarRebeldes();
        var qtdRebeldes = listaRebeldes.size();
        List<Rebelde> listaTraidores = listaRebeldes.stream()
                .filter(Rebelde::getTraidor).collect(Collectors.toList());
        var qtdTraidores = (double) listaTraidores.size();
        var traidoresPercent = (qtdTraidores*100)/(qtdRebeldes);
        return String.format("Porcentagem de traidores: %.2f%%", traidoresPercent);
    }

    public String rebeldesReport() {
        List<Rebelde> listaRebeldes = StarWarsApiApplication.bdRebeldes.listarRebeldes();
        var qtdRebeldes = listaRebeldes.size();
        List<Rebelde> listaTraidores = listaRebeldes.stream()
                .filter(Rebelde::getTraidor).collect(Collectors.toList());
        var qtdTraidores = (double) listaTraidores.size();
        var rebeldePercent = ((qtdRebeldes - qtdTraidores)*100) / (qtdRebeldes);
        return String.format("Porcentagem de rebeldes: %.2f%%", rebeldePercent);
    }

    public String pontosReport() {
        List<Rebelde> listaTraidores = StarWarsApiApplication.bdRebeldes.listarRebeldes().stream()
                .filter(Rebelde::getTraidor).collect(Collectors.toList());
        int pontosPerdidos = 0;
        for (Rebelde rebelde : listaTraidores) {
            //pontosPerdidos += StarWarsApiApplication.bdRebeldes.valorInventario(rebelde.getInventario());
        }
        return "Foram perdidos "+pontosPerdidos+" pontos devido a traidores";
    }

}
