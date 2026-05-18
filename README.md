# 📚 Desafio Back-end — API REST de Gestão de Biblioteca

> Construa uma API completa para gerenciar o acervo, empréstimos e usuários de uma biblioteca pública, seguindo boas práticas de arquitetura e segurança.

![Java](https://img.shields.io/badge/Java-17+-orange?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=flat-square)
![Nível](https://img.shields.io/badge/N%C3%ADvel-Intermedi%C3%A1rio-yellow?style=flat-square)
![Prazo](https://img.shields.io/badge/Prazo-7%20dias-red?style=flat-square)

---

## 🎯 Contexto

A Biblioteca Municipal de Miraflores precisa modernizar seu sistema. Você foi contratado para criar a **camada de backend** da nova plataforma. O sistema deve permitir que atendentes cadastrem livros e usuários, registrem empréstimos e devoluções, e que leitores consultem o catálogo. A API precisa ser segura, bem documentada e pronta para produção.

---

## 📊 Visão Geral

| Item | Detalhe |
|------|---------|
| Entidades principais | 4 entidades |
| Endpoints mínimos | 18 endpoints |
| Cobertura de testes | ≥ 70% |
| Autenticação | JWT / OAuth2 |

---

## 🗂️ Entidades do Domínio

- `Book`
- `User`
- `Loan`
- `Author`
- `Category`

---

## 🔌 Endpoints Esperados

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/auth/login` | Autenticação e geração de token JWT |
| `GET` | `/api/books?page=0&size=10&search=` | Listar livros com paginação, filtro por título, autor e categoria |
| `POST` | `/api/books` | Cadastrar novo livro *(somente ADMIN)* |
| `PUT` | `/api/books/{id}` | Atualizar dados do livro *(somente ADMIN)* |
| `DELETE` | `/api/books/{id}` | Remover livro do acervo *(somente ADMIN)* |
| `GET` | `/api/books/{id}` | Buscar detalhes de um livro |
| `GET` | `/api/authors` | Listar autores |
| `POST` | `/api/authors` | Cadastrar autor *(somente ADMIN)* |
| `GET` | `/api/categories` | Listar categorias |
| `POST` | `/api/categories` | Cadastrar categoria *(somente ADMIN)* |
| `POST` | `/api/users` | Cadastrar novo usuário |
| `GET` | `/api/users/{id}` | Buscar dados de um usuário |
| `PUT` | `/api/users/{id}` | Atualizar dados de um usuário |
| `POST` | `/api/loans` | Registrar empréstimo — valida disponibilidade e limite por usuário |
| `PATCH` | `/api/loans/{id}/return` | Registrar devolução e calcular multa por atraso |
| `GET` | `/api/loans/overdue` | Listar empréstimos em atraso *(somente ADMIN/ATTENDANT)* |
| `GET` | `/api/users/{id}/loans` | Histórico de empréstimos de um usuário |
| `GET` | `/api/reports/top-books` | Relatório: livros mais emprestados (período configurável) |

---

## ⚙️ Requisitos Técnicos

### 🗄️ Persistência
- Spring Data JPA + Hibernate
- PostgreSQL em produção
- H2 para testes
- Migrations com Flyway
- Índices em campos de busca

### 🔒 Segurança
- Spring Security 6.x
- JWT stateless (Bearer)
- Roles: `ADMIN`, `ATTENDANT`, `READER`
- Controle por anotação (`@PreAuthorize`)
- Senha encriptada com BCrypt

### ✅ Qualidade de Código
- Testes unitários com JUnit 5
- Testes de integração com `@SpringBootTest`
- Mockito para mocks
- Bean Validation (jakarta)
- Global exception handler com `@ControllerAdvice`

### 📄 Documentação
- Springdoc OpenAPI 3
- Swagger UI funcional
- README com setup local
- Collection Postman/Insomnia
- Diagrama ER do banco de dados

### 🏗️ Arquitetura
- Camadas: Controller → Service → Repository
- DTOs de request e response separados
- MapStruct ou conversão manual
- Paginação via `Pageable`
- Logs estruturados (SLF4J)

### 🐳 Infraestrutura
- Docker + docker-compose
- Perfis de ambiente: `dev`, `test`, `prod`
- Variáveis de ambiente via `.env`
- Health check via Spring Actuator (`/actuator/health`)
- Dockerfile multi-stage

---

## 📋 Regras de Negócio

- **Limite de empréstimos:** cada usuário pode ter no máximo **3 livros** simultaneamente.
- **Prazo de devolução:** padrão de **14 dias corridos** a partir da data do empréstimo.
- **Multa por atraso:** **R$ 0,50 por dia** de atraso, calculada automaticamente na devolução.
- **Bloqueio de usuário:** usuários com multa pendente **não podem fazer novos empréstimos**.
- **Disponibilidade:** cada exemplar físico só pode estar em **um empréstimo ativo** por vez.
- **Soft delete:** livros e usuários não são removidos permanentemente do banco.

---

## 📐 Estrutura de Pacotes Sugerida

```
src/main/java/com/biblioteca/
├── config/           # Configurações de segurança, Swagger, etc.
├── controller/       # Camada de apresentação (REST Controllers)
├── domain/
│   ├── model/        # Entidades JPA
│   ├── repository/   # Interfaces Spring Data
│   └── service/      # Regras de negócio
├── dto/
│   ├── request/      # DTOs de entrada
│   └── response/     # DTOs de saída
├── exception/        # Exceções customizadas e handler global
├── mapper/           # Conversão Model <-> DTO
└── security/         # JWT, filters, UserDetails
```

---

## 🚀 Como Rodar Localmente

**Pré-requisitos:** Docker e Docker Compose instalados.

```bash
# 1. Clone o repositório
git clone https://github.com/seu-usuario/biblioteca-api.git
cd biblioteca-api

# 2. Copie o arquivo de variáveis de ambiente
cp .env.example .env

# 3. Suba os containers
docker-compose up -d

# 4. Acesse a documentação
open http://localhost:8080/swagger-ui.html
```

---

## 🧪 Rodando os Testes

```bash
# Todos os testes
./mvnw test

# Com relatório de cobertura (JaCoCo)
./mvnw verify
```

---

## 📈 Critérios de Avaliação

| Critério | Peso |
|----------|------|
| Funcionalidade dos endpoints | 30% |
| Qualidade e organização do código | 25% |
| Testes automatizados | 20% |
| Segurança e autenticação | 15% |
| Documentação e README | 10% |

---

## ⭐ Diferenciais (Opcionais)

Implemente um ou mais dos itens abaixo para se destacar:

- [ ] Cache com **Redis** para listagens frequentes
- [ ] Envio de **e-mail de lembrete** de devolução (ex.: via SendGrid ou JavaMail)
- [ ] **CI/CD com GitHub Actions** (build, testes e análise de cobertura automática)
- [ ] **Rate limiting** nos endpoints públicos
- [ ] Paginação com **cursor** em vez de offset

---

## 📦 Entrega

- **Repositório:** GitHub público com histórico de commits significativo (não um único commit gigante).
- **README:** este arquivo, com instruções de como rodar localmente funcionando.
- **Commits:** mensagens claras e descritivas (preferencialmente seguindo [Conventional Commits](https://www.conventionalcommits.org/pt-br/)).

---
