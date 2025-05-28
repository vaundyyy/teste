package com.commitOuDesiste.pixverificador.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDTO {
    @NotBlank
    private String nome;

    @NotBlank
    @Size(min = 11, max = 14)
    private String cpfCnpj;

    @Email
    private String email;

    private String telefone;

}
