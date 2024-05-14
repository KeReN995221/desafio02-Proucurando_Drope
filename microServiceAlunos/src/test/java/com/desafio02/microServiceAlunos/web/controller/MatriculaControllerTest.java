package com.desafio02.microServiceAlunos.web.controller;

import com.desafio02.microServiceAlunos.services.MatriculaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static com.desafio02.microServiceAlunos.resources.MatriculaConstantes.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MatriculaController.class)
public class MatriculaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatriculaService matriculaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void matricularAluno_ComDadosValidos_RetornarMatricula() throws Exception {
        when(matriculaService.salvar(MATRICULA_DTO)).thenReturn(MATRICULA);
        mockMvc.perform(post("/api/v1/matriculas")
                        .content(objectMapper.writeValueAsString(MATRICULA))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(MATRICULA)));
    }
}