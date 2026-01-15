package com.diegoduarte.bffagendadortarefas.infrastructure.client;

import com.diegoduarte.bffagendadortarefas.business.DTO.in.EnderecoDTORequest;
import com.diegoduarte.bffagendadortarefas.business.DTO.in.LoginRequestDTO;
import com.diegoduarte.bffagendadortarefas.business.DTO.in.TelefoneDTORequest;
import com.diegoduarte.bffagendadortarefas.business.DTO.in.UsuarioDTORequest;
import com.diegoduarte.bffagendadortarefas.business.DTO.out.EnderecoDTOResponse;
import com.diegoduarte.bffagendadortarefas.business.DTO.out.TelefoneDTOResponse;
import com.diegoduarte.bffagendadortarefas.business.DTO.out.UsuarioDTOResponse;
import com.diegoduarte.bffagendadortarefas.business.DTO.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioDTOResponse buscaPorEmail(@RequestParam("email")  String email,
                                     @RequestHeader(value = "Authorization", required = false) String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario (@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO usuarioDTO);

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario (@RequestBody UsuarioDTORequest usuarioDTO,
                                             @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco (@RequestBody EnderecoDTORequest enderecoDTO,
                                          @RequestParam("id") Long id,
                                          @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone (@RequestBody TelefoneDTORequest telefoneDTO,
                                          @RequestParam("id") Long id,
                                          @RequestHeader(value = "Authorization", required = false) String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco (@RequestBody EnderecoDTORequest enderecoDTO,
                                          @RequestHeader(value = "Authorization", required = false) String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone (@RequestBody TelefoneDTORequest telefoneDTO,
                                          @RequestHeader(value = "Authorization", required = false) String token);

    @GetMapping("/endereco/{cep}")
    ViaCepDTOResponse buscaDadosViaCep (@PathVariable("cep") String cep);


}
