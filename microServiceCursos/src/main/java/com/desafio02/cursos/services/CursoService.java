package com.desafio02.cursos.services;

import com.desafio02.cursos.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
}
