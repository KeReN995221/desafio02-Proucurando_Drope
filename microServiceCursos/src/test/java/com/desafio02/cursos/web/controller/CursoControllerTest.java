package com.desafio02.cursos.web.controller;

import com.desafio02.cursos.excpetions.InvalidCourseException;
import com.desafio02.cursos.excpetions.NameUniqueViolationException;
import com.desafio02.cursos.services.CursoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.desafio02.cursos.resources.CursoConstantes.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@WebMvcTest(CursoController.class)
public class CursoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CursoService cursoService;

    @Test
    public void criarCurso_ComDadosValidos_RetornarCursoCriadoComStatus201 () throws Exception {
        when(cursoService.salvar(CURSO)).thenReturn(CURSO);
        mockMvc.perform(post("http://localhost:8090/api/v1/cursos")
                .content(objectMapper.writeValueAsString(CURSO))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                                .andExpect(jsonPath("$").value(CURSO));
    }

    @Test
    public void criarCurso_ComDadosInvalidos_RetornarErroComStatus422() throws Exception {
        when(cursoService.salvar(CURSO_INVALIDO)).thenThrow(new InvalidCourseException(""));
        mockMvc.perform(post("/api/v1/cursos")
                        .content(objectMapper.writeValueAsString(CURSO_INVALIDO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    public void criarCurso_ComNomeRepetido_RetornarErroComStatus409() throws Exception {
        when(cursoService.salvar(CURSO)).thenThrow(new NameUniqueViolationException(""));
        mockMvc.perform(post("/api/v1/cursos")
                        .content(objectMapper.writeValueAsString(CURSO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

}
