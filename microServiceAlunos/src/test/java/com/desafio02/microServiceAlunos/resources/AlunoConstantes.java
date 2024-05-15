package com.desafio02.microServiceAlunos.resources;

import com.desafio02.microServiceAlunos.entities.Aluno;

import java.time.LocalDate;

public class AlunoConstantes {
    private static final LocalDate DATE = LocalDate.of(2004, 5, 31);
    public static final Aluno ALUNO = new Aluno(1L,"nome", "11111111111", DATE, Aluno.Sexo.M , true );
    public static final Aluno ALUNO_DESATIVADO = new Aluno(1L,"nome", "11111111111", DATE, Aluno.Sexo.M , false );
    public static final Aluno ALUNO_INVALIDO = new Aluno(null, "", "", null, null , false);
    public static final Aluno ALUNO_VAZIO = new Aluno();
}
