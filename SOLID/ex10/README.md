# Exercise ex10

How to run:
```bash
cd src
javac Demo10.java.java
java Demo10
```

Tasks:
- Identify the SOLID violation(s)
- Refactor to comply with all SOLID principles
- Keep behavior; add a tiny demo/test

## SOLID Violation(s) Check

### SRP
`ReportService` is doing two things:
1. Generating reports
2. Handling logging (by creating and using ConsoleLogger).

> 2 reasons to change → SRP violation.

### OCP
- If we introduce a new logging mechanism (e.g., FileLogger, DatabaseLogger), we must modify ReportService to instantiate that logger.

> Not open for extension → OCP violation.

### LSP
- No subclassing issues here → No LSP violation.

### ISP
- No interfaces exist yet, so no ISP issue → No ISP violation.

### DIP
- `ReportService` depends on a concrete class `ConsoleLogger` instead of an abstraction.
- High-level module (ReportService) should depend on an interface, not a low-level concrete logger.

> DIP violation

---

### Solution

```bash
src/
├── Demo10.java
├── ReportService.java
├── Logger.java
├── ConsoleLogger.java
├── FileLogger.java   // (extension)
```

---

### Behaviour before refactor
```bash
uditnayak@Udits-MacBook src % java Demo10 
[LOG] Generating daily report...
Report contents...
uditnayak@Udits-MacBook src % 
```

### Behaviour after refactor
```bash
uditnayak@Udits-MacBook src % java Demo10 
[LOG] Generating daily report...
Report contents...
uditnayak@Udits-MacBook src % 
```

---

### SOLID Compliance After Refactor
- SRP → ReportService only handles report generation; logging is handled by separate classes.
- OCP → Adding new loggers (e.g., FileLogger, DatabaseLogger) requires no changes to ReportService.
- LSP → All Logger implementations can substitute each other without breaking functionality.
- ISP → Interface Logger is minimal; no class implements unnecessary methods.
- DIP → ReportService depends on Logger abstraction, not a concrete logger.

---