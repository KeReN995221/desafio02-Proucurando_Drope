package com.desafio02.cursos.web.dto.mapper;

import com.desafio02.cursos.entities.Curso;
import com.desafio02.cursos.web.dto.ProfessorCursoDto;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor
public class ProfessorCursoMapper {

    public static ProfessorCursoDto toDto(Curso curso) {
        return new ModelMapper().map(curso, ProfessorCursoDto.class);
    }

    public static Curso toCurso(ProfessorCursoDto dto) {
        return new ModelMapper().map(dto, Curso.class);
    }
}
