package com.commitOuDesiste.pixverificador.service;

import com.commitOuDesiste.pixverificador.model.Denuncia;
import com.commitOuDesiste.pixverificador.repository.DenunciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DenunciaService {

    private final DenunciaRepository denunciaRepository;

    public DenunciaService(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }

    public Denuncia cadastrarDenuncia(Denuncia denuncia) {
        return denunciaRepository.save(denuncia);
    }

    public List<Denuncia> listarDenuncias() {
        return denunciaRepository.findAll();
    }

    public Optional<Denuncia> buscarPorId(int id) {
        return denunciaRepository.findById(id);
    }

    public void deletarPorId(int id) {
        denunciaRepository.deleteById(id);
    }
}
