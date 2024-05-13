package com.desafio02.microServiceAlunos.resources;

import com.desafio02.microServiceAlunos.entities.Matricula;
import com.desafio02.microServiceAlunos.web.dto.CursoDto;
import com.desafio02.microServiceAlunos.web.dto.MatriculaDto;

import java.time.LocalDate;

public class MatriculaConstantes {  private static final LocalDate date = LocalDate.of(2004, 05, 31);
    public static final Matricula MATRICULA = new Matricula(1L, 1L, 1L, true);
    public static final MatriculaDto MATRICULADTO = new MatriculaDto(1L, 1L);
    public static final Matricula MATRCULA_INVALIDA = new Matricula(null, null, null, false);
    public static final Matricula MATRICULA_REPETIDA = new Matricula(1L, 1L, 1L, true);
    public static final CursoDto CURSO_DTO = new CursoDto(1L, "name", "professor", true, 1);
}