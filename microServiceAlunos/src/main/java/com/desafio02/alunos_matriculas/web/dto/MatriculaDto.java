package com.desafio02.alunos_matriculas.web.dto;
import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MatriculaDto {
    @NonNull
    Long idAluno;

    @NonNull
    Long idCurso;

}
