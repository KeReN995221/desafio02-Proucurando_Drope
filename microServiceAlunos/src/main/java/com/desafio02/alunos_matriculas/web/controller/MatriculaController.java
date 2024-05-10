package com.desafio02.alunos_matriculas.web.controller;

import com.desafio02.alunos_matriculas.entities.Matricula;
import com.desafio02.alunos_matriculas.services.MatriculaService;
import com.desafio02.alunos_matriculas.web.dto.MatriculaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    @PostMapping
    public ResponseEntity<MatriculaDto> matricularAluno(@RequestBody MatriculaDto matriculaDto) {
        Matricula matricula = matriculaService.salvar(matriculaDto);
        return ResponseEntity.status(201).body(matriculaDto);
    }
}
