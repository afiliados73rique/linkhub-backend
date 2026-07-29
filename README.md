# LinkHub API

API REST responsável pelo gerenciamento de usuários, autenticação e links da plataforma **LinkHub**.

Desenvolvida com **Java** e **Spring Boot**, a aplicação oferece uma arquitetura organizada, escalável e preparada para ambientes de produção.

---

## Tecnologias

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Neon Database
- Maven
- Railway

---

## Funcionalidades

- CRUD completo de links
- Associação de links ao usuário
- Persistência em PostgreSQL
- API REST
- Tratamento de exceções
- Validação de dados

---

## Arquitetura

A API segue uma arquitetura em camadas.

```
Cliente

    │

    ▼

Controller

    │

    ▼

Service

    │

    ▼

Repository

    │

    ▼

PostgreSQL (Neon)
```

---

## Estrutura do Projeto

```
src
└── main
    ├── java
    │
    ├── config
    ├── controller
    ├── dto
    ├── exception
    ├── model
    ├── repository
    ├── service
    └── LinkHubApplication.java
    │
    └── resources
        ├── application.properties
        └── application-prod.properties
```

---

## Instalação

Clone o repositório

```bash
git clone https://github.com/seu-usuario/linkhub-api.git
```

Entre na pasta

```bash
cd linkhub-api
```

Instale as dependências

```bash
mvn clean install
```

---

## Executando localmente

```bash
mvn spring-boot:run
```

A API estará disponível em

```
http://localhost:8080
```

---

## Variáveis de Ambiente

Configure as seguintes variáveis:

```properties
SPRING_DATASOURCE_URL=

SPRING_DATASOURCE_USERNAME=

SPRING_DATASOURCE_PASSWORD=

SPRING_JPA_HIBERNATE_DDL_AUTO=update

SERVER_PORT=8080
```

---

## Banco de Dados

A aplicação utiliza **PostgreSQL**, hospedado no **Neon Database**, garantindo alta disponibilidade, escalabilidade e compatibilidade com o ecossistema Spring.

Modelo simplificado:

```
User
│
├── id
├── name
├── email
├── password
└── links
        │
        ▼
Link
├── id
├── title
├── url
├── icon
└── order
```

---

## Endpoints

### Autenticação

| Método | Endpoint | Descrição |
|---------|----------|-----------|
| POST | `/auth/register` | Cadastro de usuário |
| POST | `/auth/login` | Login |

---

### Usuários

| Método | Endpoint | Descrição |
|---------|----------|-----------|
| GET | `/users` | Lista usuários |
| GET | `/users/{id}` | Busca usuário |
| PUT | `/users/{id}` | Atualiza usuário |
| DELETE | `/users/{id}` | Remove usuário |

---

### Links

| Método | Endpoint | Descrição |
|---------|----------|-----------|
| GET | `/links` | Lista links |
| GET | `/links/{id}` | Busca link |
| POST | `/links` | Cria link |
| PUT | `/links/{id}` | Atualiza link |
| DELETE | `/links/{id}` | Remove link |

---

## Exemplo de Resposta

### Sucesso

```json
{
  "success": true,
  "message": "Operação realizada com sucesso.",
  "data": {}
}
```

### Erro

```json
{
  "timestamp": "2026-07-29T15:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Recurso não encontrado."
}
```

---

## Deploy

| Serviço | Plataforma |
|----------|------------|
| API | Railway |
| Banco de Dados | Neon PostgreSQL |

---

## Qualidade do Projeto

- Arquitetura em camadas
- Código orientado a objetos
- API REST
- Spring Data JPA
- Separação de responsabilidades
- Tratamento global de exceções
- Validação de dados
- Configuração por ambiente
- Estrutura preparada para evolução do projeto

---

## Desenvolvedores

**Nicolly Meireles**

Desenvolvimento Backend, Banco de Dados, Deploy e Documentação.

**Millena Meireles**

Desenvolvimento Backend, Banco de Dados e Testes.

---

## Licença

Este projeto é proprietário e destinado ao uso da equipe LinkHub.

© 2026 LinkHub. Todos os direitos reservados.
