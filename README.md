# 🚗 Locadora Inteligente

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de uma locadora de veículos.

O sistema permite gerenciar usuários, carros, reservas e manutenções, além de possuir validações, regras de negócio e tratamento padronizado de exceções.

## 🛠️ Tecnologias

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Jakarta Validation
* Swagger / OpenAPI
* JUnit 5
* Mockito

## 📋 Funcionalidades

### 👤 Usuários

* Cadastro de usuários
* Listagem de usuários
* Busca de usuário por ID
* Atualização de usuários
* Exclusão de usuários
* Validação dos dados cadastrados

### 🚗 Carros

* Cadastro de carros
* Listagem de carros
* Busca de carro por ID
* Atualização de carros
* Exclusão de carros
* Controle de disponibilidade
* Busca de carros disponíveis
* Filtros por:

  * Marca
  * Modelo
  * Tipo
  * Combustível

### 📅 Reservas

* Cadastro de reservas
* Listagem de reservas
* Busca de reserva por ID
* Atualização de reservas
* Cancelamento de reservas
* Controle de status
* Validação das datas
* Verificação de conflitos entre reservas
* Verificação de conflitos com manutenções
* Liberação do carro após o cancelamento

### 🔧 Manutenções

* Cadastro de manutenções
* Listagem de manutenções
* Busca de manutenção por ID
* Atualização de manutenções
* Exclusão de manutenções
* Controle de status
* Controle do período de manutenção
* Cálculo do custo de acordo com o tipo de manutenção

## 🧠 Regras de negócio

A aplicação possui regras para garantir a consistência das operações.

Entre elas:

* A data inicial de uma reserva não pode ser posterior à data final.
* Um carro não pode possuir reservas em períodos conflitantes.
* Um carro em manutenção não pode ser reservado durante o período da manutenção.
* Reservas finalizadas não podem ser canceladas.
* Uma reserva já cancelada não pode ser cancelada novamente.
* O carro é liberado quando uma reserva é cancelada.
* Registros inexistentes retornam `404 Not Found`.
* Operações que violam regras de negócio retornam `400 Bad Request`.

## 📦 Arquitetura

O projeto utiliza uma estrutura baseada na separação de responsabilidades:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

### Controllers

Responsáveis por receber as requisições HTTP e retornar as respostas da API.

### Services

Responsáveis pela lógica e pelas regras de negócio da aplicação.

### Repositories

Responsáveis pela comunicação com o banco de dados utilizando Spring Data JPA.

### Entities

Representam as entidades persistidas no banco de dados.

### DTOs

Utilizados para receber os dados enviados pelos clientes da API.

### ResponseDTOs

Utilizados para controlar os dados devolvidos nas respostas da API.

## ⚠️ Tratamento de exceções

A aplicação possui tratamento global de exceções utilizando `@RestControllerAdvice`.

Foram implementadas exceções específicas para diferentes situações:

* `ResourceNotFoundException`
* `BusinessException`
* Erros de validação

As respostas de erro são padronizadas através do `ErrorResponseDTO`.

Exemplo:

```json
{
  "timestamp": "2026-09-10T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Carro não encontrado"
}
```

## ✅ Validações

Os DTOs utilizam **Jakarta Bean Validation** para validar os dados recebidos pela API.

Exemplos de validações:

* Campos obrigatórios
* Valores que não podem ser nulos
* Dados de cadastro
* Datas de reservas
* Identificadores de usuários e carros

## 📚 Documentação da API

A API possui documentação utilizando **Swagger / OpenAPI**.

Com a aplicação em execução, a documentação pode ser acessada pelo Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

O Swagger permite visualizar os endpoints, parâmetros, requisições e respostas da API.

## 🔗 Principais endpoints

| Recurso     | Endpoint       |
| ----------- | -------------- |
| Usuários    | `/usuarios`    |
| Carros      | `/carros`      |
| Reservas    | `/reservas`    |
| Manutenções | `/manutencoes` |

### Usuários

```text
GET    /usuarios
GET    /usuarios/{id}
POST   /usuarios
PUT    /usuarios/{id}
DELETE /usuarios/{id}
```

### Carros

```text
GET    /carros
GET    /carros/{id}
POST   /carros
PUT    /carros/{id}
DELETE /carros/{id}
```

### Reservas

```text
GET    /reservas
GET    /reservas/{id}
POST   /reservas
PUT    /reservas/{id}
DELETE /reservas/{id}
```

### Manutenções

```text
GET    /manutencoes
GET    /manutencoes/{id}
POST   /manutencoes
PUT    /manutencoes/{id}
DELETE /manutencoes/{id}
```

> Os endpoints específicos de busca e cancelamento também podem ser consultados na documentação do Swagger.

## 🗄️ Banco de dados

O projeto utiliza **PostgreSQL**.

Crie um banco de dados chamado:

```sql
CREATE DATABASE locadora;
```

Configure as credenciais no arquivo:

```text
src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/locadora
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
```

> Não coloque sua senha real ou outras credenciais no GitHub.

## ▶️ Como executar

### Pré-requisitos

* Java 21
* Maven
* PostgreSQL
* Git

### 1. Clone o repositório

```bash
git clone https://github.com/gblimadev/locadora-inteligente.git
```

### 2. Entre na pasta

```bash
cd locadora-inteligente
```

### 3. Configure o banco de dados

Crie o banco `locadora` no PostgreSQL e configure o `application.properties`.

### 4. Execute a aplicação

```bash
mvn spring-boot:run
```

Ou execute a classe principal através da sua IDE.

### 5. Acesse o Swagger

```text
http://localhost:8080/swagger-ui.html
```

## 📁 Estrutura do projeto

```text
src
├── main
│   ├── java
│   │   └── com.devs.locadora.carros
│   │       ├── controllers
│   │       ├── dto
│   │       ├── entities
│   │       ├── exceptions
│   │       ├── repositories
│   │       └── services
│   │
│   └── resources
│       └── application.properties
│
└── test
    └── java
```

## 🚧 Status do projeto

🟢 **Backend em desenvolvimento — primeira versão funcional concluída.**

Principais partes implementadas:

* CRUD completo
* DTOs e ResponseDTOs
* Validações
* Regras de negócio
* Tratamento global de exceções
* Respostas padronizadas de erro
* Swagger / OpenAPI
* Testes manuais dos endpoints

### 🔮 Próximos passos

* 🚀 Deploy da API
* 🖥️ Desenvolvimento do frontend com React
* 🧪 Implementação dos testes unitários
* 🔄 Integração entre frontend e backend

## 👨‍💻 Autor

**Gabriel Lima**

Projeto desenvolvido como prática de desenvolvimento **Java Backend**, com foco em construção de APIs REST utilizando Spring Boot, JPA e PostgreSQL.
