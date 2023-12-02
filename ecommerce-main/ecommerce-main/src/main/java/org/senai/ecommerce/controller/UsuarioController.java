package org.senai.ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.senai.ecommerce.entity.Usuario;
import org.senai.ecommerce.entity.dto.NovoUsuario;
import org.senai.ecommerce.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/add")
    @Operation(summary = "Criar usuário", description = "Esse método irá permitir salvar o usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado"),
    })
    public ResponseEntity<?> salvar(@RequestBody NovoUsuario usuario){
        Usuario usuarioCriado = usuarioService.salvar(usuario);
        return new ResponseEntity(usuarioCriado, HttpStatus.CREATED);
    }

}