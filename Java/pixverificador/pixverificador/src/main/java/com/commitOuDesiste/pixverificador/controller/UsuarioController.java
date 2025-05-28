package com.commitOuDesiste.pixverificador.controller;

import com.commitOuDesiste.pixverificador.DTO.UsuarioDTO;
import com.commitOuDesiste.pixverificador.model.Usuario;
import com.commitOuDesiste.pixverificador.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setCpfCnpj(usuarioDTO.getCpfCnpj());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefone(usuarioDTO.getTelefone());
        return usuarioService.salvarUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuario();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable int id) {
        usuarioService.deletarUsuario(id);
    }
}