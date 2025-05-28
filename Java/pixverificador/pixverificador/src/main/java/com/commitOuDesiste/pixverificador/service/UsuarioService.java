package com.commitOuDesiste.pixverificador.service;

import com.commitOuDesiste.pixverificador.model.Usuario;
import com.commitOuDesiste.pixverificador.repository.UsuarioRepository;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public void deletarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }
}
