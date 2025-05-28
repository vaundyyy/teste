package com.commitOuDesiste.pixverificador.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String cpfCnpj;

    private String email;
    private String telefone;
    private Boolean vendedorVerificado = false;

    private Double scoreConfianca = 100.0;

    @Enumerated(EnumType.STRING)
    private StatusUsuario status = StatusUsuario.CONFIAVEL;

    @OneToMany(mappedBy = "destinatario")
    private List<Transacao> transacoesRecebidas;

    @OneToMany(mappedBy = "remetente")
    private List<Transacao> transacoesEnviadas;

    @OneToMany(mappedBy = "denunciado")
    private List<Denuncia> denunciasRecebidas;

    @OneToMany(mappedBy = "denunciante")
    private List<Denuncia> denunciasFeitas;
}
