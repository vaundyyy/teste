package com.commitOuDesiste.pixverificador.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String motivo;
    private String descricao;
    private LocalDateTime dataDenuncia = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "denunciante_id")
    private Usuario denunciante;

    @ManyToOne
    @JoinColumn(name = "denunciado_id")
    private Usuario denunciado;
}
