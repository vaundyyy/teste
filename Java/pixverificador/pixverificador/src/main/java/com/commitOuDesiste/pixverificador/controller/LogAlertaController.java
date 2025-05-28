package com.commitOuDesiste.pixverificador.controller;

import com.commitOuDesiste.pixverificador.model.LogAlerta;
import com.commitOuDesiste.pixverificador.service.LogAlertaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas")
public class LogAlertaController {

    private final LogAlertaService service;

    public LogAlertaController(LogAlertaService service) {
        this.service = service;
    }

    @GetMapping
    public List<LogAlerta> listarAlertas() {
        return service.listarAlertas();
    }
}
