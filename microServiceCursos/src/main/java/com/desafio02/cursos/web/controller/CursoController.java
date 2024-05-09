package com.desafio02.cursos.web.controller;

import com.desafio02.cursos.entities.Curso;
import com.desafio02.cursos.services.CursoService;
import com.desafio02.cursos.web.dto.ProfessorCursoDto;
import com.desafio02.cursos.web.dto.mapper.ProfessorCursoMapper;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cursos")
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<Curso> criar(@RequestBody Curso curso) {
        Curso novoCurso = cursoService.salvar(curso);
        return ResponseEntity.status(201).body(novoCurso);
    }

    @PatchMapping("/desabilitar-curso/{id}")
    public ResponseEntity<Curso> inabilitarCurso(@PathVariable Long id) {
        Curso novoCurso = cursoService.desabilitarCurso(id);
        return ResponseEntity.status(HttpStatus.OK).body(novoCurso);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProfessorCursoDto> alterarProfessor(@PathVariable Long id , @RequestBody ProfessorCursoDto dto) {
        Curso curso = cursoService.mudarProfessor(id , ProfessorCursoMapper.toCurso(dto));

        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getById(@PathVariable Long id) {
        Curso curso = cursoService.buscarPorId(id);
        return ResponseEntity.ok(curso);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> getAll() {
        List<Curso> cursos = cursoService.buscarTodos();
        return ResponseEntity.ok(cursos);
    }

}
