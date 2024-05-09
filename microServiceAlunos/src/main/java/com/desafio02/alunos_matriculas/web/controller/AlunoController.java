package com.desafio02.alunos_matriculas.web.controller;

import com.desafio02.alunos_matriculas.entities.Aluno;
import com.desafio02.alunos_matriculas.services.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getById(@PathVariable Long id) {
        Aluno aluno = alunoService.buscarPorId(id);
        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {
        alunoService.salvar(aluno);
        return ResponseEntity.status(201).body(aluno);
    }

    @PatchMapping("/inativar-aluno/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable  Long id) {
        Aluno alunoInabilitado = alunoService.inabilitarAluno(id);
        return ResponseEntity.ok().body(alunoInabilitado);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll() {
        List<Aluno> alunos = alunoService.buscarTodos();
        return ResponseEntity.ok(alunos);
    }


}
