package com.desafio02.microServiceAlunos.services;

import com.desafio02.microServiceAlunos.entities.Aluno;
import com.desafio02.microServiceAlunos.entities.Matricula;
import com.desafio02.microServiceAlunos.repositories.AlunoRepository;
import com.desafio02.microServiceAlunos.repositories.MatriculaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.desafio02.microServiceAlunos.resources.AlunoConstantes.*;
import static com.desafio02.microServiceAlunos.resources.MatriculaConstantes.MATRICULA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoService;
    @Mock
    private AlunoRepository alunoRepository;
    @Mock
    private MatriculaRepository matriculaRepository;

    @Test
    void salvar_ComDadosValidos_RetornarAlunoCriado() {
        when(alunoRepository.save(ALUNO)).thenReturn(ALUNO);
        Aluno sut = alunoService.salvar(ALUNO);
        assertThat(sut).isEqualTo(ALUNO);
    }

    @Test
    void salvar_ComDadosInvalidos_RetornarException() {
        when(alunoRepository.save(ALUNO_INVALIDO)).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> alunoService.salvar(ALUNO_INVALIDO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void getById_ComIdExistente_RetornarAluno() {
        when(alunoRepository.findById(anyLong())).thenReturn(Optional.of(ALUNO));
        Optional<Aluno> sut = Optional.ofNullable(alunoService.buscarPorId(1L));
        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(ALUNO);
    }

    @Test
    void getById_ComIdInexistente_RetornarException() {
        when(alunoRepository.findById(0L)).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> alunoService.buscarPorId(0L)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void getAll_SemParametros_RetornarLista() {
        List<Aluno> alunos = new ArrayList<>(){{
            add(ALUNO);
        }};
        when(alunoRepository.findAll()).thenReturn(alunos);
        List<Aluno> sut = alunoRepository.findAll();
        assertThat(sut).size().isEqualTo(1);
        assertThat(sut.get(0)).isEqualTo(ALUNO);
    }

    @Test
    void inabilitarAluno_ComIdExistente_RetornarAluno() {
        when(alunoRepository.findById(anyLong())).thenReturn(Optional.of(ALUNO_DESATIVADO));
        Optional<Aluno> sut = Optional.ofNullable(alunoService.buscarPorId(1L));
        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(ALUNO_DESATIVADO);
    }

    @Test
    void inabilitarAluno_ComIdInexistente_RetornarException() {
        when(alunoRepository.findById(anyLong())).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> alunoService.buscarPorId(0L)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void buscarMatriculas_ComIdExistente_RetornarLista() {
        List<Matricula> matriculas = new ArrayList<>(){{
            add(MATRICULA);
        }};
        when(matriculaRepository.findAll()).thenReturn(matriculas);
        List<Matricula> sut = matriculaRepository.findAll();
        assertThat(sut).size().isEqualTo(1);
        assertThat(sut.get(0)).isEqualTo(MATRICULA);
    }

    @Test
    void buscarMatriculas_ComIdInexistente_RetornarException() {
        when(matriculaRepository.findAll()).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> matriculaRepository.findAll()).isInstanceOf(RuntimeException.class);
    }
}


