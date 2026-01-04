package com.diegoduarte.bffagendadortarefas.business.DTO.in;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTORequest {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTORequest> endereco;
    private List<TelefoneDTORequest> telefone;
}
