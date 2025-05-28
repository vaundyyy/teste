package com.commitOuDesiste.pixverificador.repository;

import com.commitOuDesiste.pixverificador.model.StatusTransacao;
import com.commitOuDesiste.pixverificador.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    List<Transacao> findByDestinatario_Id(Integer destinatarioId);

    List<Transacao> findByRemetente_Id(Integer remetenteId);

    int countByDestinatario_IdAndStatus(Integer destinatarioId, StatusTransacao status);

}
