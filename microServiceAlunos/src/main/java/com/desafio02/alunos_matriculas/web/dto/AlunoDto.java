package com.desafio02.alunos_matriculas.web.dto;

import com.desafio02.alunos_matriculas.entities.Aluno;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AlunoDto {

    String nome;
    Aluno.Sexo sexo;
    Boolean ativo;
}
