package com.desafio02.microServiceAlunos.web.dto.mapper;

import com.desafio02.microServiceAlunos.entities.Aluno;
import com.desafio02.microServiceAlunos.web.dto.AlunoDto;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@NoArgsConstructor
public class AlunoMapper {
    public static AlunoDto toDto(Aluno aluno) {
        return new ModelMapper().map(aluno, AlunoDto.class);
    }

}
