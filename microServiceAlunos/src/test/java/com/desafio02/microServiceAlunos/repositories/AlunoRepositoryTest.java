package com.desafio02.microServiceAlunos.repositories;

import com.desafio02.microServiceAlunos.entities.Aluno;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static com.desafio02.microServiceAlunos.resources.AlunoConstantes.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
public class AlunoRepositoryTest {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void criarAluno_ComDadosValidos_RetornarAluno() {
        Aluno aluno = alunoRepository.save(ALUNO);
        Aluno sut = testEntityManager.find(Aluno.class, aluno.getId());
        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(ALUNO);
    }

    @Test
    void criarAluno_ComDadosInvalidos_RetornarException() {
        assertThatThrownBy(() -> alunoRepository.save(ALUNO_INVALIDO)).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> alunoRepository.save(ALUNO_VAZIO)).isInstanceOf(RuntimeException.class);
    }
}
