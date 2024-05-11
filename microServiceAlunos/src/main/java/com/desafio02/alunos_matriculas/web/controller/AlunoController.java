package com.desafio02.alunos_matriculas.web.controller;

import com.desafio02.alunos_matriculas.entities.Aluno;
import com.desafio02.alunos_matriculas.services.AlunoService;
import com.desafio02.alunos_matriculas.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/alunos")
public class AlunoController {
    private final AlunoService alunoService;

    @Operation(summary = "Recuperar um aluno pelo id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Aluno.class))),
                    @ApiResponse(responseCode = "404", description = "recursos não encontrado",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),

            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getById(@PathVariable Long id) {
        Aluno aluno = alunoService.buscarPorId(id);
        return ResponseEntity.ok(aluno);
    }

    @Operation(summary = "Criar um novo aluno",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Aluno.class))),
                    @ApiResponse(responseCode = "409", description = "Aluno com cpf já cadastrado",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "400", description = "campos inválidos",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {
        alunoService.salvar(aluno);
        return ResponseEntity.status(201).body(aluno);
    }

    @Operation(summary = "Desativar status ativo de aluno",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Aluno Inabilitado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation =  Aluno.class))),
                    @ApiResponse(responseCode = "404", description = "recursos não encontrado",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(responseCode = "409", description = "Aluno  já inabilitado",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
                    ,@ApiResponse(responseCode = "400", description = "pedido mal formatado",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PatchMapping("/inativar-aluno/{id}")
    public ResponseEntity<Aluno> inativarAluno(@PathVariable  Long id) {
        Aluno alunoInabilitado = alunoService.inabilitarAluno(id);
        return ResponseEntity.ok().body(alunoInabilitado);
    }

    @Operation(summary = "Lista com todos os Alunos cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista com todos os alunos cadastrados",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Aluno.class))),
                    @ApiResponse(responseCode = "404", description = "não há alunos cadastrados",
                            content= @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<Aluno>> getAll() {
        List<Aluno> alunos = alunoService.buscarTodos();
        return ResponseEntity.ok(alunos);
    }
}
