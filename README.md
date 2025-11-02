# CI-CD-Swagger

Projeto Spring Boot de demonstração com API de disciplinas usando um repositório em memória (HashMap), com Swagger e CI/CD via GitHub Actions.

Como está organizado:

- Rotas:
  - GET /disciplinas — lista todas as disciplinas
  - GET /disciplinas/{id} — busca disciplina por id
  - POST /disciplinas — cria uma disciplina (JSON)

- Testes: localizados em `src/test/java` e executados pelo Maven (mvn test).
- Swagger UI: disponível em `/swagger-ui.html` ou `/swagger-ui/index.html` quando a aplicação estiver rodando.

CI/CD:

- O workflow `.github/workflows/ci.yml` roda os testes e gera o artefato JAR, que é publicado como artefato do workflow.

Para executar localmente (precisa ter Java 17 e Maven):

```bash
mvn clean package
java -jar target/ci-cd-swagger-0.0.1-SNAPSHOT.jar
```
