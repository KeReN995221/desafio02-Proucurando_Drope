package com.desafio02.cursos.web.dto;

import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProfessorCursoDto {

    @NonNull
    String professor;
}
