package com.desafio02.microServiceAlunos.client;

import com.desafio02.microServiceAlunos.web.dto.CursoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "matricula-consumer", url = "http://localhost:8090/api/v1/cursos")
public interface CursoClient {

    @GetMapping("{id}")
    CursoDto getCursoById(@PathVariable Long id);

    @PostMapping("/matricular/{id}")
    Void matricular(@PathVariable Long id);

    @PostMapping("/desmatricular/{id}")
    Void desamatricular(@PathVariable Long id);

    @GetMapping("/total-alunos/{id}")
    Integer getTotalAlunos(@PathVariable Long id);
}
