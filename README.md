# OrderFlow API

Business-oriented backend API to manage orders with authentication, external integrations, resilience patterns, and cloud-ready delivery.

## Day 1 Goal (Current Stage)

Define the product scope and domain foundations before coding the full flow.

- Define bounded context and ubiquitous language.
- Define entities and relationships.
- Define core business rules and status transitions.
- Freeze MVP scope for the next implementation days.

## MVP Features (Planned)

- User registration and login with JWT.
- Orders creation and tracking.
- Order status update flow.
- CEP/address external integration.
- Shipping quote external integration.
- Payment simulation.
- Repeated query caching.
- Swagger/OpenAPI docs.
- Unit and integration tests.

## API Endpoints (Target)

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

## Branching Strategy

This project follows a simple Git Flow style:

- `main`: stable production-ready code.
- `develop`: integration branch for completed features.
- `feature/*`: implementation branches (for example `feature/day1-domain-foundation`).

Flow per feature:

1. Branch from `develop`.
2. Develop and commit in small units.
3. Merge feature into `develop`.
4. When stable, merge `develop` into `main`.

## Commit Convention

Use English Conventional Commits:

- `feat: ...`
- `fix: ...`
- `docs: ...`
- `test: ...`
- `chore: ...`
- `ci: ...`

Example:

`feat: add order aggregate and status transition rules`

## Day-by-Day Plan

- Day 1: domain, entities, business rules, MVP scope.
- Day 2: auth, user module, JWT security.
- Day 3: order module and PostgreSQL persistence with Flyway.
- Day 4: CEP and shipping integrations with timeout/retry/fallback.
- Day 5: payment flow and Redis cache.
- Day 6: tests and Swagger documentation.
- Day 7: Docker, GitHub Actions, and cloud deployment.

