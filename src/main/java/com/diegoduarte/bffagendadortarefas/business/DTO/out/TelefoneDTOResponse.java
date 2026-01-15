package com.diegoduarte.bffagendadortarefas.business.DTO.out;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TelefoneDTOResponse {

    private Long id;
    private String numero;
    private String ddd;
}
