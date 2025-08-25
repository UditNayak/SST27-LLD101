# Exercise ex07

How to run:
```bash
cd src
javac Demo07.java.java
java Demo07
```

Tasks:
- Identify the SOLID violation(s)
- Refactor to comply with all SOLID principles
- Keep behavior; add a tiny demo/test

## SOLID Violation(s) Check

### SRP
`Machine` interface forces `BasicPrinter` to provide behavior for:
1. Printing
2. Scanning
3. Faxing

> 3 reasons to change → SRP violation

### OCP
- Adding a new feature like Email will require modification in Machine interface and all implementing classes.

> Not open for extension → OCP violation.

### LSP
- BasicPrinter cannot substitute Machine fully because scan() and fax() throw UnsupportedOperationException.
- Any client expecting a Machine with full functionality will break when given BasicPrinter.

> Incompatible interface → LSP violation.

### ISP
- Machine interface is too broad → forces clients to implement unnecessary methods (scan, fax) even when they don’t need them.

> Fat interface → ISP violation.

### DIP
- TODO

---

### Solution
```bash
src/
 ├── Printer.java
 ├── Scanner.java
 ├── Fax.java
 ├── BasicPrinter.java
 ├── AdvancedPrinter.java
 ├── Demo07.java
```

---

### Behaviour before refactor
```bash
uditnayak@Udits-MacBook src % java Demo07 
Print: Hello
Exception in thread "main" java.lang.UnsupportedOperationException
        at BasicPrinter.scan(BasicPrinter.java:3)
        at Demo07.main(Demo07.java:5)
uditnayak@Udits-MacBook src % 
```

### Behaviour after refactor
```bash
uditnayak@Udits-MacBook src % java Demo07 
Print: Hello
Print: Hi
Scanning to /tmp/out
Faxing to 1234567890
uditnayak@Udits-MacBook src % 
```

--- 

### SOLID Compliance After Refactor

- SRP → Each interface represents a single responsibility.
- OCP → New functionality (e.g., Email) can be added with new interfaces/implementations without modifying existing code.
- LSP → All implementations can be substituted without breaking functionality.
- ISP → Clients only need to be concerned with the methods that are relevant to them.
- DIP → High-level code depends on abstractions like Printer, Scanner, Fax instead of a fat interface.

---