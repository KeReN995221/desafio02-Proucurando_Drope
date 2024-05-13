package com.desafio02.cursos.services;

import com.desafio02.cursos.entities.Curso;
import com.desafio02.cursos.excpetions.EntityNotFoundException;
import com.desafio02.cursos.excpetions.NameUniqueViolationException;
import com.desafio02.cursos.excpetions.UnableCourseException;
import com.desafio02.cursos.repositories.CursoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.desafio02.cursos.resources.CursoConstantes.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
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
    void salvar_ComNomeRepetido_RetornarNameUniqueViolationException(){
        when(cursoService.salvar(CURSO_REPETIDO)).thenThrow(NameUniqueViolationException.class);
        assertThrows(NameUniqueViolationException.class, () -> cursoService.salvar(CURSO_REPETIDO));
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

    @Test
    void getAll_ComCursosExistentes_RetornarListaDeCurso(){
        List<Curso> cursos = new ArrayList<>(){
            {add(CURSO);}
        };
        when(cursoRepository.findAll()).thenReturn(cursos);
        List<Curso> sut = cursoService.buscarTodos();
        assertThat(sut)
                .isNotEmpty()
                .hasSize(1);
        assertThat(sut.get(0)).isEqualTo(CURSO);
    }

    @Test
    void getAll_ComCursosInexistente_RetornarEntityNotFoundException(){
        when(cursoRepository.findAll().isEmpty()).thenThrow(EntityNotFoundException.class);
        assertThatThrownBy(() -> cursoService.buscarTodos().isEmpty()).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void desabilitarCurso_ComCursohabilitado_RetornarCursoDesabilitado() {
        when(cursoRepository.findById(CURSO.getId())).thenReturn(Optional.of(CURSO));
        Curso sut = cursoService.desabilitarCurso(CURSO.getId());
        assertThat(sut.isAtivo()).isFalse();
    }

    @Test
    void desabilitarCurso_ComCursoDesabilitado_RetornarUnableCourseException() {
        when(cursoRepository.findById(CURSO_DESABILITADO.getId())).thenThrow(UnableCourseException.class);
        assertThrows(UnableCourseException.class, () -> cursoService.desabilitarCurso(CURSO_DESABILITADO.getId()));
    }

    @Test
    void mudarProfessor_ComCursoValido_RetornarCurso() {
        when(cursoRepository.findById(CURSO.getId())).thenReturn(Optional.of(NOVO_CURSO));
        Curso sut = cursoService.mudarProfessor(CURSO.getId(), NOVO_CURSO);
        assertThat(sut.isAtivo()).isEqualTo(NOVO_CURSO.isAtivo());
        assertThat(sut.getProfessor()).isEqualTo(NOVO_CURSO.getProfessor());
        assertThat(sut.getNome()).isEqualTo(NOVO_CURSO.getNome());
        assertThat(sut.getId()).isEqualTo(NOVO_CURSO.getId());
    }

    @Test
    void mudarProfessor_ComDadosInvalidos_RetornarEntityNotFoundException() {
        when(cursoRepository.findById(CURSO.getId())).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> cursoService.mudarProfessor(CURSO.getId(), CURSO_VAZIO));
    }

    @Test
    void buscarTotalAlunosPorCurso_ComIdValido_RetornarTotalAlunos() {
        when(cursoRepository.findById(CURSO.getId())).thenReturn(Optional.of(CURSO));
        Integer sut = cursoService.buscarTotalAlunos(CURSO.getId());
        assertThat(sut).isEqualTo(CURSO.getTotalAlunos());
    }

    @Test
    void buscarTotalAlunosPorCurso_ComIdInvalido_RetornarEntityNotFoundException() {
        when(cursoRepository.findById(0L)).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> cursoService.buscarTotalAlunos(0L));
    }

    @Test
    void aumentarTotalMatriculas_ComIdValido_Retornar() {
        when(cursoRepository.findById(CURSO.getId())).thenReturn(Optional.of(CURSO));
        Integer sut = cursoService.buscarTotalAlunos(CURSO.getId());
        assertThat(sut).isEqualTo(CURSO.getTotalAlunos());
    }

    @Test
    void aumentarTotalMatriculas_ComIdExistente_SemRetorno() {
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(CURSO));
        assertThatCode(() -> cursoService.aumentarTotalMatriculas(1L)).doesNotThrowAnyException();
    }

    @Test
    void aumentarTotalMatriculas_ComIdInexistente_RetornarException() {
        doThrow(new EntityNotFoundException("")).when(cursoRepository).findById(0L);
        assertThatThrownBy(() -> cursoService.diminuirTotalMatriculas(0L)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void diminuirTotalMatriculas_ComIdExistente_SemRetorno() {
        when(cursoRepository.findById(1L)).thenReturn(Optional.of(CURSO));
        assertThatCode(() -> cursoService.aumentarTotalMatriculas(1L)).doesNotThrowAnyException();
    }

    @Test
    void diminuirTotalMatriculas_ComIdInexistente_RetornarException() {
        doThrow(new EntityNotFoundException("")).when(cursoRepository).findById(0L);
        assertThatThrownBy(() -> cursoService.diminuirTotalMatriculas(0L)).isInstanceOf(EntityNotFoundException.class);
    }



}
