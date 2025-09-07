# Flyweight — Deduplicate Glyph Styles (Refactoring)

## Narrative (Current Code)
Each `Glyph` stores its own style fields (font,size,bold). Rendering long texts creates thousands of duplicate style objects → memory blow-up.

## Your Task
- Extract immutable `TextStyle` (font,size,bold) as **intrinsic** state.
- Implement `TextStyleFactory` that caches and returns shared `TextStyle` instances by key.
- Modify `Glyph` to hold a `TextStyle` (intrinsic) and a `char` (extrinsic).
- Update `Renderer` to obtain styles via the factory.

## Acceptance Criteria
- Same rendering “cost” as before
- Identical style configs reuse the same `TextStyle` instance (reference equality in a quick check)
- `TextStyle` is immutable

## Hints
- Use a `Map<String, TextStyle>` cache in the factory
- Key suggestion: `"Inter|14|B"`

## Build & Run
```bash
cd flyweight-glyphs/src
javac com/example/render/*.java
java com.example.render.App
```


## Folder Structure
```
flyweight-glyphs/
    ├── src/
    │   └── com/example/render/
    │       ├── App.java
    │       ├── Glyph.java
    │       └── Renderer.java
    └── README.md
```

## Results

### Before
```shell
uditnayak@Udits-MacBook src % java com.example.render.App
Cost=536000
uditnayak@Udits-MacBook src % 
```

### After
```shell
uditnayak@Udits-MacBook src % java com.example.render.App
Cost=536000
uditnayak@Udits-MacBook src % 
```

## Glyph  

A **glyph** is the visual representation of a character.  
For example, the character `'A'` can have multiple glyphs depending on the font, size, and style (bold/italic).  

Think of it this way:  
- **Character** = abstract symbol (`'A'`, `'B'`, `'C'`, …)  
- **Glyph** = how that symbol actually looks when rendered on screen or paper  

### Example  

| Character | Font & Style      | Glyph (visual form) |
|-----------|------------------|----------------------|
| `'A'`     | Inter, 14, normal | A |
| `'A'`     | Inter, 14, bold   | **A** |
| `'A'`     | Times, 16, italic | *A* |

So in our code:  
- **Extrinsic state** → the actual character (`'A'`, `'B'`, etc.)  
- **Intrinsic state** → the style (`font`, `size`, `bold`) which is shared across many glyphs.  

## File Structure after Implementation
```
flyweight-glyphs/
    ├── src/
    │   └── com/example/render/
    │       ├── App.java
    │       ├── Glyph.java
    │       ├── Renderer.java
    │       ├── TextStyle.java               (new flyweight)
    │       └── TextStyleFactory.java        (new factory)
    └── README.md
```

## SOLID Compliance Check

- **Dependency Inversion Principle (DIP)** — Violated.  
  The `Renderer` class depends directly on the concrete `TextStyleFactory`.  
  If we wanted to introduce a different caching strategy (e.g., `WeakHashMap` to allow GC of unused styles, an LRU cache with eviction policy, or even a remote style service), we would have to modify `Renderer`.  
  Instead, `Renderer` should depend on an abstraction like an `ITextStyleFactory` interface, with different factory implementations injected as needed.  
  This would make the system more flexible, testable, and truly open to extension without modification.

## File Structure after SOLID Refactor
```
flyweight-glyphs/
    ├── src/
    │   └── com/example/render/
    │       ├── App.java
    │       ├── Glyph.java
    │       ├── Renderer.java
    │       ├── TextStyle.java               (new flyweight)
    │       ├── ITextStyleFactory.java       (new abstraction)
    │       └── TextStyleFactory.java        (implements ITextStyleFactory)
    └── README.md
```
