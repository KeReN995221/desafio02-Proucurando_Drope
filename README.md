## Desafio 02: Microsserviços para Gestão de Cursos e Alunos

### Time Proucurando_Drope

* **Keren Apuque Cardoso de Morais** - keren.morais.pb@compasso.com.br
* **Nicole Inaê de Oliveira** - nicole.oliveira.pb@compasso.com.br
* **Pedro Alexandre Fonseca Spader** - pedro.spader.pb@compasso.com.br
* **Vinicius Ruan Fillos** - vinicius.fillos.pb@compasso.com.br

### Objetivos

Neste desafio, vamos construir dois microsserviços cruciais para uma plataforma de ensino:

1. **Microsserviço de Gestão de Cursos:**
   * Cadastrar curso.
   * Inabilitar curso .
   * Alterar professor de um curso já cadastrado.

2. **Microsserviço de Gestão de Alunos e Matrículas:**
    * Cadastrar alunos.
    * Matricular alunos a determinados cursos.
    * Inativar a matrícula de um aluno a curso.
    * Consultar alunos matriculados em um curso.
    * Inativar um aluno.
    * Cadastrar alunos.
    * Matricular alunos a determinados cursos.
    * Inativar a matrícula de um aluno a curso.
    * Consultar alunos matriculados em um curso.

### Branches

* **main:** Branch principal, onde **apenas** as alterações validadas serão mescladas.
* **dev:** Branch de desenvolvimento, onde as funcionalidades serão implementadas.
* **Branches de tasks:** Seguindo o padrão `[nome-do-microserviço]/[feature]-[nome_da_funcionalidade]`, por exemplo: `cursos/feature-cadastrar_curso`.

### Tipos de Commit

* **docs:** Alterações na documentação.
* **feat:** Implementação de novas funcionalidades.
* **fix:** Correção de bugs.
* **perf:** Melhorias de performance.
* **refactor:** Reorganização do código sem afetar funcionalidades ou corrigir bugs.
* **style:** Formatação e organização do código (espaços em branco, etc.).
* **test:** Adição ou correção de testes.

## EndPoints MicroServiceCursos
### CURSOS

[POST] api/v1/cursos (Criar um curso)     
[GET] api/v1/cursos (Recuperar todos os cursos)       
[GET] api/v1/cursos/{id} (Buscar curso)
[PATCH] api/v1/cursos/desabilitar-curso/{id} (inabilitar um curso)    
[PATCH] api/v1/cursos/{id} (Alterar professor)      
[POST] api/v1/cursos/total-alunos/{id} ()

### EndPoints suportes para OpenFeign
[POST] api/v1/cursos/matricular/{id} (Quantidade do total de alunos +1)
[POST] api/v1/cursos/desmatricular/{id} (Quantidade do total de alunos -1)

## EndPoints MicroServiceAlunos
### ALUNOS
[POST] api/v1/alunos (Criar um alunos)    
[GET] api/v1/alunos (Recuperar todos os alunos)       
[GET] api/v1/alunos/{id} (Buscar alunos)         
[PATCH] api/v1/alunos/inativar-aluno/{id} (Inativar aluno)
### MATRICULAS
[POST] api/v1/matriculas (Criar uma matricula)
[GET] api/v1/matriculas/{id} (Buscar matricula)         
[GET] api/v1/matriculas/curso/{id} (Buscar matriculas de um curso)          
[PATCH] api/v1/matriculas/inativar-matricula/{id} (Inativar matricula)    

