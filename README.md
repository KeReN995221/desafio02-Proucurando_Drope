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

### Link para acesso a documentação no Swagger
Micro Service Cursos - http://localhost:8090/swagger-ui/index.html#/

Micro Service Alunos - http://localhost:8080/swagger-ui/index.html#/

### EndPoints MicroServiceCursos
CURSOS:

[POST] api/v1/cursos (Criar um curso)     
[GET] api/v1/cursos (Recuperar todos os cursos)       
[GET] api/v1/cursos/{id} (Buscar curso)
[PATCH] api/v1/cursos/desabilitar-curso/{id} (inabilitar um curso)    
[PATCH] api/v1/cursos/{id} (Alterar professor)      
[POST] api/v1/cursos/total-alunos/{id} ()

### EndPoints suportes para OpenFeign
[POST] api/v1/cursos/matricular/{id} (Quantidade do total de alunos +1)
[POST] api/v1/cursos/desmatricular/{id} (Quantidade do total de alunos -1)

### EndPoints MicroServiceAlunos
ALUNOS:

[POST] api/v1/alunos (Criar um alunos)    
[GET] api/v1/alunos (Recuperar todos os alunos)       
[GET] api/v1/alunos/{id} (Buscar alunos)         
[PATCH] api/v1/alunos/inativar-aluno/{id} (Inativar aluno)

MATRICULAS:

[POST] api/v1/matriculas (Criar uma matricula)
[GET] api/v1/matriculas/{id} (Buscar matricula)         
[GET] api/v1/matriculas/curso/{id} (Buscar matriculas de um curso)          
[PATCH] api/v1/matriculas/inativar-matricula/{id} (Inativar matricula)    

## Relatório de Desenvolvimento do Projeto

Durante o desenvolvimento do projeto, nossa equipe seguiu um processo colaborativo e iterativo, buscando aprimorar nossas práticas. Os membros participaram ativamente na implementação dos métodos, discutindo abordagens e revisando o código procurando garantir que estivesse claro, eficiente e aderente aos requisitos do projeto.

Realizamos calls de squad regularmente ao longo do processo de implementação, onde discutimos os requisitos, planejamos as tarefas e revisamos o progresso do desenvolvimento. Durante essas reuniões, Keren, Nicole e Vinicius contribuíram com ideias e sugestões.

Um aspecto fundamental do nosso processo foi a colaboração na escrita dos métodos. Os membros participaram ativamente na implementação dos métodos, discutindo abordagens e revisando o código para garantir que estivesse claro, eficiente e aderente aos requisitos do projeto.

Testamos os endpoints utilizando o Postman, garantindo que todas as funcionalidades estivessem corretamente implementadas e que os resultados estivessem de acordo com o esperado. Durante esse processo, identificamos áreas que precisavam de ajustes e refinamentos, e realizamos essas correções antes de enviar o código para o repositório no GitHub. Keren e Vinicios foram responsáveis pela escrita dos testes automatizados, colaborando um com o outro para garantir uma cobertura abrangente dos casos de teste e a qualidade dos testes implementados. Nicole e Keren realizaram a documentação do projeto através do Swagger.

Enfrentamos desafios relacionados ao controle de versão com o Git, mas conseguimos contornar esses problemas com o apoio mútuo dos membros da equipe. Sempre que surgiam dúvidas ou dificuldades, recorremos uns aos outros para encontrar soluções e garantir que o desenvolvimento continuasse fluindo sem contratempos significativos. Para resolver problemas específicos de implementação, realizamos pesquisas e consultamos recursos online. Quando necessário, também solicitamos apoio dos instrutores do Programa de Bolsas, que nos forneceram orientações e sugestões para superar os obstáculos encontrados.

Estamos felizes e orgulhosos do que foi desenvolvido até agora no projeto. Em resumo, nosso processo de desenvolvimento foi caracterizado por uma forte colaboração entre os membros da equipe, comunicação aberta, e um compromisso com o desafio proposto. Agradecemos a oportunidade e apoio fornecidos pelo Programa de Bolsas da Compass UOL