OrderFlow API
Backend Java com foco em padrões de mercado para vaga de Desenvolvedor Java Júnior: arquitetura hexagonal, segurança JWT, persistência relacional, integração externa resiliente, testes automatizados e entrega contínua.

Objetivo do projeto
Este projeto foi criado para demonstrar competências esperadas em backend Java júnior em ambiente real:

Modelagem de domínio além de CRUD simples
Separação clara entre domínio, aplicação e infraestrutura
Segurança stateless com JWT e Spring Security
Persistência com PostgreSQL e versionamento de schema
Integrações externas com tratamento de falhas
Testes automatizados e pipeline de CI
Status atual
Etapa atual: Fundações de domínio e autenticação concluídas.

Entregue até agora:

Regras de negócio iniciais de domínio
Estrutura baseada em arquitetura hexagonal
Persistência de usuário com JPA
Fluxo de autenticação com JWT
Configuração de segurança stateless
Arquitetura
A API segue arquitetura hexagonal para reduzir acoplamento e facilitar evolução.

Camadas principais:

Domain: entidades e regras de negócio centrais
Application: casos de uso e portas
Adapter In: entrada HTTP (controllers e contratos)
Adapter Out: persistência, segurança e integrações externas
Benefícios desta abordagem:

Testes mais fáceis em casos de uso
Troca de tecnologia de infraestrutura com menor impacto
Código mais legível para review técnico
Stack
Java 21
Spring Boot 3
Spring Web
Spring Security
Spring Data JPA
PostgreSQL
Flyway
JWT
Bean Validation
JUnit 5
Gradle Wrapper
OpenAPI Swagger
Docker e Docker Compose
GitHub Actions
Domínio coberto
Conceitos:

User
Address
Order
OrderItem
Payment
Enums:

Role
OrderStatus
PaymentMethod
PaymentStatus
Endpoints
Autenticação e usuário:

POST /auth/register
POST /auth/login
GET /users/me
Pedidos:

POST /orders
GET /orders/{id}
GET /orders
PATCH /orders/{id}/status
Integrações:

GET /address/{cep}
GET /shipping/quote?cep=xxxxx-xxx
Pagamento:

POST /payments/charge
Qualidade e testes
Estratégia de testes:

Testes unitários para regras de domínio e casos de uso
Testes de integração para repositórios e fluxos de aplicação
Testes HTTP para autenticação, autorização e contratos da API
Testes de segurança para endpoints protegidos
Coberturas obrigatórias:

Fluxo completo de login e autorização por perfil
Transições válidas e inválidas de status de pedido
Regras de ownership em leitura de pedido
Validações de entrada e resposta de erro padronizada
Observabilidade e erro
Padrão de erro único:

timestamp
status
code
message
path
traceId
Observabilidade:

Healthcheck
Métricas básicas
Logs estruturados por requisição
Como executar localmente
Pré-requisitos:

JDK 21+
Docker + Docker Compose
Fluxo recomendado:

Subir PostgreSQL com Docker Compose
Executar migrations Flyway
Rodar testes
Subir aplicação
Comandos:

gradlew.bat test
gradlew.bat bootRun
Documentação da API
Swagger UI: /swagger-ui.html
OpenAPI JSON: /v3/api-docs
CI e fluxo de trabalho
Branches:

main: estável
develop: integração
feature/nome-da-tarefa: desenvolvimento incremental
Padrão de commits:

feat:
fix:
docs:
test:
chore:
Pipeline de CI:

build
test
validação de qualidade
falha obrigatória em caso de regressão
