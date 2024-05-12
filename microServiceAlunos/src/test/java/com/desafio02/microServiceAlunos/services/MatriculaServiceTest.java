package com.desafio02.microServiceAlunos.services;
import com.desafio02.microServiceAlunos.repositories.MatriculaRepository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class MatriculaServiceTest {
    @InjectMocks
    private MatriculaService matriculaService;
    @Mock
    private MatriculaRepository matriculaRepository;
}

