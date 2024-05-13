package com.desafio02.microServiceAlunos.web.controller;

import com.desafio02.microServiceAlunos.entities.Matricula;
import com.desafio02.microServiceAlunos.services.MatriculaService;
import com.desafio02.microServiceAlunos.web.dto.ListaAlunosDeCursoDto;
import com.desafio02.microServiceAlunos.web.dto.MatriculaDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ListaAlunosDeCursoDto> GetAllMatriculasFromCursoId(@PathVariable Long id) {
        ListaAlunosDeCursoDto alunos = matriculaService.buscarAlunosPorCurso(id);
        return ResponseEntity.ok(alunos);
    }

    @PostMapping
    public ResponseEntity<Matricula> matricularAluno(@RequestBody @Valid MatriculaDto matriculaDto) {
        Matricula matricula = matriculaService.salvar(matriculaDto);
        return ResponseEntity.status(201).body(matricula);
    }

    @PatchMapping("/inativar-matricula/{id}")
    public ResponseEntity<Matricula> inativarmatricula(@PathVariable  Long id) {
        Matricula matriculainabilitada = matriculaService.inativarMatricula(id);
        return ResponseEntity.ok().body(matriculainabilitada);
    }


}
