package com.commitOuDesiste.pixverificador.repository;

import com.commitOuDesiste.pixverificador.model.Denuncia;
import com.commitOuDesiste.pixverificador.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DenunciaRepository extends JpaRepository<Denuncia, Integer> {

    List<Denuncia> findByDenunciado_Id(Integer id);
    List<Denuncia> findByDenunciante_Id(Integer id);

    int countByDenunciado_Id(Integer id);

}
