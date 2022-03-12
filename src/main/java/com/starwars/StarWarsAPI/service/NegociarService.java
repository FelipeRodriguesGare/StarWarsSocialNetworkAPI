package com.starwars.StarWarsAPI.service;

import com.starwars.StarWarsAPI.StarWarsApiApplication;
import com.starwars.StarWarsAPI.dto.NegociarRequest;
import com.starwars.StarWarsAPI.model.Rebelde;
import org.springframework.stereotype.Service;

@Service
public class NegociarService {

    private boolean checkTradeRequest(NegociarRequest negociarRequest) throws Exception {
        Rebelde remetente = StarWarsApiApplication.bdRebeldes.buscaRebelde(negociarRequest.getIdRemetente());
        Rebelde destinatario = StarWarsApiApplication.bdRebeldes.buscaRebelde(negociarRequest.getIdDestinatario());

        int pontosRemetente = 0;
        int pontosDestinatario = 0;

        for (String item : negociarRequest.getItemRemetente()){
            if (
                    remetente.getInventario().getItems().get(item).getQuantidade() <
                            negociarRequest.getQtdItemRemetente().get(negociarRequest.getItemRemetente().indexOf(item))
            ){
                return false;
            }
            pontosRemetente += remetente.getInventario().getItems().get(item).getPontos()
                    * negociarRequest.getQtdItemRemetente().get(negociarRequest.getItemRemetente().indexOf(item));
        }

        for (String item : negociarRequest.getItemDestinatario()){
            if (
                    destinatario.getInventario().getItems().get(item).getQuantidade() <
                            negociarRequest.getQtdItemDestinatario().get(negociarRequest.getItemDestinatario().indexOf(item))
            ){
                return false;
            }
            pontosDestinatario += destinatario.getInventario().getItems().get(item).getPontos()
                    * negociarRequest.getQtdItemDestinatario().get(negociarRequest.getItemDestinatario().indexOf(item));

        }

        return pontosRemetente == pontosDestinatario;
    }

    public String negociar(NegociarRequest negociarRequest) throws Exception{
        if(checkTradeRequest(negociarRequest)){
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

        return "Erro na Troca";
    }
}
