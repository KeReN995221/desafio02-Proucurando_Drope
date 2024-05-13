package com.desafio02.microServiceAlunos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "tb_matriculas")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "id_aluno", nullable = false)
    private Long idAluno;

    @NotBlank
    @Column(name = "id_curso", nullable = false)
    private Long idCurso;

    @NotBlank
    @Column(name = "ativo")
    private boolean ativo;
}
