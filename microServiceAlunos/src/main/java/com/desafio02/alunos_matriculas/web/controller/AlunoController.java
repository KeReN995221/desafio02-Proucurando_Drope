package com.desafio02.alunos_matriculas.web.controller;

import com.desafio02.alunos_matriculas.services.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/alunos")
public class AlunoController {
    private final AlunoService alunoService;
}
