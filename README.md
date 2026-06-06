# Sistema de Venda Interprovincial de Produtos Agrários

API REST desenvolvida com Spring Boot para gestão da comercialização de produtos agrários entre províncias de Moçambique.

## Tecnologias

- Java 17
- Spring Boot 4.0.6
- Spring Security + JWT (jjwt 0.11.5)
- Spring Data JPA / Hibernate
- MySQL 8+
- Maven 3.8+
- SpringDoc OpenAPI 2.8.8 (Swagger UI)

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

# Conexão MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/agro_gestao?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=suapassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Swagger / OpenAPI
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=alpha
springdoc.swagger-ui.tagsSorter=alpha
springdoc.swagger-ui.tryItOutEnabled=true
springdoc.swagger-ui.filter=true

## Iniciar o projecto

bash
mvn spring-boot:run

A API fica disponível em `http://localhost:9010`.

---

## Documentação Swagger UI

A documentação interactiva da API está disponível assim que a aplicação estiver em execução:

| URL | Descrição |
|-----|-----------|
| `http://localhost:9010/swagger-ui/index.html` | Interface Swagger UI |
| `http://localhost:9010/api-docs` | Especificação OpenAPI 3.1 (JSON) |

### Como usar o Swagger UI

1. Inicie a aplicação com `mvn spring-boot:run`
2. Abra `http://localhost:9010/swagger-ui/index.html` no browser
3. Faça login em `POST /auth/login` para obter o token JWT
4. Clique no botão **Authorize 🔒** (canto superior direito)
5. Cole o token no campo `bearerAuth` e clique em **Authorize**
6. Todos os endpoints ficam desbloqueados para teste directo

### Pré-visualização

A documentação apresenta:

- **Título e versão** da API com badge OAS 3.1
- **Descrição** com tabela de perfis de acesso
- **Endpoints agrupados** por controller (agricultor, auth, comprador, produto, pedido, entrega)
- **Ícone de cadeado 🔒** nos endpoints que requerem autenticação
- **Try it out** — executa chamadas reais à API directamente no browser

---

## Autenticação

A API usa **JWT (JSON Web Token)**. Todos os endpoints (excepto `/auth/login` e `/auth/registar`) requerem autenticação.

### Perfis

| Perfil | Permissões |
|--------|------------|
| `ADMIN` | Acesso total |
| `AGRICULTOR` | Gerir produtos, ver e actualizar pedidos e entregas |
| `COMPRADOR` | Pesquisar produtos, efectuar pedidos e criar entregas |

### Registar utilizador

`POST /auth/registar`

json
{
  "username": "admin",
  "password": "admin123",
  "perfil": "ADMIN"
}

### Login

`POST /auth/login`

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


Use o token em todas as chamadas seguintes no header:


Authorization: Bearer {token}


---

## Endpoints

### Autenticação

| Método | Endpoint | Acesso | Descrição |
|--------|----------|--------|-----------|
| POST | `/auth/login` | Público | Fazer login |
| POST | `/auth/registar` | Público | Registar utilizador |

### Agricultores

| Método | Endpoint | Acesso | Descrição |
|--------|----------|--------|-----------|
| GET | `/agricultor` | ADMIN | Listar todos |
| GET | `/agricultor/{id}` | ADMIN | Buscar por ID |
| GET | `/agricultor/provincia/{provincia}` | ADMIN | Buscar por província |
| POST | `/agricultor` | ADMIN | Registar (aceita array) |
| PUT | `/agricultor/{id}` | ADMIN | Actualizar |
| DELETE | `/agricultor/{id}` | ADMIN | Remover |

### Compradores

| Método | Endpoint | Acesso | Descrição |
|--------|----------|--------|-----------|
| GET | `/comprador` | ADMIN, COMPRADOR | Listar todos |
| GET | `/comprador/{id}` | ADMIN, COMPRADOR | Buscar por ID |
| GET | `/comprador/provincia/{provincia}` | ADMIN, COMPRADOR | Buscar por província |
| GET | `/comprador/empresa/{nomeEmpresa}` | ADMIN, COMPRADOR | Buscar por empresa |
| POST | `/comprador` | ADMIN, COMPRADOR | Cadastrar (aceita array) |
| PUT | `/comprador/{id}` | ADMIN, COMPRADOR | Actualizar |
| DELETE | `/comprador/{id}` | ADMIN, COMPRADOR | Remover |

### Produtos

| Método | Endpoint | Acesso | Descrição |
|--------|----------|--------|-----------|
| GET | `/produto` | ADMIN, AGRICULTOR, COMPRADOR | Listar todos |
| GET | `/produto/{id}` | ADMIN, AGRICULTOR, COMPRADOR | Buscar por ID |
| POST | `/produto` | ADMIN, AGRICULTOR | Registar (aceita array) |
| PUT | `/produto/{id}` | ADMIN, AGRICULTOR | Actualizar |
| DELETE | `/produto/{id}` | ADMIN, AGRICULTOR | Remover |

### Pedidos

| Método | Endpoint | Acesso | Descrição |
|--------|----------|--------|-----------|
| GET | `/pedido` | ADMIN, AGRICULTOR, COMPRADOR | Listar todos |
| GET | `/pedido/{nomeProduto}` | ADMIN, AGRICULTOR, COMPRADOR | Buscar por nome do produto |
| POST | `/pedido` | ADMIN, COMPRADOR | Criar (aceita array) |
| PUT | `/pedido/{id}` | ADMIN, AGRICULTOR | Actualizar / Aprovar |

**Estados do pedido:** `PENDENTE` → `APROVADO` → `EM_TRANSPORTE` → `ENTREGUE` / `CANCELADO`

### Entregas

| Método | Endpoint | Acesso | Descrição |
|--------|----------|--------|-----------|
| GET | `/entrega` | ADMIN, AGRICULTOR, COMPRADOR | Listar todas |
| GET | `/entrega/{valor}` | ADMIN, AGRICULTOR, COMPRADOR | Buscar por data (YYYY-MM-DD) ou província |
| POST | `/entrega` | ADMIN, COMPRADOR | Criar (pedido deve estar APROVADO) |
| PUT | `/entrega/{id}/confirmar` | ADMIN, AGRICULTOR | Confirmar entrega |

**Estados da entrega:** `PENDENTE` → `EM_TRANSPORTE` → `ENTREGUE` / `CANCELADA`

---

## Fluxo de utilização


1. Registar utilizador   → POST /auth/registar
2. Fazer login           → POST /auth/login  (guardar token)
3. Registar agricultores → POST /agricultor
4. Registar compradores  → POST /comprador
5. Registar produtos     → POST /produto
6. Criar pedidos         → POST /pedido
7. Aprovar pedidos       → PUT  /pedido/{id}   (estado: APROVADO)
8. Criar entregas        → POST /entrega
9. Confirmar entregas    → PUT  /entrega/{id}/confirmar

---

## Modelos de dados

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


**Valores de `genero`:** `MASCULINO`, `FEMININO`, `OUTRO`

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
  "provinciaOrigem": "Maputo",
  "imagem": "https://exemplo.com/imagem.jpg"
}]

### Pedido

json
[{
  "comprador": { "id": 1 },
  "produto": { "id": 1 },
  "quantidade": 100.00
}]

> O `valorTotal` e a `dataPedido` são calculados automaticamente. O estado inicial é `PENDENTE`.

### Entrega

json
[{
  "pedido": { "id": 1 },
  "provinciaDestino": "Sofala"
}]


> Apenas pedidos com estado `APROVADO` podem gerar uma entrega.

---

## Estrutura do projecto


src/main/java/com/ujc/students/
├── config/          # SecurityConfig, OpenApiConfig
├── controller/      # AgricultorController, CompradorController,
│                    # ProdutoController, PedidoController,
│                    # EntregaController, AuthController
├── dao/             # Interfaces e implementações (AbstractDao)
├── model/           # Entidades JPA (Agricultor, Comprador, Produto,
│                    # Pedido, Entrega, Usuario)
├── security/        # JwtUtil, JwtFilter
└── service/         # Interfaces e implementações de negócio

src/main/resources/
├── application.properties
└── openapi.yaml     # Especificação OpenAPI 3.1


---

## Autores

- @Mario de Mario
- @Castigo Mata
- @Alvaro Hilario

**Prof:** momademha@gmail.com  
**Curso:** Engenharia e Tecnologia de Sistemas de Informação  
**Especialização:** Engenharia de Software  
**Disciplina:** Engenharia de Software  
**Ano Lectivo:** 2026
