package com.diegoduarte.bffagendadortarefas.infrastructure.client;

import com.diegoduarte.bffagendadortarefas.business.DTO.in.TarefasDTORequest;
import com.diegoduarte.bffagendadortarefas.business.DTO.out.TarefasDTOResponse;
import com.diegoduarte.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTOResponse gravarTarefas (@RequestBody TarefasDTORequest tarefasDTO,
                                      @RequestHeader(value = "Authorization", required = false) String token);

    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscaListaDeTarefasPorPeriodo (
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(value = "Authorization", required = false) String token);

    @GetMapping
    List<TarefasDTOResponse> buscaTarefasPorEmail (@RequestHeader(value = "Authorization", required = false) String token);

    @DeleteMapping
    void deletaPorId (@RequestParam("id") String id,
                      @RequestHeader(value = "Authorization", required = false) String token);

    @PatchMapping
    TarefasDTOResponse alteraStatusNotificacao (@RequestParam("status") StatusNotificacaoEnum statusNotificacaoEnum,
                                               @RequestParam("id") String id,
                                               @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping
    TarefasDTOResponse updateTarefas (@RequestBody TarefasDTORequest tarefasDTO,
                                     @RequestParam("id") String id,
                                     @RequestHeader(value = "Authorization", required = false) String token);

}
