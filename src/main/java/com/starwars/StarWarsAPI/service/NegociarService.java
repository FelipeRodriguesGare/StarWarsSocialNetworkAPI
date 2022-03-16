package com.starwars.StarWarsAPI.service;

import com.starwars.StarWarsAPI.StarWarsApiApplication;
import com.starwars.StarWarsAPI.dto.NegociarRequest;
import com.starwars.StarWarsAPI.model.Rebelde;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NegociarService {

    private Boolean checkRebelIsTraitor(Rebelde rebelde) {
        return rebelde.getTraidor();
    }

    private String checkTradeRequest(NegociarRequest negociarRequest) throws Exception {
        Rebelde remetente = StarWarsApiApplication.bdRebeldes.buscaRebelde(negociarRequest.getIdRemetente());
        Rebelde destinatario = StarWarsApiApplication.bdRebeldes.buscaRebelde(negociarRequest.getIdDestinatario());

        if(checkRebelIsTraitor(remetente)) {
            return "ERRO: O remetente é um traidor! Portanto está impossibilitado de realizar negociações.";
        } else if (checkRebelIsTraitor(destinatario)) {
            return "ERRO: O destinatário é um traidor! Portanto está impossibilitado de realizar negociações.";
        }

        int pontosRemetente = 0;
        int pontosDestinatario = 0;

        for (String item : negociarRequest.getItemRemetente()){
            if (
                    remetente.getInventario().getItems().get(item).getQuantidade() <
                            negociarRequest.getQtdItemRemetente().get(negociarRequest.getItemRemetente().indexOf(item))
            ){
                return "ERRO: O remetente não possui este item ou não possui a quantidade desejada!";
            }
            pontosRemetente += remetente.getInventario().getItems().get(item).getPontos()
                    * negociarRequest.getQtdItemRemetente().get(negociarRequest.getItemRemetente().indexOf(item));
        }

        for (String item : negociarRequest.getItemDestinatario()){
            if (
                    destinatario.getInventario().getItems().get(item).getQuantidade() <
                            negociarRequest.getQtdItemDestinatario().get(negociarRequest.getItemDestinatario().indexOf(item))
            ){
                return "ERRO: O destinatário não possui este item ou não possui a quantidade desejada!";
            }
            pontosDestinatario += destinatario.getInventario().getItems().get(item).getPontos()
                    * negociarRequest.getQtdItemDestinatario().get(negociarRequest.getItemDestinatario().indexOf(item));

        }

        if (pontosRemetente == pontosDestinatario) {
            return "Válido";
        } else {
            return "Os rebeldes não possuem a mesma quantidade de pontos!";
        }

    }

    public String negociar(NegociarRequest negociarRequest) throws Exception{
        String checkTradeRequest = checkTradeRequest(negociarRequest);
        if(checkTradeRequest.equals("valido")){
            Rebelde remetente = StarWarsApiApplication.bdRebeldes.buscaRebelde(negociarRequest.getIdRemetente());
            Rebelde destinatario = StarWarsApiApplication.bdRebeldes.buscaRebelde(negociarRequest.getIdDestinatario());

            for(String item : negociarRequest.getItemRemetente()){
                int qteSubtrai = remetente.getInventario().getItems().get(item).getQuantidade()
                        - negociarRequest.getQtdItemRemetente().get(negociarRequest.getItemRemetente().indexOf(item));

                int qteSoma = destinatario.getInventario().getItems().get(item).getQuantidade()
                        + negociarRequest.getQtdItemRemetente().get(negociarRequest.getItemRemetente().indexOf(item));

                remetente.getInventario().getItems().get(item).setQuantidade(qteSubtrai);
                destinatario.getInventario().getItems().get(item).setQuantidade(qteSoma);
            }

            for(String item : negociarRequest.getItemDestinatario()){
                int qteSubtrai = destinatario.getInventario().getItems().get(item).getQuantidade()
                        - negociarRequest.getQtdItemDestinatario().get(negociarRequest.getItemDestinatario().indexOf(item));

                int qteSoma = remetente.getInventario().getItems().get(item).getQuantidade()
                        + negociarRequest.getQtdItemDestinatario().get(negociarRequest.getItemDestinatario().indexOf(item));

                destinatario.getInventario().getItems().get(item).setQuantidade(qteSubtrai);
                remetente.getInventario().getItems().get(item).setQuantidade(qteSoma);
            }
            return "Troca Realizada com Sucesso!";
        }

        return checkTradeRequest;
    }
}
