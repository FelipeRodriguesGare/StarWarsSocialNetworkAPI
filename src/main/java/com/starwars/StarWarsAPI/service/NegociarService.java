package com.starwars.StarWarsAPI.service;

import com.starwars.StarWarsAPI.data_base.RebeldesDAO;
import com.starwars.StarWarsAPI.dto.NegociarRequest;
import com.starwars.StarWarsAPI.model.Rebelde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NegociarService {

    @Autowired
    @Qualifier("mysql")
    private RebeldesDAO rebeldesDAO;

    private Boolean checkRebelIsTraitor(Rebelde rebelde) throws Exception {
        return rebelde.getTraidor();
    }

    private String checkTradeRequest(NegociarRequest negociarRequest) throws Exception {
        Rebelde remetente = rebeldesDAO.getRebeldeByID(negociarRequest.getIdRemetente());
        Rebelde destinatario = rebeldesDAO.getRebeldeByID(negociarRequest.getIdDestinatario());

        if(checkRebelIsTraitor(remetente)) {
            return "O remetente é um traidor! Portanto está impossibilitado de realizar negociações.";
        } else if (checkRebelIsTraitor(destinatario)) {
            return "O destinatário é um traidor! Portanto está impossibilitado de realizar negociações.";
        }

        int pontosRemetente = 0;
        int pontosDestinatario = 0;

        for (String item : negociarRequest.getItemRemetente()){
            if (
                    remetente.getInventario().getItems().get(item).getQuantidade() <
                            negociarRequest.getQtdItemRemetente().get(negociarRequest.getItemRemetente().indexOf(item))
            ){
                return "O remetente não possui este item ou não possui a quantidade desejada!";
            }
            pontosRemetente += remetente.getInventario().getItems().get(item).getPontos()
                    * negociarRequest.getQtdItemRemetente().get(negociarRequest.getItemRemetente().indexOf(item));
        }

        for (String item : negociarRequest.getItemDestinatario()){
            if (
                    destinatario.getInventario().getItems().get(item).getQuantidade() <
                            negociarRequest.getQtdItemDestinatario().get(negociarRequest.getItemDestinatario().indexOf(item))
            ){
                return "O destinatário não possui este item ou não possui a quantidade desejada!";
            }
            pontosDestinatario += destinatario.getInventario().getItems().get(item).getPontos()
                    * negociarRequest.getQtdItemDestinatario().get(negociarRequest.getItemDestinatario().indexOf(item));

        }

        if (pontosRemetente == pontosDestinatario) {
            return "valido";
        } else {
            return "Os rebeldes não possuem a mesma quantidade de pontos!";
        }

    }

    public String negociar(NegociarRequest negociarRequest) throws Exception{
        String checkTradeRequest = checkTradeRequest(negociarRequest);
        if(checkTradeRequest == "valido"){
            Rebelde remetente = rebeldesDAO.getRebeldeByID(negociarRequest.getIdRemetente());
            Rebelde destinatario = rebeldesDAO.getRebeldeByID(negociarRequest.getIdDestinatario());

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
            rebeldesDAO.updateInventarioDB(remetente);
            rebeldesDAO.updateInventarioDB(destinatario);
            return "Troca Realizada com Sucesso!";
        }

        return checkTradeRequest;
    }


}
