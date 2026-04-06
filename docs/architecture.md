# Architecture - Hexagonal Approach

OrderFlow API will follow a **hexagonal architecture** (ports and adapters) so the business rules stay independent from Spring, JPA, and external integrations.

## Goal

Keep the center of the application pure and easy to test.

- Domain rules live in plain Java.
- Use cases define application behavior.
- Interfaces define the ports.
- Controllers, databases, and HTTP clients are adapters.

## Layer Direction

Dependencies should point inward:

`adapter.in` / `adapter.out` -> `application` -> `domain`

The `domain` layer should never depend on Spring, web, or persistence APIs.

## Why split adapters into `in` and `out`?

Yes — this is the best fit for OrderFlow API.

- `adapter.in` handles incoming traffic: REST controllers, request DTOs, and message consumers.
- `adapter.out` handles outgoing traffic: databases, external HTTP clients, queues, and other integrations.

This split is easy to explain and helps a junior developer see the dependency direction fast.

## Suggested Package Structure

- `com.marcosdias.orderflowapi.domain`
  - Entities, value objects, enums, and business rules
  - Example subpackages: `domain.user`, `domain.order`, `domain.payment`, `domain.address`
- `com.marcosdias.orderflowapi.application`
  - Use cases, input ports, output ports, and small command/view objects
  - Example subpackages for Day 2: `application.auth.usecase`, `application.auth.port.out`, `application.auth.dto`
- `com.marcosdias.orderflowapi.adapter.in.web`
  - REST controllers and request/response DTOs
- `com.marcosdias.orderflowapi.adapter.out.persistence`
  - JPA entities, Spring Data repositories, and persistence mappers
- `com.marcosdias.orderflowapi.adapter.out.client`
  - External API clients for CEP, shipping, and payments
- `com.marcosdias.orderflowapi.config`
  - Spring configuration and bean wiring

## Day 2 Direction

For authentication, the first use cases will likely be:

- `RegisterUserUseCase`
- `AuthenticateUserUseCase`
- `GetCurrentUserUseCase`

Ports to keep the core independent:

- `UserRepositoryPort`
- `PasswordEncoderPort`
- `JwtTokenPort`
- `CurrentUserPort`

Suggested auth flow:

`adapter.in.web.AuthController` -> `application.auth.usecase.*` -> `application.auth.port.out.*` -> `adapter.out.persistence` / `adapter.out.client`

## Practical Rule

If a class is part of business logic, it should be easy to test without starting Spring.

## Quick Classification Rule

- If it receives input from the outside world, it goes to `adapter.in`.
- If it sends output to the outside world, it goes to `adapter.out`.
- If it contains rules and decisions, it goes to `domain`.
- If it orchestrates a use case, it goes to `application`.

## Keep it simple

For this project, avoid creating too many nested folders too early. Start with the layer split and then group domain concepts by business area inside `domain`.

