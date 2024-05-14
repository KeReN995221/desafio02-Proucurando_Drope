package com.desafio02.cursos.web.controller;

import com.desafio02.cursos.excpetions.EntityNotFoundException;
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
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    void criarCurso_ComDadosValidos_RetornarCursoCriadoComStatus201 () throws Exception {
        when(cursoService.salvar(CURSO)).thenReturn(CURSO);
        mockMvc.perform(post("http://localhost:8090/api/v1/cursos")
                        .content(objectMapper.writeValueAsString(CURSO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(CURSO));
    }

    @Test
    void criarCurso_ComDadosInvalidos_RetornarErroComStatus400() throws Exception {
        when(cursoService.salvar(CURSO_INVALIDO)).thenThrow(new InvalidCourseException(""));

        mockMvc.perform(post("/api/v1/cursos")
                        .content(objectMapper.writeValueAsString(CURSO_INVALIDO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    void criarCurso_ComNomeRepetido_RetornarErroComStatus409() throws Exception {
        when(cursoService.salvar(any())).thenThrow(new NameUniqueViolationException(""));
        mockMvc.perform(post("/api/v1/cursos")
                        .content(objectMapper.writeValueAsString(CURSO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }


    @Test
    void buscarCurso_ComIdValido_RetornarCursoComStatus200 () throws Exception {
        when(cursoService.buscarPorId(1L)).thenReturn(CURSO);
        mockMvc.perform(get("/api/v1/cursos/{id}", 1L)
                        .content(objectMapper.writeValueAsString(CURSO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(CURSO));
    }


    @Test
    void buscarCurso_ComIdInvalido_RetornarErroComStatu404 () throws Exception {
        when(cursoService.buscarPorId(0L)).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(get("/api/v1/cursos/{id}", 0L)
                        .content(objectMapper.writeValueAsString(CURSO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscarTodosCursos_Cadastrados_RetornarListaCursosComStatus200 () throws Exception {
        when(cursoService.buscarTodos()).thenReturn(CURSOS);
        mockMvc.perform(get("/api/v1/cursos")
                        .content(objectMapper.writeValueAsString(CURSO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

}
