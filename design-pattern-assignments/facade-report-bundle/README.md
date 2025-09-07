# Facade — Report Bundle Export (Feature Implementation)

## Narrative (Current Code)
Caller orchestrates 3 utilities: `JsonWriter` → `Zipper` → `AuditLog`. This is error-prone and leaks subsystem order & details.

## Your Task
Create `ReportBundleFacade` with:
```java
Path export(java.util.Map<String,Object> data, java.nio.file.Path outDir, String baseName)
```
Inside, orchestrate: write JSON → zip it to `baseName.zip` in `outDir` → log success.
Refactor `App` to use **only** the facade.

## Acceptance Criteria
- `App` no longer directly uses `JsonWriter`, `Zipper`, or `AuditLog`
- On success: prints path to the `.zip` and writes an entry to `audit.log`
- Any IO errors surface as `UncheckedIOException`

## Hints
- Keep the facade thin; do not change the utility classes
- Validate inputs with `Objects.requireNonNull`

## Build & Run
```bash
cd facade-report-bundle/src
javac com/example/report/*.java
java com.example.report.App
```

## Folder Structure
```
facade-report-bundle/
    ├── src/
    │   └── com/example/report/
    │       ├── App.java
    │       ├── ReportBundleFacade.java     (new facade)
    │       ├── JsonWriter.java             (utility)
    │       ├── Zipper.java                 (utility)
    │       └── AuditLog.java               (utility)
    └── README.md
```

## Results

### File Structure after Implementation
```
facade-report-bundle/
    ├── src/
    │   └── com/example/report/
    │       ├── App.java
    │       ├── ReportBundleFacade.java     (new facade)
    │       ├── JsonWriter.java             (utility)
    │       ├── Zipper.java                 (utility)
    │       └── AuditLog.java               (utility)
    ├── audit.log                           (log file with entries)
    ├── out/                                (output directory)
    │   ├── report.json                     (generated JSON)
    │   └── report.zip                      (zipped bundle)
```

### Output
```shell
uditnayak@Udits-MacBook src % java com.example.report.App    
DONE out/report.zip
uditnayak@Udits-MacBook src % 
```

### JSON Content (`out/report.json`)
```json
{"ok":true,"name":"Quarterly"}
```


### Audit Log Entries
```
2025-09-07T09:55:36.065458Z exported out/report.zip     // Created
2025-09-07T10:02:26.738075Z exported out/report.zip     // Appended
2025-09-07T10:20:08.706828Z exported out/report.zip     // Appended
```

## SOLID Compliance Check

- **Dependency Inversion Principle (DIP)** — Violated.  
  `ReportBundleFacade` depends directly on concrete classes (`JsonWriter`, `Zipper`, `AuditLog`).  
  The facade should instead depend on abstractions (interfaces) and receive implementations via constructor injection.
  This would make it easier to extend (e.g., new writer or logger) and test (e.g., with mocks).  

- **Open/Closed Principle (OCP)** — Partially violated.  
  Adding a new export strategy (e.g., XML instead of JSON) requires modifying the facade.  
  With proper abstractions (`ReportWriter`, `Archiver`, `AuditLogger`), the facade could remain closed to modification but open to extension.