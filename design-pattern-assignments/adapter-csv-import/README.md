# Adapter — CSV Import (Feature Implementation)

## Narrative (Current Code)
You must add a CSV import feature. The only available utility is a low-level `NaiveCsvReader` that returns `List<String[]>`. The domain service `ProfileService` expects validated fields.

## Your Task
Implement `CsvProfileImporter` that adapts `NaiveCsvReader` to the `ProfileImporter` interface:
- Parse: columns `id,email,displayName`
- Validate: skip invalid rows (missing id/email or bad email)
- Count successful imports

Wire `App` to accept a CSV path and print a summary.

## Acceptance Criteria
- `java com.example.imports.App users.csv` prints created profiles and `Imported N profiles`
- Invalid rows are skipped with an explanatory message
- `ProfileService` remains unchanged

## Hints
- Use `Objects.requireNonNull`
- Do validation in the adapter; keep the domain service clean

## Build & Run
```bash
cd adapter-csv-import/src
javac com/example/imports/*.java
java com.example.imports.App ../users.csv
```

## Folder Structure
```
adapter-csv-import/
├── src/
│   └── com/
│       └── example/
│           └── imports/
│               ├── App.java
│               ├── CsvProfileImporter.java
│               ├── NaiveCsvReader.java
│               ├── ProfileImporter.java
│               └── ProfileService.java
├── README.md
└── users.csv
```

## Problem Narrative:
You have two worlds that don’t match:

1. **Legacy / low-level utility (`NaiveCsvReader`)**
    - Old, very simple code: just reads lines and splits them into `List<String[]>`.
    - It doesn’t know anything about profiles or validation.

2. **Domain / business logic (`ProfileService`)**
    - It expects validated data (`id`, `email`, `displayName`).
    - It doesn’t want to deal with CSV parsing or garbage data.

---

### The Challenge
- `NaiveCsvReader` gives you raw, unstructured data (raw string arrays).
- `ProfileService` expects structured, validated data (profile fields).
- You cannot (and should not) change these classes because:
  - `NaiveCsvReader` is a legacy utility that might be used elsewhere.
    - Changing it could break existing functionality or introduce bugs.
    - It’s better to keep it simple and focused on its job: reading CSV files.
  - `ProfileService` is part of the core business logic and should remain clean.

You need to connect these two worlds without changing either of them.

> **This is a classic case for the Adapter Design Pattern!**

## Why Adapter Pattern here?
The Adapter Pattern allows us to **bridge incompatible interfaces**.

The Adapter Pattern is used when:
- You have an **existing class** (here: `NaiveCsvReader`) with an interface that doesn’t fit your needs.
- You want to **reuse it without modifying it** (maybe it’s legacy, external, or too widely used).
- You need to adapt its output to match the input expectations of another class (here: `ProfileService`).

So you write an adapter class (`CsvProfileImporter`) that:
- Talks to the legacy class.
- Transforms the output into the format your new system expects.
- Handles extra responsibilities (validation, error handling).

That way, the old code remains untouched, and the new code doesn’t have to care about how the old one works.

## Result
```shell
uditnayak@Udits-MacBook src % java com.example.imports.App ../users.csv
Created: 1 tony.stark@avengers.com Iron Man
Created: 2 bruce.wayne@dc.com Batman
Created: 3 jerry.mouse@cartoon.com Jerry
Invalid row (invalid data): 4,clark.kentdc.com,Superman
Invalid row (invalid data): 5,,Wonder Woman
Created: 6 tom.cat@cartoon.com Tom
Invalid row (missing columns): 7,spider.man@marvel.com
Imported 4 profiles
Import OK (wire the adapter to complete).
uditnayak@Udits-MacBook src % 
```

## SOLID Compliance Check

### Does Adapter Pattern Violate DIP?
No, it does not violate the Dependency Inversion Principle (DIP). The adapter depends on abstractions (interfaces) rather than concrete implementations.

### Another Problem
- We are doing same validation twice:
  - In `CsvProfileImporter` (adapter).
  - In `ProfileService` (domain service).
