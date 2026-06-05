# Sistema de Venda Interprovincial de Produtos Agrários
API REST desenvolvida com Spring Boot para gestão da comercialização de produtos agrários entre províncias de Moçambique.

## Tecnologias
- Java 17
- Spring Boot 4.0.6
- Spring Security + JWT
- Spring Data JPA / Hibernate
- MySQL
- Maven

## Configuração

### Pré-requisitos

- Java 17+
- MySQL 8+
- Maven 3.8+

### Base de dados

A base de dados `agro_gestao` é criada automaticamente ao iniciar a aplicação.

### application.properties
properties
spring.application.name=Sistema_De_Venda_Interprovincial_De_Produtos_Agrarios
server.port=9010

spring.datasource.url=jdbc:mysql://localhost:3306/agro_gestao?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=suapassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

## Iniciar o projecto
bash
mvn spring-boot:run
A API fica disponível em `http://localhost:9010`.

---

## Autenticação

A API usa **JWT (JSON Web Token)**. Todos os endpoints (excepto `/auth/login` e `/auth/registar`) requerem autenticação.

### Perfis

| Perfil | Permissões |
|---|---|
| `ADMIN` | Acesso total |
| `AGRICULTOR` | Gerir produtos, ver e actualizar pedidos e entregas |
| `COMPRADOR` | Pesquisar produtos, efectuar pedidos e criar entregas |

### Registar utilizador
POST /auth/registar
json
{
  "username": "admin",
  "password": "admin123",
  "perfil": "ADMIN"
}


### Login
POST /auth/login

json
{
  "username": "admin",
  "password": "admin123"
}


**Resposta:**
json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "perfil": "ADMIN",
  "username": "admin"
}


Use o token em todas as chamadas no header:

Authorization: Bearer {token}


---

## Endpoints

### Autenticação

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/auth/login` | Fazer login |
| POST | `/auth/registar` | Registar utilizador |

### Agricultores

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/agricultor` | Listar todos |
| GET | `/agricultor/{id}` | Buscar por ID |
| GET | `/agricultor/provincia/{provincia}` | Buscar por província |
| POST | `/agricultor` | Registar (aceita array) |
| PUT | `/agricultor/{id}` | Actualizar |
| DELETE | `/agricultor/{id}` | Remover |

### Compradores

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/comprador` | Listar todos |
| GET | `/comprador/{id}` | Buscar por ID |
| GET | `/comprador/provincia/{provincia}` | Buscar por província |
| POST | `/comprador` | Cadastrar (aceita array) |
| PUT | `/comprador/{id}` | Actualizar |
| DELETE | `/comprador/{id}` | Remover |

### Produtos

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/produto` | Listar todos |
| GET | `/produto/{id}` | Buscar por ID |
| POST | `/produto` | Registar (aceita array) |
| PUT | `/produto/{id}` | Actualizar |
| DELETE | `/produto/{id}` | Remover |

### Pedidos

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/pedido` | Listar todos |
| GET | `/pedido/{id}` | Buscar por ID |
| GET | `/pedido/nome_produto/{nomeProduto}` | Buscar por nome do produto |
| POST | `/pedido` | Criar (aceita array) |
| PUT | `/pedido/{id}` | Actualizar / Aprovar |

### Entregas

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/entrega` | Listar todas |
| GET | `/entrega/{data_pedido}` | Buscar por data (YYYY-MM-DD) |
| GET | `/entrega/{provincia_destino}` | Buscar por província de destino |
| POST | `/entrega` | Criar (pedido deve estar APROVADO) |
| PUT | `/entrega/{id}/confirmar` | Confirmar entrega |


## Fluxo de utilização
1. Registar agricultores   → POST /agricultor
2. Registar compradores    → POST /comprador
3. Registar produtos       → POST /produto
4. Criar pedidos           → POST /pedido
5. Aprovar pedidos         → PUT /pedido/{id}  (estado: APROVADO)
6. Criar entregas          → POST /entrega
7. Confirmar entregas      → PUT /entrega/{id}/confirmar


## Exemplo de dados

### Agricultor
json
[{
  "nome": "António",
  "apelido": "Machava",
  "provincia": "Maputo",
  "distrito": "Marracuene",
  "genero": "MASCULINO",
  "telefone": "84 111 2233"
}]


### Comprador
json
[{
  "nomeEmpresa": "AgroMaputo Lda",
  "nomeResponsavel": "Carlos Manuel Sitoe",
  "telefone": "84 123 4567",
  "provinciaResidencia": "Maputo",
  "distritoResidencia": "KaMpfumo"
}]

### Produto
json
[{
  "agricultor": { "id": 1 },
  "nomeProduto": "Milho",
  "categoria": "Cereais",
  "quantidadeDisponivel": 500.00,
  "unidadeMedida": "kg",
  "precoUnitario": 25.00,
  "provinciaOrigem": "Maputo"
}]

### Pedido
json
[{
  "comprador": { "id": 1 },
  "produto": { "id": 1 },
  "quantidade": 100.00
}]


### Entrega
json
[{
  "pedido": { "id": 1 },
  "provinciaDestino": "Sofala"
}]


## Autores
@Mario de Mario, @Castigo Mata, @Alvaro Hilario
Prof: momademha@gmail.com
Curso: Engenharia e Tecnologia de sistemas de informação
Especialização: Engenharia de Software
Disciplina: Engenharia de Software
Ano Lectivo:2026
