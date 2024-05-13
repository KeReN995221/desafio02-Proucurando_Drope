package com.desafio02.microServiceAlunos.repositories;

import com.desafio02.microServiceAlunos.entities.Matricula;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static com.desafio02.microServiceAlunos.resources.MatriculaConstantes.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
public class MatriculaRepositoryTest {
    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void criarMatricula_ComDadosValidos_RetornarAluno() {
        Matricula matricula = matriculaRepository.save(MATRICULA);
        Matricula sut = testEntityManager.find(Matricula.class, matricula.getId());
        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(MATRICULA);
    }

    @Test
    void criarMatricula_ComDadosInvalidos_RetornarException() {
        assertThatThrownBy(() -> matriculaRepository.save(MATRCULA_INVALIDA)).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> matriculaRepository.save(MATRICULA_VAZIA)).isInstanceOf(RuntimeException.class);
    }
}

