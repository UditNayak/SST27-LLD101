# Exercise ex04

How to run:
```bash
cd src
javac Demo04.java.java
java Demo04
```

Tasks:
- Identify the SOLID violation(s)
- Refactor to comply with all SOLID principles
- Keep behavior; add a tiny demo/test


## SOLID Violation(s) Check

### SRP
`PaymentService` is responsible for:
1. Deciding payment processing logic
2. Handling payment provider mapping

> 2 Reasons to change --> SRP violation

### OCP
- If we add a new payment provider (e.g., **NETBANKING**), we must **modify and test** `PaymentService`.
- If the logic for any provider changes, `PaymentService` needs modification.

> Not open for extension → OCP violation

### LSP
- Currently no inheritance in the code.

### ISP
- Currently no interfaces in the code.

### DIP
- `PaymentService` depends on concrete provider logic inside itself instead of abstractions.

---

### Solution

```bash
src/
 ├── PaymentService.java                     <--- Delegates to strategy
 ├── paymentStrategy/
 │     ├── Payment.java                      <--- Payment model
 │     ├── PaymentStrategy.java              <--- Strategy interface
 │     ├── CardPaymentStrategy.java          <--- Card payment logic
 │     ├── UpiPaymentStrategy.java           <--- UPI payment logic
 │     ├── WalletPaymentStrategy.java        <--- Wallet payment logic
 ├── PaymentStrategyFactory.java             <--- Strategy mapping logic
 └── Demo04.java                             <--- main class (entry point)
```

---

### Behavior before refactor
```bash
uditnayak@Udits-MacBook src % java Demo04 
Paid via UPI: 499.0
uditnayak@Udits-MacBook src % 
```

### Behavior after refactor
```bash
uditnayak@Udits-MacBook src % java Demo04      
Paid via UPI: 499.0
uditnayak@Udits-MacBook src % 
```

---

### SOLID Compliance after refactor
1. **SRP**: `PaymentService` now only handles payment processing, while `PaymentProcessorFactory` manages payment processor creation.
2. **OCP**: New payment methods can be added with minimal changes. Just implement a new `PaymentProcessor` and update the factory.
3. **LSP**: All payment processors can be used interchangeably without affecting the client code.
4. **ISP**: Each payment processor implements only the methods relevant to its functionality.
5. **DIP**: `PaymentService` depends on the `PaymentProcessor` interface, not on concrete implementations.

---
