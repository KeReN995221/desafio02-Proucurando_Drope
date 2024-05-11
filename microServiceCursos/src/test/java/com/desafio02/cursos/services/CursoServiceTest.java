package com.desafio02.cursos.services;

import com.desafio02.cursos.entities.Curso;
import com.desafio02.cursos.excpetions.EntityNotFoundException;
import com.desafio02.cursos.excpetions.NameUniqueViolationException;
import com.desafio02.cursos.repositories.CursoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static com.desafio02.cursos.resources.CursoConstantes.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {

    @InjectMocks
    private CursoService cursoService;
    @Mock
    private CursoRepository cursoRepository;

    @Test
    void salvar_ComDadosValidos_RetornarCursoCriado(){
        when(cursoRepository.save(CURSO)).thenReturn(CURSO);
        Curso sut = cursoService.salvar(CURSO);
        assertThat(sut).isEqualTo(CURSO);

    }

    @Test
    void salvar_ComDadosInvalidos_RetornarThrowException(){
        when(cursoRepository.save(CURSO_INVALIDO)).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> cursoService.salvar(CURSO_INVALIDO)).isInstanceOf(RuntimeException.class);
    }



    @Test
    void getById_ComIdExistente_RetornarCurso(){
        when(cursoRepository.findById(3L)).thenReturn(Optional.of(CURSO));
        Optional<Curso> sut = Optional.ofNullable(cursoService.buscarPorId(3L));
        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(CURSO);
    }
    @Test
    void getById_ComIdInexistente_RetornarVazio(){
        when(cursoRepository.findById(null)).thenThrow(EntityNotFoundException.class);
        assertThatThrownBy(() -> cursoService.buscarPorId(null)).isInstanceOf(EntityNotFoundException.class);
    }

}
