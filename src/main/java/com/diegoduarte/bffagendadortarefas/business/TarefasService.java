package com.diegoduarte.bffagendadortarefas.business;

import com.diegoduarte.bffagendadortarefas.business.DTO.in.TarefasDTORequest;
import com.diegoduarte.bffagendadortarefas.business.DTO.out.TarefasDTOResponse;
import com.diegoduarte.bffagendadortarefas.infrastructure.client.TarefasClient;
import com.diegoduarte.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;

    public TarefasDTOResponse gravarTarefa (String token, TarefasDTORequest tarefasDTO) {
        return tarefasClient.gravarTarefas(tarefasDTO, token);
    }

    public List<TarefasDTOResponse> buscaListaDeTarefasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token) {
        return tarefasClient.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail (String token){
        return tarefasClient.buscaTarefasPorEmail(token);
    }

    public void deletaPorId (String id, String token){
        tarefasClient.deletaPorId(id, token);
    }

    public TarefasDTOResponse alteraStatus (StatusNotificacaoEnum statusNotificacaoEnum, String id, String token) {
        return tarefasClient.alteraStatusNotificacao(statusNotificacaoEnum, id ,token);
    }

    public TarefasDTOResponse updateTarefas (TarefasDTORequest tarefasDTO, String id, String token) {
        return tarefasClient.updateTarefas(tarefasDTO, id, token);
    }
}
