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
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "nome", unique = true, nullable = false)
    private String nome;
    @Column(name = "quantidade_horas", unique = true)
    private int quantidadeHoras;
    @Column(name = "professor", unique = true)
    private String professor;
    @Column(name = "area_conhecimento", unique = true)
    private String areaConhecimento;
    @Column(name = "ativo", unique = true)
    private boolean ativo;

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
