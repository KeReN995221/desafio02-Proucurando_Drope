package com.desafio02.cursos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_curso")
public class Curso {

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
    private String areaConhecimento;
    @Column(name = "ativo")
    private boolean ativo;
}
