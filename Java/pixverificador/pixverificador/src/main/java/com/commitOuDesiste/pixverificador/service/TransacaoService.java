package com.commitOuDesiste.pixverificador.service;

import com.commitOuDesiste.pixverificador.model.Transacao;
import com.commitOuDesiste.pixverificador.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public Transacao salvarTransacao(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

    public List<Transacao> listarTransacoes() {
        return transacaoRepository.findAll();
    }

    public Optional<Transacao> buscarPorId(int id) {
        return transacaoRepository.findById(id);
    }

    public void deletarPorId(int id) {
        transacaoRepository.deleteById(id);
    }
}
