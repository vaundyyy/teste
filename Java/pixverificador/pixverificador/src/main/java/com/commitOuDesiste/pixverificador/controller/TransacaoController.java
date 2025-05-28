package com.commitOuDesiste.pixverificador.controller;

import com.commitOuDesiste.pixverificador.model.Transacao;
import com.commitOuDesiste.pixverificador.service.TransacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")

public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public Transacao criarTransacao(@RequestBody Transacao transacao) {
        return transacaoService.salvarTransacao(transacao);
    }

    @GetMapping
    public List<Transacao> listarTransacoes() {
        return transacaoService.listarTransacoes();
    }

    @GetMapping("/{id}")
    public Transacao buscarPorId(@PathVariable int id) {
        return transacaoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {
        transacaoService.deletarPorId(id);
    }
}
