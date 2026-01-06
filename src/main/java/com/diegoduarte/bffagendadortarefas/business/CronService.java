package com.diegoduarte.bffagendadortarefas.business;

import com.diegoduarte.bffagendadortarefas.business.DTO.in.LoginRequestDTO;
import com.diegoduarte.bffagendadortarefas.business.DTO.out.TarefasDTOResponse;
import com.diegoduarte.bffagendadortarefas.infrastructure.enums.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefasService tarefasService;
    private final UsuarioService usuarioService;
    private final NotificacaoService notificacaoService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;


    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProxHora () {
        String token = login(converterParaRequestDTO());
        log.info("Iniciada a busca de tarefas");
        LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        //Qualquer tarefa entre HORA ATUAL + 1 (22h - 23h)

        List<TarefasDTOResponse> listaTarefas = tarefasService.buscaListaDeTarefasPorPeriodo(
                horaAtual, horaFutura, token);
        log.info("Tarefas encontradas: " + listaTarefas);

        listaTarefas.forEach(tarefa -> { notificacaoService.enviaEmail(tarefa);
            log.info("Email enviado para o usuário: " + tarefa.getEmailUsuario());
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
        });

        log.info("Finalizada a busca e notificação de tarefas");
    }

    private String login (LoginRequestDTO loginRequestDTO) {
        return usuarioService.login(loginRequestDTO);
    }

    public LoginRequestDTO converterParaRequestDTO () {
        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
