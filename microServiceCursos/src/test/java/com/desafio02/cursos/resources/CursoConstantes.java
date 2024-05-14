package com.desafio02.cursos.resources;

import com.desafio02.cursos.entities.Curso;

import java.util.ArrayList;
import java.util.List;

import static com.desafio02.cursos.entities.Curso.AreaDoConhecimento.MEDICINA;
import static com.desafio02.cursos.entities.Curso.AreaDoConhecimento.PEDAGOGIA;

public class CursoConstantes {
    public static final Curso CURSO = new Curso(3L, "Odontologia", 350, "Ayslan Zotesso", MEDICINA, true, 3);
    public static final Curso NOVO_CURSO = new Curso(3L, "Odontologia", 350, "Marcelo Terenciane", MEDICINA, true, 3);
    public static final Curso CURSO_REPETIDO = new Curso(2L, "Odontologia", 350, "Amarildo Zotesso", MEDICINA, true, 3);
    public static final Curso CURSO_DESABILITADO = new Curso(1L, "Odontologia", 350, "Amarildo Zotesso", MEDICINA, false, 3);
    public static final Curso CURSO_VAZIO = new Curso();
    public static final Curso CURSO_INVALIDO  = new Curso(0L,"", 0, "", null, false,0);
    public static final Curso CURSO_MEDICINA = new Curso(4L, "Medicina", 450, "Pedro Bial", MEDICINA, true, 45);
    public static final Curso CURSO_PEDAGOGIA = new Curso(5L, "Pedagogia", 450, "Telma", PEDAGOGIA, true, 45);
    public static final Curso CURSO_PISICOLOGIA = new Curso(6L, "Pisicologia", 450, "Ana", PEDAGOGIA, true, 45);

    public static  final List<Curso> CURSOS = new ArrayList<Curso>(){
        {
            add(CURSO_MEDICINA);
            add(CURSO_PEDAGOGIA);
            add(CURSO_PISICOLOGIA);
        }
    };
}