# Exercise ex02

How to run:
```bash
cd src
javac Demo02.java.java
java Demo02
```

Tasks:
- Identify the SOLID violation(s)
- Refactor to comply with all SOLID principles
- Keep behavior; add a tiny demo/test

### Code Summary:
This code mimics a simple media player with fake input data. Here's how it works:
- Takes in byte data
- Converts it to a frame (pretend decoding)
- Displays a playback message
- Caches the last frame played

## SOLID Violation(s) Check

### SRE
`Player` is doing 3 things:
1. Decoding: the File into Frame objects
2. Rendering: UI elements
3. Caching the last Frame

> 3 Reasons to change --> SRP violation

### OCP
- If tomorrow we add a new decoding format (e.g., MP4, MP3), we need to modify the `Player`.
- If tomorrow we change the UI drawing style (e.g., console --> GUI), we need to modify the `Player`.
- If tomorrow we adopt a new caching strategy (e.g., Key-Value Cache, Redis, Store in File/Database), we need to modify the `Player`.

> Not open for extension --> OCP violation

### LSP
- Currently no inheritance in the code.

### ISP
- Currently no interfaces in the code.

### DIP
- `Player` directly depends on concrete implementation:
    ```bash
    Frame f = new Frame(fileBytes); // hard-coded decoding
    ```
- No abstraction for decoding, rendering, or caching.

> `Player` is tightly coupled --> DIP violation

---

### Solution

```bash
Decoder.java         <-- Interface (decode method)
SimpleDecoder.java    <-- implementation of Decoder
Renderer.java         <-- Interface (render method)
ConsoleRenderer.java  <-- Implementation of Renderer
Cache.java            <-- Interface (cache method)
MemoryCache.java      <-- Implementation of Cache
Frame.java            <-- Represents a frame
Player.java           <-- Orchestrator (depends on abstractions)
Demo02.java           <-- main method
```

--- 

### Behavior before refactor
```bash
uditnayak@Udits-MacBook src % java Demo02 
▶ Playing 4 bytes
Cached last frame? true
uditnayak@Udits-MacBook src % 
```

### Behavior after refactor
```bash
uditnayak@Udits-MacBook src % java Demo02 
▶ Playing 4 bytes
Cached last frame? true
uditnayak@Udits-MacBook src % 
```

---

### SOLID Compliance After Refactor
- **SRE**: Each class has one reason to change
- **OCP**: New Decoder/Renderer/Cache implementations can be added without modifying Player
- **LSP**: Any new Decoder/Renderer/Cache implementation can be substituted with the base interfaces without breaking the code
- **ISP**: Small, focused interfaces (Decoder, Renderer, Cache)
- **DIP**: `Player` depend on abstractions (interfaces) rather than concrete implementations

---