package com.diegoduarte.bffagendadortarefas.business.DTO.in;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDTO {

    private String email;
    private String senha;
}
