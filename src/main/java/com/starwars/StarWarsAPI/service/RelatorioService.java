package com.starwars.StarWarsAPI.service;

import com.starwars.StarWarsAPI.data_base.RebeldesDAO;
import com.starwars.StarWarsAPI.dto.RebeldeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.starwars.StarWarsAPI.StarWarsApiApplication;
import com.starwars.StarWarsAPI.model.Rebelde;

@Service
public class RelatorioService {

    @Autowired
    static
    RebeldeService rebeldeService;
    static RebeldeResponse rebeldeResponse = new RebeldeResponse();

    @Autowired
    @Qualifier("mysql")
    private RebeldesDAO rebeldesDAO;

    public static String traitorsReport(List<RebeldeResponse> listaRebeldes) {
        var qtdRebeldes = listaRebeldes.size();
        List<RebeldeResponse> listaTraidores = listaRebeldes.stream()
                .filter(RebeldeResponse::getTraidor).collect(Collectors.toList());
        var qtdTraidores = (double) listaTraidores.size();
        var traidoresPercent = (qtdTraidores*100)/(qtdRebeldes);
        return String.format("Porcentagem de traidores: %.2f%%", traidoresPercent);
    }

    public static String rebeldesReport(List<RebeldeResponse> listaRebeldes) {
        var qtdRebeldes = listaRebeldes.size();
        List<RebeldeResponse> listaTraidores = listaRebeldes.stream()
                .filter(RebeldeResponse::getTraidor).collect(Collectors.toList());
        var qtdTraidores = (double) listaTraidores.size();
        var rebeldePercent = ((qtdRebeldes - qtdTraidores)*100) / (qtdRebeldes);
        return String.format("Porcentagem de rebeldes: %.2f%%", rebeldePercent);
    }

    public static String recursosReport(List<RebeldeResponse> listaRebeldes) {
        List<RebeldeResponse> listaRebeldes2 = listaRebeldes.stream()
                .filter(RebeldeResponse -> RebeldeResponse.getTraidor() == false).collect(Collectors.toList());
        float mediaArma = 0, mediaMunicao = 0, mediaAgua = 0, mediaComida = 0;
        for (com.starwars.StarWarsAPI.dto.RebeldeResponse RebeldeResponse : listaRebeldes2) {
            mediaArma += RebeldeResponse.getInventario().getItems().get("arma").getQuantidade();
            mediaMunicao += RebeldeResponse.getInventario().getItems().get("municao").getQuantidade();
            mediaAgua += RebeldeResponse.getInventario().getItems().get("agua").getQuantidade();
            mediaComida += RebeldeResponse.getInventario().getItems().get("comida").getQuantidade();
        }
        var listSize = listaRebeldes2.size();
        mediaArma /= listSize;
        mediaMunicao /= listSize;
        mediaAgua /= listSize;
        mediaComida /= listSize;
        return String.format("Relatório de items: %.2f armas por rebelde; " +
                        "%.2f munições por rebelde; %.2f aguas por rebelde; %.2f comidas por rebelde; "
                ,mediaArma,mediaMunicao,mediaAgua,mediaComida);
    }

    public static String pontosReport(List<RebeldeResponse> listaRebeldes) {
        List<RebeldeResponse> listaTraidores = listaRebeldes.stream()
                .filter(RebeldeResponse::getTraidor).collect(Collectors.toList());
        int pontosPerdidos = 0;
        for (com.starwars.StarWarsAPI.dto.RebeldeResponse RebeldeResponse : listaTraidores) {
            pontosPerdidos += (RebeldeResponse.getInventario().getItems().get("arma").getQuantidade()*RebeldeResponse.getInventario().getItems().get("arma").getPontos()) + (RebeldeResponse.getInventario().getItems().get("municao").getQuantidade()*RebeldeResponse.getInventario().getItems().get("municao").getPontos()) +
                    (RebeldeResponse.getInventario().getItems().get("agua").getQuantidade()*RebeldeResponse.getInventario().getItems().get("agua").getPontos()) + (RebeldeResponse.getInventario().getItems().get("comida").getQuantidade()*RebeldeResponse.getInventario().getItems().get("comida").getPontos());
        }

        return "Foram perdidos "+pontosPerdidos+" pontos devido a traidores";
    }

}
