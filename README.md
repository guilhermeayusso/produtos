# Produtos API

Este projeto é uma API REST para gerenciamento de produtos, utilizando Java 17, Spring Boot 3.2.5 e MySQL 5.7.

## Funcionalidades

- CRUD de produtos
- Validação de dados
- Tratamento de exceções

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.5
- MySQL 5.7
- Docker
- Maven

## Pré-requisitos

- JDK 17
- Docker e Docker Compose
- IDE de sua escolha (Eclipse, IntelliJ, etc.)


## Executando o Projeto

### 1. Executando Localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/guilhermeayusso/produtos.git
   ```

2. Importe o projeto na sua IDE preferida.

3. Execute o Docker Compose local:
   ```bash
   docker-compose -f docker-compose-local.yml up
   ```
4. Inicie a aplicação na sua IDE.

### 2. Executando com Docker Compose

1. Clone o repositório:
   ```bash
   git clone https://github.com/guilhermeayusso/produtos.git
   ```
2. Execute o Docker Compose:
   ```bash
   docker-compose up --build
   ```
## Endpoints da API

- **GET /produtos:** Lista todos os produtos.
- **GET /produtos/{id}:** Retorna um produto pelo ID.
- **POST /produtos:** Cria um novo produto.
- **PUT /produtos/{id}:** Atualiza um produto existente.
- **DELETE /produtos/{id}:** Deleta um produto pelo ID.

## Observações

- Certifique-se de ter o Docker instalado na sua máquina para executar o projeto com Docker Compose.