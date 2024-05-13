package com.desafio02.cursos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_curso")
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Min(3)
    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    @NotBlank
    @Column(name = "quantidade_horas")
    private int quantidadeHoras;

    @NotBlank
    @Column(name = "professor")
    private String professor;

    @NotBlank
    @Column(name = "area_conhecimento")
    @Enumerated(EnumType.STRING)
    private AreaDoConhecimento areaConhecimento;

    @NotBlank
    @Column(name = "ativo")
    private boolean ativo;

    @NotBlank
    @Column(name = "total_alunos")
    private Integer totalAlunos;

    public enum AreaDoConhecimento {
        INFORMATICA,
        BIOLOGIA,
        QUIMICA,
        FISICA,
        ASTRONOMIA,
        SOCIOLOGIA,
        PSICOLOGIA,
        ECONOMIA,
        FILOSOFIA,
        ARTES,
        MEDICINA,
        PEDAGOGIA
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return id.equals(curso.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
