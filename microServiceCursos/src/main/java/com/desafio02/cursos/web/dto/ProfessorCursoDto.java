package com.desafio02.cursos.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProfessorCursoDto {

    @NotBlank
    String professor;
}
