package com.starwars.StarWarsAPI.ServiceTest;

import com.starwars.StarWarsAPI.dto.LocalizacaoRequest;
import com.starwars.StarWarsAPI.dto.NegociarRequest;
import com.starwars.StarWarsAPI.dto.RebeldeRequest;
import com.starwars.StarWarsAPI.model.Rebelde;
import com.starwars.StarWarsAPI.service.NegociarService;
import com.starwars.StarWarsAPI.service.RebeldeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class NegociarServiceTest {

    NegociarService negociarService = new NegociarService();

    RebeldeService rebeldeService = new RebeldeService();

    @Test
    void RebeldesTest() throws Exception {
        LocalizacaoRequest localizacao = new LocalizacaoRequest();
        localizacao.setNomeDaGalaxia("testeGaláxia");
        localizacao.setLongitude(0L);
        localizacao.setLatitude(0L);
        RebeldeRequest rebelde = new RebeldeRequest("teste","1234","nomeTeste","Avatar1",10,"masculino", localizacao);

        Rebelde remetente = rebeldeService.criaRebelde(rebelde);
        Rebelde destinatario = rebeldeService.criaRebelde(rebelde);

        remetente.getInventario().getItems().get("arma").setQuantidade(1);
        remetente.getInventario().getItems().get("agua").setQuantidade(0);
        destinatario.getInventario().getItems().get("agua").setQuantidade(2);
        destinatario.getInventario().getItems().get("arma").setQuantidade(0);

        List<String> itemsRemetente = new ArrayList<>();
        List<String> itemsDestinatario = new ArrayList<>();
        List<Integer> itemsQTERemetente = new ArrayList<>();
        List<Integer> itemsQTEDestinatario = new ArrayList<>();

        itemsRemetente.add("arma");
        itemsQTERemetente.add(1);
        itemsDestinatario.add("agua");
        itemsQTEDestinatario.add(2);

        NegociarRequest negociarRequest = new NegociarRequest(
                remetente.getId(),
                destinatario.getId(),
                itemsRemetente,
                itemsDestinatario,
                itemsQTERemetente,
                itemsQTEDestinatario
        );

        String text = negociarService.negociar(negociarRequest);
        System.out.println(text);
        Assertions.assertEquals(0,remetente.getInventario().getItems().get("arma").getQuantidade());
        Assertions.assertEquals(2,remetente.getInventario().getItems().get("agua").getQuantidade());
        Assertions.assertEquals(0,destinatario.getInventario().getItems().get("agua").getQuantidade());
        Assertions.assertEquals(1,destinatario.getInventario().getItems().get("arma").getQuantidade());

    }
}
