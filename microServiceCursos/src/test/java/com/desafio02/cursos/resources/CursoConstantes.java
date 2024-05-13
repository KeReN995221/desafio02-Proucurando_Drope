package com.desafio02.cursos.resources;

import com.desafio02.cursos.entities.Curso;

import static com.desafio02.cursos.entities.Curso.AreaDoConhecimento.MEDICINA;

public class CursoConstantes {
    public static final Curso CURSO = new Curso(3L, "Odontologia", 350, "Ayslan Zotesso", MEDICINA, true, 3);
    public static final Curso NOVO_CURSO = new Curso(3L, "Odontologia", 350, "Marcelo Terenciane", MEDICINA, true, 3);
    public static final Curso CURSO_REPETIDO = new Curso(2L, "Odontologia", 350, "Amarildo Zotesso", MEDICINA, true, 3);
    public static final Curso CURSO_DESABILITADO = new Curso(1L, "Odontologia", 350, "Amarildo Zotesso", MEDICINA, false, 3);
    public static final Curso CURSO_INVALIDO = new Curso();

}