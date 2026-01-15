package com.diegoduarte.bffagendadortarefas.business.DTO.in;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class EnderecoDTORequest {

    private String rua;
    private Long numero;
    private String complemento;
    private String cep;
    private String estado;
    private String bairro;
}
