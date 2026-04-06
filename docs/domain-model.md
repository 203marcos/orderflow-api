# Domain Model - Day 1

## Bounded Context

`OrderFlow` handles the complete lifecycle of an order from creation until delivery or cancellation.

## Core Entities

- `User`
  - Identity: `id`
  - Attributes: `name`, `email`, `passwordHash`, `role`, `createdAt`
  - Responsibility: authenticated actor that owns orders

- `Address`
  - Identity: `id`
  - Attributes: `cep`, `street`, `number`, `complement`, `neighborhood`, `city`, `state`
  - Responsibility: delivery destination linked to an order

- `Order`
  - Identity: `id`
  - Attributes: `status`, `totalAmount`, `shippingCost`, `createdAt`, `updatedAt`
  - Relationships: belongs to one `User`, has one `Address`, has many `OrderItem`, has many `Payment`

- `OrderItem`
  - Identity: `id`
  - Attributes: `sku`, `name`, `quantity`, `unitPrice`, `lineTotal`
  - Responsibility: immutable snapshot of item info at order creation

- `Payment`
  - Identity: `id`
  - Attributes: `amount`, `method`, `status`, `providerReference`, `createdAt`
  - Responsibility: track charge attempts and final payment state

## Supporting Concepts

- `OrderStatus`: `CREATED`, `AWAITING_PAYMENT`, `PAID`, `SHIPPED`, `DELIVERED`, `CANCELLED`
- `Role`: `USER`, `ADMIN`
- `PaymentStatus`: `PENDING`, `APPROVED`, `DECLINED`
- `PaymentMethod`: `CREDIT_CARD`, `PIX`, `BOLETO`

## Relationship Notes

- A `User` can own many `Order` records.
- An `Order` must have at least one `OrderItem`.
- An `Order` has exactly one delivery `Address`.
- An `Order` can have multiple `Payment` attempts, but only one approved charge.

