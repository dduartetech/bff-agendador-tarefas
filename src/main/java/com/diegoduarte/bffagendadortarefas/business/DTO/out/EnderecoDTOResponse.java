package com.diegoduarte.bffagendadortarefas.business.DTO.out;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class EnderecoDTOResponse {

    private Long id;
    private String rua;
    private Long numero;
    private String complemento;
    private String cep;
    private String estado;
    private String bairro;
}
