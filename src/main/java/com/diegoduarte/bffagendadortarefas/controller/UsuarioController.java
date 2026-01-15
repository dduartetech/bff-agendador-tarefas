package com.diegoduarte.bffagendadortarefas.controller;

import com.diegoduarte.bffagendadortarefas.business.DTO.in.EnderecoDTORequest;
import com.diegoduarte.bffagendadortarefas.business.DTO.in.LoginRequestDTO;
import com.diegoduarte.bffagendadortarefas.business.DTO.in.TelefoneDTORequest;
import com.diegoduarte.bffagendadortarefas.business.DTO.in.UsuarioDTORequest;
import com.diegoduarte.bffagendadortarefas.business.DTO.out.EnderecoDTOResponse;
import com.diegoduarte.bffagendadortarefas.business.DTO.out.TelefoneDTOResponse;
import com.diegoduarte.bffagendadortarefas.business.DTO.out.UsuarioDTOResponse;
import com.diegoduarte.bffagendadortarefas.business.DTO.out.ViaCepDTOResponse;
import com.diegoduarte.bffagendadortarefas.business.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar Usuários", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario (@RequestBody UsuarioDTORequest usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login usuários", description = "Login do Usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginRequestDTO usuarioDTO){
        return usuarioService.login(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados do Usuário por Email", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<UsuarioDTOResponse> buscaPorEmail(@RequestParam("email")  String email,
                                                            @RequestHeader (name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar usuários", description = "Deleta um Usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader (name = "Authorization", required = false) String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build(); //.build ja q n temos retorno
    }

    @PutMapping
    @Operation(summary = "Atualiza dados Usuário", description = "Atualiza dados do Usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario (@RequestBody UsuarioDTORequest usuarioDTO,
                                                                    @RequestHeader (name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, usuarioDTO));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza Endereço do Usuário", description = "Atualiza Endereço do Usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco (@RequestBody EnderecoDTORequest enderecoDTO,
                                                                 @RequestParam("id") Long id,
                                                                 @RequestHeader (name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizadoEndereco(id, enderecoDTO, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza Telefone do Usuário", description = "Atualiza Telefone do Usuário")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone (@RequestBody TelefoneDTORequest telefoneDTO,
                                                                 @RequestParam("id") Long id,
                                                                 @RequestHeader (name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, telefoneDTO, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva Endereço do Usuário", description = "Salva Endereço do Usuário")
    @ApiResponse(responseCode = "200", description = "Endereçp cadastrado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco (@RequestBody EnderecoDTORequest enderecoDTO,
                                                                 @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, enderecoDTO));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva Telefone do Usuário", description = "Salva Telefone do Usuário")
    @ApiResponse(responseCode = "200", description = "Telefone cadastrado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone (@RequestBody TelefoneDTORequest telefoneDTO,
                                                                 @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, telefoneDTO));
    }

    @PostMapping("/endereco/{cep}")
    @Operation(summary = "Busca Endereço pelo Cep", description = "Busca Dados de Endereço recebendo o CEP")
    @ApiResponse(responseCode = "200", description = "Endereço retornado com sucesso")
    @ApiResponse(responseCode = "400", description = "CEP inválido")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ViaCepDTOResponse> buscaDadosViaCep(@PathVariable("cep") String cep) {
        return ResponseEntity.ok(usuarioService.buscaDadosViaCep(cep));
    }
}
