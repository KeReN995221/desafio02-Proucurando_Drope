package com.desafio02.microServiceAlunos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "id_aluno", nullable = false)
    private Long idAluno;

    @NotNull
    @Column(name = "id_curso", nullable = false)
    private Long idCurso;

    @NotNull
    @Column(name = "ativo")
    private boolean ativo;
}
