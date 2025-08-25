# Exercise ex08

How to run:
```bash
cd src
javac Demo08.java.java
java Demo08
```

Tasks:
- Identify the SOLID violation(s)
- Refactor to comply with all SOLID principles
- Keep behavior; add a tiny demo/test

## SOLID Violation(s) Check

### SRP
`Vehicle` interface is responsible for:
1. Engine operations (startEngine)
2. Pedal operations (pedal)
3. Battery operations (recharge)

> 3 reasons to change → SRP violation

### OCP
- If we introduce a new vehicle type (e.g., `CNG`), we must modify the `Vehicle` interface or existing implementations.
- For vehicles without engines or batteries, the code will throw exceptions.

> Not open for extension → OCP violation

### LSP
- `Bicycle` cannot be used as a `Vehicle` because it does not support `startEngine` and `stopEngine`.
- Client code expecting all `Vehicle` methods to work fails at runtime.

> LSP violation

### ISP
- `Vehicle` interface forces `Bicycle` to implement methods it doesn't use.

> ISP violation

### DIP
- Currently no violation.

---

### Solution

```bash
src/
├── Demo08.java
├── EngineVehicle.java
├── PedalVehicle.java
├── ElectricVehicle.java
├── Bicycle.java
├── Car.java
├── E_Bike.java
```

---

### Behavior before refactor

```bash
uditnayak@Udits-MacBook src % java Demo08 
Exception in thread "main" java.lang.UnsupportedOperationException
        at Bicycle.startEngine(Bicycle.java:2)
        at Demo08.main(Demo08.java:4)
uditnayak@Udits-MacBook src % 
```

### Behaviour after refactor
```bash
uditnayak@Udits-MacBook src % java Demo08      
Pedaling with effort: 5
Car engine started
Recharging E-Bike with 10 kWh
uditnayak@Udits-MacBook src % 
```

---

### SOLID Compliance after refactor

- SRP: Each interface and class now has a single reason to change.
- OCP: New vehicle types (e.g., `CNG`) can be added without modifying existing code.
- LSP: All implementations respect their contracts—no method throws `UnsupportedOperationException`.
- ISP: No class depends on methods it does not use.
- DIP: High-level modules are not dependent on low-level modules.

---
