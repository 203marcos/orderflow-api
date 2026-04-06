# Business Rules - Day 1

## Order Creation

- Only authenticated users can create orders.
- Order must contain at least one item.
- Item quantity must be greater than zero.
- Unit price must be non-negative.
- CEP format must be valid before requesting shipping quote.

## Ownership and Access

- A regular user can only read or update their own orders.
- Admin users can read any order.

## Status Transition Rules

Allowed transitions:

- `CREATED` -> `AWAITING_PAYMENT`
- `AWAITING_PAYMENT` -> `PAID`
- `AWAITING_PAYMENT` -> `CANCELLED`
- `PAID` -> `SHIPPED`
- `SHIPPED` -> `DELIVERED`

Forbidden transitions:

- Any transition from `DELIVERED`
- Any transition from `CANCELLED`
- `CREATED` -> `SHIPPED`
- `PAID` -> `CANCELLED`

## Payment Rules

- Payment amount must match order payable amount.
- An order cannot be marked as `PAID` without an approved charge.
- Once an approved payment exists, additional approval attempts are rejected.

## Integration and Resilience (Planned)

- CEP and shipping calls must enforce timeout.
- Retry applies only to transient failures.
- Fallback response must preserve API contract and observability.

