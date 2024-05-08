package com.desafio02.cursos.web.controller;

import com.desafio02.cursos.entities.Curso;
import com.desafio02.cursos.services.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cursos")
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<Curso> create(@RequestBody Curso curso) {
        Curso newCurso = cursoService.salvar(curso);
        return ResponseEntity.status(201).body(newCurso);
    }
}
