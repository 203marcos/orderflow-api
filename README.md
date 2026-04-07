# OrderFlow API

OrderFlow API is a backend project built to practice the kind of code a junior Java developer is expected to write: clear rules, simple structure, security, persistence, and small commits.

## Project status

Current stage: **Day 2 implemented**.

What is already done:
- Day 1 domain and business rules.
- Hexagonal package split with `in` and `out`.
- User persistence port and JPA adapter.
- Basic JWT authentication flow.
- Stateless Spring Security setup.

Reference docs:
- `docs/domain-model.md`
- `docs/business-rules.md`
- `docs/mvp-scope-day1.md`
- `docs/architecture.md`

## Why this project exists

This repository is a study project, but the goal is to keep it close to a real backend job:
- build around business rules instead of only CRUD,
- separate application, domain, and infrastructure,
- use JWT, Spring Security, JPA, and validation,
- keep the code easy to read for interviews and code reviews.

## Architecture

The project follows **hexagonal architecture**.

Simple version:
- `domain` keeps the business model.
- `application` keeps use cases and ports.
- `adapter.in` keeps HTTP and API entry points.
- `adapter.out` keeps persistence, security integration, and external services.

### Project structure

```text
src/main/java/com/marcosdias/orderflowapi
├── adapter
│   ├── in
│   │   └── web
│   └── out
│       └── persistence
│           └── user
├── application
│   └── auth
│       ├── dto
│       ├── exception
│       ├── port
│       └── usecase
├── domain
│   ├── address
│   ├── order
│   ├── payment
│   └── user
└── security
```

## Current stack

- Java 21
- Spring Boot 3
- Spring Web
- Spring Security
- Spring Data JPA
- H2 for local development
- PostgreSQL driver for future production setup
- JWT (jjwt)
- JUnit 5
- Gradle Wrapper

## What the domain covers

Main concepts:
- `User`
- `Address`
- `Order`
- `OrderItem`
- `Payment`

Main enums:
- `Role`
- `OrderStatus`
- `PaymentMethod`
- `PaymentStatus`

## API endpoints

### Auth
- `POST /auth/register`
- `POST /auth/login`
- `GET /users/me`

### Planned for the next days
- `POST /orders`
- `GET /orders/{id}`
- `GET /orders`
- `PATCH /orders/{id}/status`
- `GET /shipping/quote?cep=xxxxx-xxx`
- `GET /address/{cep}`
- `POST /payments/charge`

## Roadmap

- [x] Day 1: domain, rules, MVP scope.
- [x] Day 2: auth, JWT, user module, security.
- [ ] Day 3: order module + PostgreSQL persistence with Flyway.
- [ ] Day 4: CEP and shipping integration with timeout/retry/fallback.
- [ ] Day 5: payment flow + Redis cache.
- [ ] Day 6: tests + Swagger.
- [ ] Day 7: Docker + GitHub Actions + deployment.

## Local run

```powershell
.\gradlew.bat test
.\gradlew.bat bootRun
```

The app now starts with an embedded H2 database, so it runs locally without extra environment variables.

## Git workflow

- `main`: stable branch.
- `develop`: integration branch.
- `feature/*`: branch for each small task.

Suggested flow:
1. Branch from `develop`.
2. Work in small commits.
3. Merge back into `develop`.
4. Promote `develop` into `main` when the day is finished.

Commit style used in this project:
- `feat: ...`
- `fix: ...`
- `docs: ...`
- `test: ...`
- `chore: ...`


