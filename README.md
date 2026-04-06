# OrderFlow API

Backend project focused on a realistic order flow: authentication, order lifecycle, external integrations, resilience, and cloud-ready delivery.

## Project Status

Current stage: **Day 1 completed**.

What is already done:
- Domain model and initial entities.
- Core business rules and order status transitions.
- MVP scope definition for the next implementation days.
- Repository workflow (`main`, `develop`, `feature/*`).

Reference docs:
- `docs/domain-model.md`
- `docs/business-rules.md`
- `docs/mvp-scope-day1.md`

## Architecture

The project will follow **hexagonal architecture** (ports and adapters).

What that means here:
- Business rules stay inside the core.
- Use cases define the application flow.
- Controllers, database access, and external APIs stay in adapters.

Planned package direction:
- `domain` for business rules and models
  - `domain.user`
  - `domain.order`
  - `domain.payment`
  - `domain.address`
- `application` for use cases and ports
  - `application.auth.usecase`
  - `application.auth.port.out`
  - `application.auth.dto`
- `adapter.in` for incoming traffic like REST controllers
- `adapter.out` for outgoing traffic like persistence and external clients

More details: `docs/architecture.md`

## Why this project

I built this repository to practice backend skills expected in junior Java positions:
- Building APIs around business rules, not only CRUD.
- Integrating external services (CEP and shipping).
- Applying security, persistence, cache, testing, and delivery practices.

## Tech Stack (current repository)

- Java 21
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA
- Flyway
- PostgreSQL driver
- Redis integration
- JUnit 5
- Gradle Wrapper

## Domain Highlights

Main concepts modeled so far:
- `User`
- `Address`
- `Order`
- `OrderItem`
- `Payment`

Main enums modeled so far:
- `Role`
- `OrderStatus`
- `PaymentMethod`
- `PaymentStatus`

At the moment, the domain is being prepared to move toward a cleaner core with ports and adapters.

## Planned API Endpoints (MVP)

- `POST /auth/register`
- `POST /auth/login`
- `GET /users/me`
- `POST /orders`
- `GET /orders/{id}`
- `GET /orders`
- `PATCH /orders/{id}/status`
- `GET /shipping/quote?cep=xxxxx-xxx`
- `GET /address/{cep}`
- `POST /payments/charge`

## Roadmap (7 days)

- [x] Day 1: domain, entities, business rules, MVP scope.
- [ ] Day 2: authentication, user module, JWT security.
- [ ] Day 3: order module + PostgreSQL persistence with Flyway.
- [ ] Day 4: CEP and shipping integrations with timeout/retry/fallback.
- [ ] Day 5: payment flow + Redis cache.
- [ ] Day 6: unit/integration tests + Swagger docs.
- [ ] Day 7: Docker + GitHub Actions + cloud deploy.

## Local Run

At this stage, the project is in foundation mode (Day 1), but the app and tests can run.

```powershell
.\gradlew.bat test
.\gradlew.bat bootRun
```

## Git Workflow

- `main`: stable branch.
- `develop`: integration branch.
- `feature/*`: implementation branches.

Default flow:
1. Create a feature branch from `develop`.
2. Commit in small units.
3. Merge into `develop`.
4. Promote `develop` into `main`.

Commit style:
- `feat: ...`
- `fix: ...`
- `docs: ...`
- `test: ...`
- `chore: ...`
- `ci: ...`


