package com.diegoduarte.bffagendadortarefas.infrastructure.client;

import com.diegoduarte.bffagendadortarefas.business.DTO.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

    @PostMapping
    void enviaEmail (@RequestBody TarefasDTOResponse tarefasDTO);
}
