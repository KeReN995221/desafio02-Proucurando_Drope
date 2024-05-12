package com.desafio02.microServiceAlunos.web.dto;

import com.desafio02.microServiceAlunos.entities.Aluno;
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
