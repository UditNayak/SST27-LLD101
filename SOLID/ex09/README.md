# Exercise ex09

How to run:
```bash
cd src
javac Demo09.java.java
java Demo09
```

Tasks:
- Identify the SOLID violation(s)
- Refactor to comply with all SOLID principles
- Keep behavior; add a tiny demo/test


## SOLID Violation(s) Check

### SRP
`OrderController` is responsible for:
1. Handling **order creation** logic.
2. Managing **persistence** (saving orders to a database).

> 2 reasons to change → SRP violation

### OCP
- If we need to change the way orders are saved (e.g., switch from SQL to NoSQL), we must modify `OrderController`.
- Adding a new order processing step (e.g., sending a confirmation email) requires changes to `OrderController`.

> Not open for extension → OCP violation

### LSP
- Currently no inheritance in the code.

### ISP
- Currently no interfaces in the code.

### DIP
- `OrderController` depends on concrete class for order persistence (`SqlOrderRepository`) and order processing.
- Violates “high-level modules should not depend on low-level modules”.

> DIP violation

---

### Solution: Interface + Dependency Inversion

```bash
src/
 ├── OrderController.java
 ├── OrderRepository.java        <--- abstraction
 ├── SqlOrderRepository.java     <--- concrete implementation
 └── Demo09.java
```

---

### Behavior before refactor

```bash
uditnayak@Udits-MacBook src % java Demo09 
Saved order ORD-1 to SQL
Created order: ORD-1
uditnayak@Udits-MacBook src % 
```

### Behavior after refactor

```bash
uditnayak@Udits-MacBook src % java Demo09      
Saved order ORD-1 to SQL
Created order: ORD-1
uditnayak@Udits-MacBook src % 
```

---

### SOLID Compliance after Refactor
- **SRP**: `OrderController` now only handles order creation, while `OrderRepository` manages persistence.
- **OCP**: Adding a new repository type (e.g., `MongoOrderRepository`) does not require changes in `OrderController`.
- **LSP**: All `OrderRepository` implementations can replace each other without breaking behavior.
- **ISP**: No violation as `OrderRepository` is a focused interface.
- **DIP**: `OrderController` depends on the abstraction (`OrderRepository`), not the concrete implementation.
