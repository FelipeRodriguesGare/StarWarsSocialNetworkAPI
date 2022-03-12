package com.starwars.StarWarsAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class NegociarRequest {
    UUID idRemetente;
    UUID idDestinatario;
    List<String> itemRemetente;
    List<String> itemDestinatario;
    List<Integer> qtdItemRemetente;
    List<Integer> qtdItemDestinatario;
}
