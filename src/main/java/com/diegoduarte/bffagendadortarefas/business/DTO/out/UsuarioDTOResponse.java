package com.diegoduarte.bffagendadortarefas.business.DTO.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTOResponse {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTOResponse> endereco;
    private List<TelefoneDTOResponse> telefone;
}
