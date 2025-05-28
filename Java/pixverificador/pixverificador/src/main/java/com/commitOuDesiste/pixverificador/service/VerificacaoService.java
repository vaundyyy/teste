package com.commitOuDesiste.pixverificador.service;

import com.commitOuDesiste.pixverificador.model.StatusTransacao;
import com.commitOuDesiste.pixverificador.model.StatusUsuario;
import com.commitOuDesiste.pixverificador.model.Usuario;
import com.commitOuDesiste.pixverificador.repository.DenunciaRepository;
import com.commitOuDesiste.pixverificador.repository.TransacaoRepository;
import com.commitOuDesiste.pixverificador.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class VerificacaoService {

    private final UsuarioRepository usuarioRepository;
    private final DenunciaRepository denunciaRepository;
    private final TransacaoRepository transacaoRepository;
    private final LogAlertaService logAlertaService;

    public VerificacaoService(UsuarioRepository usuarioRepository,
                              DenunciaRepository denunciaRepository,
                              TransacaoRepository transacaoRepository,
                              LogAlertaService logAlertaService) {
        this.usuarioRepository = usuarioRepository;
        this.denunciaRepository = denunciaRepository;
        this.transacaoRepository = transacaoRepository;
        this.logAlertaService = logAlertaService;
    }

    public void avaliarUsuario(Usuario usuario) {
        int totalDenuncias = denunciaRepository.countByDenunciado_Id(usuario.getId());

        int transacoesSuspeitas = transacaoRepository.countByDestinatario_IdAndStatus(
                usuario.getId(),
                StatusTransacao.SUSPEITA
        );

        if (totalDenuncias < 0 || transacoesSuspeitas < 0) {
            throw new IllegalArgumentException("Valores negativos não são permitidos.");
        }
        if (usuario.getStatus() == StatusUsuario.BLOQUEADO) {
            logAlertaService.registrarAlerta(usuario, "Usuário já está bloqueado.");
            return;
        }

        if (totalDenuncias >= 10) {
            usuario.setStatus(StatusUsuario.BLOQUEADO);
            logAlertaService.registrarAlerta(usuario, "Usuário bloqueado por denúncias excessivas.");
            usuarioRepository.save(usuario);
            return;
        }

        if (totalDenuncias >= 5) {
            usuario.setStatus(StatusUsuario.BLOQUEADO);
            logAlertaService.registrarAlerta(usuario, "Usuário bloqueado por excesso de denúncias.");
            usuarioRepository.save(usuario);
            return;
        }

        if (transacoesSuspeitas >= 3) {
            usuario.setStatus(StatusUsuario.SUSPEITO);
            logAlertaService.registrarAlerta(usuario, "Usuário marcado como suspeito por transações suspeitas.");
            usuarioRepository.save(usuario);
            return;
        }

        double score = 100.0;
        score -= totalDenuncias * 20;
        score -= transacoesSuspeitas * 10;

        if (score < 0) score = 0;

        usuario.setScoreConfianca(score);

        if (score == 0) {
            usuario.setStatus(StatusUsuario.BLOQUEADO);
            logAlertaService.registrarAlerta(usuario, "Usuário bloqueado por score zero.");
        } else if (score < 50) {
            usuario.setStatus(StatusUsuario.SUSPEITO);
            logAlertaService.registrarAlerta(usuario, "Usuário marcado como suspeito.");
        } else {
            usuario.setStatus(StatusUsuario.CONFIAVEL);
        }

        usuarioRepository.save(usuario);
    }
}
