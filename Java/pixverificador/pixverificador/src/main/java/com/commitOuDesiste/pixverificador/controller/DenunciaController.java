package com.commitOuDesiste.pixverificador.controller;

import com.commitOuDesiste.pixverificador.model.Denuncia;
import com.commitOuDesiste.pixverificador.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    private final DenunciaService denunciaService;

    @Autowired
    public DenunciaController(DenunciaService denunciaService) {
        this.denunciaService = denunciaService;
    }

    @PostMapping
    public Denuncia cadastrar(@RequestBody Denuncia denuncia) {
        return denunciaService.cadastrarDenuncia(denuncia);
    }

    @GetMapping
    public List<Denuncia> listarTodas() {
        return denunciaService.listarDenuncias();
    }

    @GetMapping("/{id}")
    public Denuncia buscarPorId(@PathVariable int id) {
        return denunciaService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Denúncia não encontrada"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {
        denunciaService.deletarPorId(id);
    }
}
