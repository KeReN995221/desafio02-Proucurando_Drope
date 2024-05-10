package com.desafio02.alunos_matriculas.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class CursoDto {
        private Long id;
        private String nome;
        private String professor;
        private boolean ativo;
        private int totalAlunosMatriculados;
}
