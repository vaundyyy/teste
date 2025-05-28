package com.commitOuDesiste.pixverificador.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Double valor;

    private LocalDateTime data = LocalDateTime.now();

    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusTransacao status;

    @ManyToOne
    @JoinColumn(name = "remetente_id")
    private Usuario remetente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private Usuario destinatario;
}
