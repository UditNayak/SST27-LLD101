# Exercise C — Immutable Orders

## Project Overview
```sh
builder-orders/
├── README.txt
└── src/
    ├── TryIt.java
    └── com/
        └── example/
            └── orders/
                ├── Order.java
                ├── OrderLine.java
                ├── OrderService.java
                └── PricingRules.java
```
This project demonstrates **refactoring a mutable `Order` class into an immutable class using the Builder Pattern**.
The original design had:
- A mutable `Order` class with setters for all fields.
- No validation in the constructor or setters.
- Direct exposure of internal lists (e.g., `lines`).

The refactored design addresses these issues by:
- Making the `Order` class immutable.
- Using the Builder Pattern for controlled object creation.
- Implementing defensive copies to prevent external mutation.

## Problem Objective
- Prevent external modifications to an `Order` after it has been created.
- Provide a clean and flexible object construction mechanism without telescoping constructors.
- Validate essential fields (email, discount, order lines) in a single place before object creation.

## Key Design Choices
1. **Immutable Class Design**
   - All fields are declared `private final`.
   - No setters exist; only getters that return **deep copies** for mutable objects like `List<OrderLine>`.
   - Class marked as `final` to prevent subclassing.

2. **Defensive Copies**
   - Constructor creates deep copies of `OrderLine` objects from the builder.
   - Getters return new copies of internal lists to avoid reference leaks.

3. **Builder Pattern**
   - Inner static `Builder` class handles incremental configuration.
   - Provides fluent API with chainable setters.
   - `build()` method performs **centralized validation** using `PricingRules` before creating an `Order`.

4. **Validation Centralization**
   - All critical checks (valid email, valid discount, at least one line item) are done in the `build()` method to ensure object consistency.

## Result: Before vs After Refactoring

### Before Refactoring (Mutable)
```shell
uditnayak@Udits-MacBook src % javac com/example/orders/*.java TryIt.java
uditnayak@Udits-MacBook src % java TryIt
Before: 450
After:  180090
=> In the solution, totals remain stable due to defensive copies.
uditnayak@Udits-MacBook src % 
```


### After Refactoring (Immutable)
```shell
uditnayak@Udits-MacBook src % javac com/example/orders/*.java TryIt.java
uditnayak@Udits-MacBook src % java TryIt                                
Before: 450
After:  450
=> In the solution, totals remain stable due to defensive copies.
uditnayak@Udits-MacBook src % 
```

---

