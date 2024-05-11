package com.desafio02.cursos.services;

import com.desafio02.cursos.entities.Curso;
import com.desafio02.cursos.repositories.CursoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.desafio02.cursos.resources.CursoConstantes.CURSO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {

    @InjectMocks
    private CursoService cursoService;
    @Mock
    private CursoRepository cursoRepository;
    @Test
    public void salvarCurso_ComDadosValidos_RetornarCursoCriadoComStatus201(){
        when(cursoRepository.save(CURSO)).thenReturn(CURSO);
        Curso sut = cursoService.salvar(CURSO);
        assertThat(sut).isEqualTo(CURSO);

    }

}
