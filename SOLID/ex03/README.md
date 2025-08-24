# Exercise ex03

How to run:
```bash
cd src
javac Demo03.java.java
java Demo03
```

Tasks:
- Identify the SOLID violation(s)
- Refactor to comply with all SOLID principles
- Keep behavior; add a tiny demo/test

## SOLID Violation(s) Check

### SRP
`ShippingCostCalculator` is responsible for:
1. Deciding cost calculation logic
2. Handling shipping type mapping

> 2 Reasons to change --> SRP violation

### OCP
- If tomorrow we need to change the shipping cost calculation logic, we need to modify & test entire `ShippingCostCalculator`.
- If tomorrow we need to add a new shipping type (e.g., international), we need to modify & test the `ShippingCostCalculator`.

> Not open for extension --> OCP violation

### LSP
- Currently no inheritance in the code.

### ISP
- Currently no interfaces in the code.

### DIP
- `ShippingCostCalculator` depends on concrete logics inside itself.
- It should depend on abstractions (strategies) instead of hardcoded types.

---

### Solution

```bash
src/
 ├── ShippingCostCalculator.java            <--- Shipping cost calculation logic
 ├── shippingStrategy/
 │     ├── Shipment.java                    <--- Shipment model
 │     ├── ShippingCostStrategy.java        <--- Strategy interface
 │     ├── StandardShippingCost.java        <--- Standard shipping strategy
 │     ├── ExpressShippingCost.java         <--- Express shipping strategy
 │     ├── OvernightShippingCost.java       <--- Overnight shipping strategy
 ├── ShippingStrategyFactory.java           <-- Strategy mapping logic
 └── Demo03.java                            <-- main class (entry point)
 ```

---

### Behavior before refactor
```bash
uditnayak@Udits-MacBook src % java Demo03 
96.0
uditnayak@Udits-MacBook src % 
```

### Behavior after refactor
```bash
uditnayak@Udits-MacBook src % java Demo03      
96.0
uditnayak@Udits-MacBook src % 
```

---

### SOLID Compliance after refactor
- **SRP**: No class has more than one reason to change.
- **OCP**: Changing the shipping cost calculation logic or adding a new shipping type can be done by adding new strategy classes without modifying existing code.
- **LSP**: Subtypes can be used interchangeably without affecting the correctness of the program.
- **ISP**: No fat interfaces; `ShippingCostStrategy` is minimal and focused on a single responsibility.
- **DIP**: `ShippingCostCalculator` depends on the `ShippingCostStrategy` abstraction, not on concrete implementations.