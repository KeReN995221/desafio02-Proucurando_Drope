package com.desafio02.alunos_matriculas.web.dto;

import com.desafio02.alunos_matriculas.entities.Aluno;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ListaAlunosDeCursoDto {
    String curso;
    String professor;
    Integer totalAlunos;
    List<AlunoDto> alunos = new ArrayList<>();

}
