package com.diegoduarte.bffagendadortarefas.controller;


import com.diegoduarte.bffagendadortarefas.business.DTO.in.TarefasDTORequest;
import com.diegoduarte.bffagendadortarefas.business.DTO.out.TarefasDTOResponse;
import com.diegoduarte.bffagendadortarefas.business.TarefasService;
import com.diegoduarte.bffagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.diegoduarte.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar Tarefas de Usuários", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salvo com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas (@RequestBody TarefasDTORequest tarefasDTO,
                                                             @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, tarefasDTO));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca Tarefas por Período", description = "Busca tarefas cadastradas por período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo (
            @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca Tarefas por Email", description = "Busca tarefas cadastradas por email")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Email não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail (@RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta Tarefas por Id", description = "Deleta tarefas cadastradas por Id")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<Void> deletaPorId (@RequestParam("id") String id,
                                             @RequestHeader(name = "Authorization", required = false) String token) {
        tarefasService.deletaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera Status de Tarefa", description = "Altera status de tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Status atualizado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao (@RequestParam("status") StatusNotificacaoEnum statusNotificacaoEnum,
                                                                      @RequestParam("id") String id,
                                                                      @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.alteraStatus(statusNotificacaoEnum, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera Dados de Tarefa", description = "Altera dados de tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefasDTOResponse> updateTarefas (@RequestBody TarefasDTORequest tarefasDTO,
                                                            @RequestParam("id") String id,
                                                            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.updateTarefas(tarefasDTO, id, token));
    }

}
