# REST API com Spring Boot e Java

Este projeto é uma API RESTful desenvolvida com **Spring Boot** e **Java**, utilizando **Maven** como gerenciador de dependências. A aplicação gerencia entidades como `Person` e `Book`, permitindo operações CRUD e suporte a paginação, ordenação e HATEOAS.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
    - Spring Data JPA
    - Spring Web
    - Spring HATEOAS
- **Maven**
- **Hibernate**
- **Banco de Dados SQL**
- **Lombok**
- **Jackson**
## Ferramentas e Dependências

- **Spring Boot**: Framework principal para desenvolvimento da API.
    - **Spring Web**: Criação de endpoints REST.
    - **Spring Data JPA**: Persistência de dados com ORM.
    - **Spring Security**: Autenticação e autorização.
    - **Spring Mail**: Envio de e-mails.
    - **Spring HATEOAS**: Navegação entre recursos REST.
- **Springdoc OpenAPI**: Documentação automática da API (Swagger).
- **Hibernate**: Implementação JPA.
- **Lombok**: Redução de código repetitivo (getters/setters).
- **Jackson**: Serialização/deserialização de JSON, XML e YAML.
- **MySQL Connector/J**: Driver JDBC para MySQL.
- **Flyway**: Migração e versionamento do banco de dados.
- **Testcontainers**: Testes automatizados com containers.
- **Rest-Assured**: Testes de endpoints REST.
- **Java JWT (com.auth0)**: Manipulação de tokens JWT para autenticação.

## Funcionalidades

### Entidade `Person`
- **CRUD**: Criar, ler, atualizar e deletar pessoas.
- **Paginação e Filtros**: Buscar todas as pessoas ou filtrar por nome.
- **HATEOAS**: Links adicionados às respostas para facilitar a navegação.

### Entidade `Book`
- **CRUD**: Gerenciamento de livros com informações como autor, título, data de lançamento e preço.

## Estrutura do Projeto

### Pacotes Principais
- `controller`: Contém os controladores REST.
- `services`: Implementa a lógica de negócios.
- `repository`: Interfaces para acesso ao banco de dados.
- `model`: Define as entidades do banco de dados.
- `dto`: Objetos de transferência de dados.
- `exceptions`: Tratamento de exceções personalizadas.

### Principais Classes
- **`Person`**: Representa a entidade de pessoa.
- **`Book`**: Representa a entidade de livro.
- **`PersonServices`**: Contém a lógica de negócios para a entidade `Person`.
- **`PersonController`**: Controlador REST para a entidade `Person`.

## Endpoints

### Person
- **GET /api/person/v1**: Retorna todas as pessoas com paginação.
- **GET /api/person/v1/{id}**: Retorna uma pessoa pelo ID.
- **POST /api/person/v1**: Cria uma nova pessoa.
- **PUT /api/person/v1**: Atualiza uma pessoa existente.
- **DELETE /api/person/v1/{id}**: Deleta uma pessoa pelo ID.

### Book
- **GET /api/book/v1**: Retorna todos os livros com paginação.
- **GET /api/book/v1/{id}**: Retorna um livro pelo ID.
- **POST /api/book/v1**: Cria um novo livro.
- **PUT /api/book/v1**: Atualiza um livro existente.
- **DELETE /api/book/v1/{id}**: Deleta um livro pelo ID.

## Como Executar

1. **Pré-requisitos**:
    - Java 17 ou superior.
    - Maven instalado.
    - Banco de dados configurado (ex.: MySQL, PostgreSQL).

2. **Clonar o repositório**:
   ```bash
   git clone https://github.com/Victornsto/rest-with-spring-boot-and-java.git
   cd rest-with-spring-boot-and-java