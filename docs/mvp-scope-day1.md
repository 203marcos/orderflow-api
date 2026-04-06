# MVP Scope Freeze - Day 1

## In Scope for MVP

- JWT authentication and user identity endpoint.
- Order creation, listing, detail, and status update.
- CEP lookup integration.
- Shipping quote integration.
- Payment charge simulation.
- Basic cache for repeated CEP/shipping lookups.
- Swagger/OpenAPI documentation.
- Unit and integration tests for critical flows.

## Out of Scope for MVP

- Inventory reservation and stock consistency.
- Full payment gateway integration with settlement.
- Event-driven architecture and message broker.
- Multi-tenant support.
- Advanced analytics/reporting.

## Day 2 Handoff

Day 1 output enables Day 2 implementation with no domain ambiguity:

- Stable list of entities and statuses.
- Stable list of endpoint contracts.
- Stable business invariants for auth and order ownership.

