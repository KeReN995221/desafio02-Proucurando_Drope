package com.desafio02.cursos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank (message = "Nome é obrigatório")
    @NonNull
    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "quantidade_horas")
    private int quantidadeHoras;

    @NotBlank (message = "Nome do Professor é obrigatório")
    @Column(name = "professor")
    private String professor;

    @Column(name = "area_conhecimento")
    @Enumerated(EnumType.STRING)
    private AreaDoConhecimento areaConhecimento;

    @NotNull
    @Column(name = "ativo")
    private boolean ativo;

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
