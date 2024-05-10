package com.desafio02.alunos_matriculas.web.controller;

import com.desafio02.alunos_matriculas.entities.Aluno;
import com.desafio02.alunos_matriculas.entities.Matricula;
import com.desafio02.alunos_matriculas.services.MatriculaService;
import com.desafio02.alunos_matriculas.web.dto.MatriculaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> getById(@PathVariable Long id) {
        Matricula matricula = matriculaService.buscarPorId(id);
        return ResponseEntity.ok(matricula);
    }

    @GetMapping("/curso/{id}")
    public ResponseEntity<List<Aluno>> GetAllMatriculasFromCursoId(@PathVariable Long id) {
        List<Aluno> alunos = matriculaService.buscarAlunosPorCurso(id);
        return ResponseEntity.ok(alunos);
    }


    @PostMapping
    public ResponseEntity<MatriculaDto> matricularAluno(@RequestBody MatriculaDto matriculaDto) {
        Matricula matricula = matriculaService.salvar(matriculaDto);
        return ResponseEntity.status(201).body(matriculaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMatricula(@PathVariable Long id) {
        matriculaService.apagarMatricula(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/inativar-matricula/{id}")
    public ResponseEntity<Matricula> inativarmatricula(@PathVariable  Long id) {
        Matricula matriculainabilitada = matriculaService.inativarMatricula(id);
        return ResponseEntity.ok().body(matriculainabilitada);
    }


}
