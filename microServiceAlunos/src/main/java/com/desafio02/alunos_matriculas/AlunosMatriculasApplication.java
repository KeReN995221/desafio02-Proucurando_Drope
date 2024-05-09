package com.desafio02.alunos_matriculas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class AlunosMatriculasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlunosMatriculasApplication.class, args);
	}

}
