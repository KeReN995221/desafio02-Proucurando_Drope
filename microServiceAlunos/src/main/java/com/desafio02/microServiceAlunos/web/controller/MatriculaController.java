package com.desafio02.microServiceAlunos.web.controller;

import com.desafio02.microServiceAlunos.entities.Matricula;
import com.desafio02.microServiceAlunos.services.MatriculaService;
import com.desafio02.microServiceAlunos.web.dto.ListaAlunosDeCursoDto;
import com.desafio02.microServiceAlunos.web.dto.MatriculaDto;
import com.desafio02.microServiceAlunos.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Matrículas", description = "Contém as funcionalidades do micro serviço")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    @Operation(summary = "Buscar matrícula por id", description = "Funcionalidade de buscar uma matrícula pelo id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Matricula.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            })
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> getById(@PathVariable Long id) {
        Matricula matricula = matriculaService.buscarPorId(id);
        return ResponseEntity.ok(matricula);
    }

    @Operation(summary = "Buscar matrículas do curso", description = "Funcionalidade de buscar todas as matrículas do curso",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Matricula.class))),
            })
    @GetMapping("/curso/{id}")
    public ResponseEntity<ListaAlunosDeCursoDto> GetAllMatriculasFromCursoId(@PathVariable Long id) {
        ListaAlunosDeCursoDto alunos = matriculaService.buscarAlunosPorCurso(id);
        return ResponseEntity.ok(alunos);
    }

    @Operation(summary = "Fazer matrícula", description = "Funcionalidades para criar uma nova matrícula",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Matricula.class))),
                    @ApiResponse(responseCode = "404", description = "Aluno não encontrado",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "400", description = "\"O curso não está ativo.\" ou \"O aluno não está ativo.\" ou \"O aluno já está matriculado neste curso\"",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            })
    @PostMapping
    public ResponseEntity<Matricula> matricularAluno(@RequestBody @Valid MatriculaDto matriculaDto) {
        Matricula matricula = matriculaService.salvar(matriculaDto);
        return ResponseEntity.status(201).body(matricula);
    }

    @Operation(summary = "Inabilitar matrícula", description = "Funcionalidade de inabilitar uma matrícula por id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Matrícula inabilitado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation =  Matricula.class))),
                    @ApiResponse(responseCode = "404", description = "Matrícula não encontrada",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "400", description = "A matrícula já está inabilitada",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PatchMapping("/inativar-matricula/{id}")
    public ResponseEntity<Matricula> inativarmatricula(@PathVariable  Long id) {
        Matricula matriculainabilitada = matriculaService.inativarMatricula(id);
        return ResponseEntity.ok().body(matriculainabilitada);
    }
}
