package com.desafio02.cursos.entities;

import jakarta.persistence.*;
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

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "quantidade_horas")
    private int quantidadeHoras;

    @Column(name = "professor")
    private String professor;

    @Column(name = "area_conhecimento")
    @Enumerated(EnumType.STRING)
    private AreaDoConhecimento areaConhecimento;

    @Column(name = "ativo")
    private boolean ativo;

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
