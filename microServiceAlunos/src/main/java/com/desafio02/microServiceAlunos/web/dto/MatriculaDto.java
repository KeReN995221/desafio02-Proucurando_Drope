package com.desafio02.microServiceAlunos.web.dto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MatriculaDto {

    @NotNull
    Long idAluno;

    @NotNull
    Long idCurso;
}
