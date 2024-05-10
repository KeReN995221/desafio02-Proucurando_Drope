package com.desafio02.alunos_matriculas.client;

import com.desafio02.alunos_matriculas.web.dto.CursoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "matricula-consumer", url = "http://localhost:8090/api/v1/cursos")
public interface CursoClient {

    @GetMapping("{id}")
    CursoDto getCursoById(@PathVariable Long id);
}
