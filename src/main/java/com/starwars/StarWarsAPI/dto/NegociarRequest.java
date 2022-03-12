package com.starwars.StarWarsAPI.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class NegociarRequest {
    UUID idRemetente;
    UUID idDestinatario;
    List<String> itemRemetente;
    List<String> itemDestinatario;
    List<Integer> qtdItemRemetente;
    List<Integer> qtdItemDestinatario;
}
