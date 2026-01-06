package com.diegoduarte.bffagendadortarefas.business;

import com.diegoduarte.bffagendadortarefas.business.DTO.out.TarefasDTOResponse;
import com.diegoduarte.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final EmailClient emailClient;

    public void enviaEmail (TarefasDTOResponse tarefasDTO) {
        emailClient.enviaEmail(tarefasDTO);
    }
}
