package com.desafio02.microServiceAlunos.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "tb_matriculas")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_aluno")
    private Long idAluno;

    @Column(name = "id_curso")
    private Long idCurso;

    @Column(name = "ativo")
    private boolean ativo;
}
