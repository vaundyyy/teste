package com.commitOuDesiste.pixverificador.service;

import com.commitOuDesiste.pixverificador.model.LogAlerta;
import com.commitOuDesiste.pixverificador.model.Usuario;
import com.commitOuDesiste.pixverificador.repository.LogAlertaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogAlertaService {

    private final LogAlertaRepository repository;

    public LogAlertaService(LogAlertaRepository repository) {
        this.repository = repository;
    }

    public void registrarAlerta(Usuario usuario, String mensagem) {
        LogAlerta alerta = new LogAlerta();
        alerta.setUsuario(usuario);
        alerta.setMensagem(mensagem);
        alerta.setData(LocalDateTime.now());
        repository.save(alerta);
    }

    public List<LogAlerta> listarAlertas() {
        return repository.findAll();
    }
}
