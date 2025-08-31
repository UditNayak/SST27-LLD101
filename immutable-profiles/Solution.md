# Immutable UserProfile

## Project Overview
```sh
immutable-profiles/
├── README.txt
├── src/
│   ├── TryIt.java
│   └── com/
│       └── example/
│           └── profiles/
│               ├── ProfileService.java
│               ├── UserProfile.java
│               └── Validation.java
```

This project demonstrates **refactoring a mutable `UserProfile` class into an immutable class using the Builder Pattern**.  
The original design had:
- Multiple constructors
- Public setters (mutability leaks)
- Scattered and inconsistent validation logic

The refactored solution ensures:
- Immutability for `UserProfile`
- Centralized validation
- Clean and safe object creation using Builder.

---

## Problem Objective

- Refactor `UserProfile` to make it **immutable**.
- Remove all **public setters** and **multiple constructors**.
- Introduce a **Builder pattern** for controlled object creation.
- Ensure **required fields**: `id` and `email`.
- Support **optional fields**: `phone`, `displayName`, `address`, `marketingOptIn`, `twitter`, `github`.
- Centralize **validation logic** inside `Builder.build()` using `Validation` helpers.
- Update `ProfileService` to **stop mutating after creation**.

---

## Design Guidelines

### Immutability Rules
- **private final fields** for all attributes.
- **Constructor Injection** (takes Builder object).
- **No setters** — remove all mutator methods.
- Return **deep copy** in getters (for mutable fields).
- Mark the class as **final** to prevent subclassing.

### Builder Pattern Rules
- Create an **inner static Builder class** with the same attributes as the parent class (all mutable).
- Provide a **private constructor** in the parent class that accepts the Builder.
- All setter-like methods in the Builder will **return the Builder object** for method chaining.
- Implement a **build() method** in the Builder class to:
    - Perform all necessary **validations**.
    - Return an immutable `UserProfile` instance.

---

## Result: Before vs After Refactoring

### Before Refactoring (Mutable)
```shell
uditnayak@Udits-MacBook src % javac com/example/profiles/*.java TryIt.java
uditnayak@Udits-MacBook src % java TryIt
Before: a@b.com
After:  evil@example.com
=> In the solution, this setter disappears and object becomes immutable.
uditnayak@Udits-MacBook src % 
```


### After Refactoring (Immutable)
```shell
uditnayak@Udits-MacBook src % javac com/example/profiles/*.java TryIt.java
uditnayak@Udits-MacBook src % java TryIt                                  
Before: a@b.com
After (unchanged): a@b.com
=> Object is immutable. No setters exist.
uditnayak@Udits-MacBook src % 
```

---