package com.diegoduarte.bffagendadortarefas.business.DTO.in;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TelefoneDTORequest {

    private String numero;
    private String ddd;
}
