package com.desafio02.alunos_matriculas.web.controller;

import com.desafio02.alunos_matriculas.entities.Aluno;
import com.desafio02.alunos_matriculas.services.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {
        alunoService.salvar(aluno);
        return ResponseEntity.status(201).body(aluno);
    }

    @PatchMapping("inativar-aluno/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable  Long id) {
        Aluno alunoInabilitado = alunoService.inabilitarAluno(id);
        return ResponseEntity.ok().body(alunoInabilitado);
    }
}
