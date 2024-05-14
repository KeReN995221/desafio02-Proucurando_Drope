package com.desafio02.microServiceAlunos.web.controller;

import com.desafio02.microServiceAlunos.exceptions.UnprocessableEntityException;
import com.desafio02.microServiceAlunos.services.AlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static com.desafio02.microServiceAlunos.resources.AlunoConstantes.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void cadastrarAluno_ComDadosValidos_RetornarAluno() throws Exception {
        when(alunoService.salvar(ALUNO)).thenReturn(ALUNO);
        mockMvc.perform(post("/api/v1/alunos")
                        .content(objectMapper.writeValueAsString(ALUNO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(ALUNO)));
    }

    @Test
    public void cadastrarAluno_ComDadosInvalidos_RetornarUnprocessableEntity() throws Exception {
        when(alunoService.salvar(ALUNO_VAZIO)).thenThrow(new UnprocessableEntityException(""));
        when(alunoService.salvar(ALUNO_INVALIDO)).thenThrow(new UnprocessableEntityException(""));
        mockMvc.perform(
                        post("/api/v1/alunos")
                                .content(objectMapper.writeValueAsString(ALUNO_VAZIO))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
        mockMvc.perform(
                        post("/api/v1/alunos")
                                .content(objectMapper.writeValueAsString(ALUNO_INVALIDO))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }
}
