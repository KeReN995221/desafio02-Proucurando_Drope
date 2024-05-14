package com.desafio02.microServiceAlunos.web.controller;

import com.desafio02.microServiceAlunos.entities.Aluno;
import com.desafio02.microServiceAlunos.services.AlunoService;
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
import java.util.List;

@Tag(name = "Alunos", description = "Contém as funcionalidades do micro serviço")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    @Operation(summary = "Recuperar aluno pelo id", description = "Funcionalidades para criar um novo aluno",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Aluno.class))),
                    @ApiResponse(responseCode = "404", description = "recursos não encontrado",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),

            })
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getById(@PathVariable Long id) {
        Aluno aluno = alunoService.buscarPorId(id);
        return ResponseEntity.ok(aluno);
    }

    @Operation(summary = "Criar aluno", description = "Funcionalidades para criar um novo aluno",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Aluno.class))),
                    @ApiResponse(responseCode = "409", description = "CPF já cadastrado para um aluno",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@Valid @RequestBody Aluno aluno) {
        alunoService.salvar(aluno);
        return ResponseEntity.status(201).body(aluno);
    }

    @Operation(summary = "Inabilitar aluno", description = "Funcionalidade de inabilitar um aluno por id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Aluno inabilitado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation =  Aluno.class))),
                    @ApiResponse(responseCode = "404", description = "Aluno não encontrado",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "400", description = "O aluno já está inabilitado",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PatchMapping("/inativar-aluno/{id}")
    public ResponseEntity<Aluno> inativarAluno(@PathVariable  Long id) {
        Aluno alunoInabilitado = alunoService.inabilitarAluno(id);
        return ResponseEntity.ok().body(alunoInabilitado);
    }

    @Operation(summary = "Buscar todos alunos", description = "Funcionalidade de buscar todos os alunos cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Aluno.class))),
                    @ApiResponse(responseCode = "404", description = "Não há alunos cadastrados",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @GetMapping
    public ResponseEntity<List<Aluno>> getAll() {
        List<Aluno> alunos = alunoService.buscarTodos();
        return ResponseEntity.ok(alunos);
    }
}
