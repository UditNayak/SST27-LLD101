# Exercise ex01

How to run:
```bash
cd src
javac Demo01.java.java
java Demo01
```

Tasks:
- Identify the SOLID violation(s)
- Refactor to comply with all SOLID principles
- Keep behavior; add a tiny demo/test


## SOLID Violation(s) Check

### SRP
Current `OrderService` is doing 3 jobs:
1. Calculating Amount Post Tax (`totalWithTax()`)
2. Sending Email (`email.send()`)
3. Storing Data in DB

Any change in tax logic, email content or DB storage will require modifying `OrderService`.

> 3 reasons of change --> violation of SRE

### OCP
- If tomorrow we add **SMS Notification** instead of only Email, we need to modify `OrderService`.
- If tomorrow we introduce a different tax policy (e.g., Import Duty, GST, VAT), we need to modify `OrderService`.

> Not open to extend & closed to modify --> violation of OCP

### LSP
- There is no subclasses or inheritance in the current code, so LSP is not violated.

### ISP
- Currently there are no interfaces.

### DIP
- Current `OrderService` class depends on a concrete class:
    ```bash
    EmailClient email = new EmailClient();
    ```
- If we want to switch to **SMSClient**, we need to modify `OrderService`.

> `OrderService` is not agnostic to the type of Notifier being used --> DIP Violation

---

### Solution

```bash
TaxCalculator.java         <-- Interface
DefaultTaxCalculator.java  <-- Implementation of TaxCalculator
Notifier.java              <-- Interface
EmailNotifier.java         <-- Implementation of Notifier
OrderRepository.java       <-- Handles DB storage logic
OrderService.java          <-- Coordinator class (depends on abstractions)
Demo01.java                <-- main method (starting point)
```

---

### Behaviour before Refactor
```bash
uditnayak@Udits-MacBook src % java Demo01 
[EMAIL to=a@shop.com] Thanks! Your total is 118.0
Order stored (pretend DB).
uditnayak@Udits-MacBook src % 
```

### Behaviour after Refactor
```bash
uditnayak@Udits-MacBook src % java Demo01 
[EMAIL to=a@shop.com] Thanks! Your total is 118.0
Order stored (pretend DB).
uditnayak@Udits-MacBook src % 
```

---

### SOLID Compliance Check After Refactor

- **SRP**: Each class has only one responsibility
- **OCP**: 
    - Add a new `Notifier` (**SMS**) without modifying `OrderService`
    - Add a new `TaxCalculator` (ImportTax, GST, VAT) without modifying `OrderService`
- **LSP**: Subclasses (new tax calculators or notifiers) can replace base classes without breaking code.
- **ISP**: Both the Interfaces (TaxCalculator, Notifier) has only one method; No fat Interfaces
- **DIP**: `OrderService` depends on abstract Interfaces (Notifier, TaxCalculator), not concrete classes.

---