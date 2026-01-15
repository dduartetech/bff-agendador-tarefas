package com.diegoduarte.bffagendadortarefas.business;

import com.diegoduarte.bffagendadortarefas.business.DTO.in.*;
import com.diegoduarte.bffagendadortarefas.business.DTO.out.EnderecoDTOResponse;
import com.diegoduarte.bffagendadortarefas.business.DTO.out.TelefoneDTOResponse;
import com.diegoduarte.bffagendadortarefas.business.DTO.out.UsuarioDTOResponse;
import com.diegoduarte.bffagendadortarefas.business.DTO.out.ViaCepDTOResponse;
import com.diegoduarte.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTOResponse salvaUsuario (UsuarioDTORequest usuarioDTO) {
        return usuarioClient.salvaUsuario(usuarioDTO);
    }

    public String login (LoginRequestDTO usuarioDTO) {
        return usuarioClient.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscaUsuarioPorEmail (String email, String token) {
        return usuarioClient.buscaPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail (String email, String token) {
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario (String token, UsuarioDTORequest usuarioDTO) {
        return usuarioClient.atualizaDadosUsuario(usuarioDTO, token);
    }

    public EnderecoDTOResponse atualizadoEndereco (Long idEndereco, EnderecoDTORequest enderecoDTO, String token) {
        return usuarioClient.atualizaEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone (Long idTelefone, TelefoneDTORequest telefoneDTO, String token) {
        return usuarioClient.atualizaTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTOResponse cadastraEndereco (String token, EnderecoDTORequest enderecoDTO) {
        return usuarioClient.cadastraEndereco(enderecoDTO, token);
    }

    public TelefoneDTOResponse cadastraTelefone (String token, TelefoneDTORequest telefoneDTO) {
        return usuarioClient.cadastraTelefone(telefoneDTO, token);
    }

    public ViaCepDTOResponse buscaDadosViaCep (String cep) {
        return usuarioClient.buscaDadosViaCep(cep);
    }
}
