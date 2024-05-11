package com.desafio02.cursos.web.controller;

import com.desafio02.cursos.entities.Curso;
import com.desafio02.cursos.services.CursoService;
import com.desafio02.cursos.web.dto.ProfessorCursoDto;
import com.desafio02.cursos.web.dto.mapper.ProfessorCursoMapper;
import com.desafio02.cursos.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cursos", description = "Contém as funcionalidades do micro serviço")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cursos")
public class CursoController {

    private final CursoService cursoService;

    @Operation(summary = "Criar um novo curso", description = "Funcionalidades para criar um novo curso",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Curso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Curso.class))),
                    @ApiResponse(responseCode = "409", description = "Curso já cadastrado no sistema",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "400", description = "Recurso não processado por dados de entrada inválidas",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping
    public ResponseEntity<Curso> criar(@RequestBody Curso curso) {
        Curso novoCurso = cursoService.salvar(curso);
        return ResponseEntity.status(201).body(novoCurso);
    }

    @Operation(summary = "InabilitarCurso", description = "Funcionalidade de desabilitar o curso",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Curso desabilitado com sucesso"),
                    @ApiResponse(responseCode = "409", description = "Curso já desabilitado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Curso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "400", description = "Campos inválidos ou mal formatados",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PatchMapping("/desabilitar-curso/{id}")
    public ResponseEntity<Curso> inabilitarCurso(@PathVariable Long id) {
        Curso novoCurso = cursoService.desabilitarCurso(id);
        return ResponseEntity.status(HttpStatus.OK).body(novoCurso);
    }

    @Operation(summary = "Alterar professor", description = "Funcionalidade de alterar o professor do curso",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Professor atualizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
                    @ApiResponse(responseCode = "404", description = "Curso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "400", description = "Campos inválidos ou mal formatados",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PatchMapping("/{id}")
    public ResponseEntity<ProfessorCursoDto> alterarProfessor(@PathVariable Long id , @RequestBody ProfessorCursoDto dto) {
        cursoService.mudarProfessor(id , ProfessorCursoMapper.toCurso(dto));
        return ResponseEntity.status(200).build();
    }

    @Operation(summary = "Recuperar um curso pelo id", description = "Funcionalidade de buscar curso pelo id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Curso.class))),
                    @ApiResponse(responseCode = "404", description = "Recursos não encontrado",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),

            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getById(@PathVariable Long id) {
        Curso curso = cursoService.buscarPorId(id);
        return ResponseEntity.ok(curso);
    }

    @Operation(summary = "Lista com todos os Cursos cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista com todos os cursos cadastrados",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Curso.class))),
                    @ApiResponse(responseCode = "404", description = "Não há cursos cadastrados",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<Curso>> getAll() {
        List<Curso> cursos = cursoService.buscarTodos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/total-alunos/{id}")
    public ResponseEntity<Integer> getTotalAlunos(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.buscarTotalAlunos(id));
    }

    @PostMapping("/matricular/{id}")
    public ResponseEntity<Void> matricular(@PathVariable Long id) {
        cursoService.aumentarTotalMatriculas(id);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/desmatricular/{id}")
    public ResponseEntity<Void> desamatricular(@PathVariable Long id) {
        cursoService.diminuirTotalMatriculas(id);
        return ResponseEntity.status(200).build();
    }
}
