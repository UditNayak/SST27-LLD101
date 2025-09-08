# Adapter — Payments (Refactoring)

## Narrative (Current Code)
OrderService directly depends on two mismatched SDKs (`FastPayClient`, `SafeCashClient`), uses a string `provider` switch, and duplicates glue logic.

## Your Task
Introduce an **Adapter** so `OrderService` depends only on a `PaymentGateway` interface. Create:
- `PaymentGateway` (target interface): `String charge(String customerId, int amountCents)`
- `FastPayAdapter` and `SafeCashAdapter` mapping to their respective SDKs
- A simple map-based registry in `App` to select the gateway

Refactor `OrderService` to accept a `PaymentGateway` and remove provider branching.

## Acceptance Criteria
- `OrderService` calls **only** `PaymentGateway`
- Adding a new provider requires no change to `OrderService`
- Running `App` prints transaction IDs for both providers

## Hints
- Use constructor injection or a `Map<String, PaymentGateway>`
- Keep adapters stateless
- Use `Objects.requireNonNull` to validate inputs

## Build & Run
```bash
cd adapter-payments/src
javac com/example/payments/*.java
java com.example.payments.App
```

## Folder Structure
```
adapter-payments/
    ├── src/
    │   └── com/example/payments/
    │       ├── App.java
    │       ├── PaymentGateway.java
    │       ├── OrderService.java
    │       ├── FastPayClient.java      (3rd party SDK)
    │       ├── FastPayAdapter.java     (wraps FastPayClient + implements PaymentGateway)
    │       ├── SafeCashClient.java     (3rd party SDK)
    │       ├── SafeCashPayment.java    (part of SafeCashClient)
    │       └── SafeCashAdapter.java    (wraps SafeCashClient + implements PaymentGateway)
    └── README.md
```

## Problem Narrative
You have two worlds that don’t match:

1. **Third-party payment SDKs**
   - `FastPayClient` → exposes `payNow(customerId, amountCents)`.
   - `SafeCashClient` → exposes `createPayment(amount, user)` which returns a `SafeCashPayment`, and then you must call `.confirm()`.
   - Both have **different, incompatible APIs**.

2. **Domain / business logic (`OrderService`)**
   - Needs to charge customers in a **consistent way**.
   - Should not know the details of each SDK.
   - Currently uses a **string `provider` switch** and **duplicates glue logic** for each provider.

---

### The Challenge
- The two SDKs have **different interfaces** for doing the same thing: charging a customer.
- `OrderService` has to know about **both SDKs**, making it tightly coupled.
- Adding a new provider means **modifying `OrderService`** (violates Open/Closed Principle).
- This makes the system rigid and harder to maintain.

You need to decouple `OrderService` from these provider SDKs and unify them behind a single, consistent interface.

> **This is a classic case for the Adapter Design Pattern!**

## Why Adapter Pattern here?
The Adapter Pattern allows us to **bridge incompatible interfaces**.

It is used when:
- You have **existing classes** (here: third-party payment SDKs) with different APIs.
- You want to **reuse them without modifying them** (since they are external/legacy).
- You need to provide a **common interface** to the rest of your system.

So you write adapter classes:
- `FastPayAdapter` wraps `FastPayClient`.
- `SafeCashAdapter` wraps `SafeCashClient` and its `SafeCashPayment`.
- Both implement the **`PaymentGateway` interface**, exposing a uniform `charge(customerId, amountCents)` method.

That way:
- `OrderService` depends only on the `PaymentGateway` abstraction.
- Adding new providers becomes easy → just add a new adapter, no changes to `OrderService`.


## Result
```shell
uditnayak@Udits-MacBook src % java com.example.payments.App
FP#cust-1:1299
SC#pay(cust-2,1299)
uditnayak@Udits-MacBook src % 
```

## SOLID Compliance Check

### Before Refactor (problematic state described in the narrative)
- **DIP (Dependency Inversion Principle) – Violated**
  - `OrderService` directly called concrete SDKs (`FastPayClient`, `SafeCashClient`) and knew their call sequences (e.g., `createPayment(...).confirm()`).
  - High-level policy (charging an order) depended on low-level details (SDK shapes).
- **OCP (Open/Closed Principle) – Violated**
  - Adding a new provider required modifying `OrderService` (new `if/switch` branches and glue code).
- **SRP (Single Responsibility Principle) – Smell**
  - `OrderService` mixed business logic with provider-specific orchestration.

### After Refactor (the solution in this repo)
- **DIP – Satisfied**
  - `OrderService` depends only on the abstraction `PaymentGateway`.
  - Adapters (`FastPayAdapter`, `SafeCashAdapter`) depend on SDK concretes—this is OK because they are low-level details.
  - The composition root (`App`) wires concretes to abstractions (acceptable place to depend on concretes).
- **OCP – Satisfied**
  - New providers are added by creating a new adapter implementing `PaymentGateway` and registering it in `App`. No changes to `OrderService`.
- **SRP – Improved**
  - `OrderService` focuses on charging via a gateway; provider-specific translation lives in adapters.
- **ISP (Interface Segregation Principle) – Reasonable**
  - `PaymentGateway` is a small, focused interface with a single operation `charge(customerId, amountCents)`.
- **LSP (Liskov Substitution Principle) – Maintained**
  - All adapters can substitute for `PaymentGateway` without changing `OrderService` behavior.

### Notes & Small Enhancements
- **Provider Lookup by String Keys**
  - Using `Map<String, PaymentGateway>` is fine; consider an enum or a typed registry to avoid typos.
- **Stateless Adapters**
  - Keep adapters free of mutable state; they should only translate calls to the underlying SDKs.
- **Validation**
  - Keep input validation at boundaries (e.g., `Objects.requireNonNull`, non-negative amounts) in `OrderService` or before invoking it; adapters should avoid duplicating domain validation.