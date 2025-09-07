# Facade (+Adapter optional) — Video Pipeline (Refactoring)

## Narrative (Current Code)
Caller wires 3 subsystems (`Decoder`, `FilterEngine`, `Encoder`) and can't easily use a legacy sharpen filter with an odd API. Too much ordering knowledge leaks to the client.

## Your Task
- Implement `VideoPipelineFacade`:
  ```java
  java.nio.file.Path process(java.nio.file.Path src, java.nio.file.Path out, boolean gray, Double scale, Integer sharpenStrength)
  ```
- Sequence inside: decode → optional grayscale → optional scale → optional sharpen → encode.
- **Adapter (required):** create `SharpenAdapter` that adapts a legacy `LegacySharpen` API to work with real frames.

## Acceptance Criteria
- `App` calls only the facade
- Sharpen step adjustable via `sharpenStrength`
- Legacy API is never used directly by `App`

## Hints
- You can simulate frame handles as strings if needed
- Keep `Frame` as a simple data class

## Build & Run
```bash
cd facade-video/src
javac com/example/video/*.java
java com.example.video.App
```

## Folder Structure
```
facade-video/
    ├── src/
    │   └── com/example/video/
    │       ├── App.java
    │       ├── Decoder.java
    │       ├── FilterEngine.java
    │       ├── Encoder.java
    │       ├── Frame.java
    │       └── LegacySharpen.java         (legacy API)
    └── README.md
```

## What's the Issue?

1. **Facade Problem**  
   - The client (`App`) currently wires up `Decoder`, `FilterEngine`, and `Encoder` directly.  
   - This leaks **ordering knowledge** to the caller (decode → filters → encode).  
   - Any new processing step (e.g., sharpen) requires changes in the `App`, making it fragile.

2. **Adapter Problem**  
   - The project includes a legacy API `LegacySharpen` with an **odd, handle-based interface** (`String` instead of `Frame[]`).  
   - This API cannot be consumed directly in the pipeline without extra conversion logic.  
   - Direct use would **violate clean abstractions** and make the pipeline inconsistent with other filters.


## File Structure After Implementation
```
facade-video/
    ├── src/
    │   └── com/example/video/
    │       ├── App.java
    │       ├── VideoPipelineFacade.java    (new facade)
    │       ├── ISharpen.java               (sharpen interface)
    │       ├── SharpenAdapter.java         (adapter wrapping LegacySharpen)
    │       ├── Decoder.java                (subsystem)
    │       ├── FilterEngine.java           (subsystem)
    │       ├── Encoder.java                (subsystem)
    │       ├── Frame.java                  (data class)
    │       └── LegacySharpen.java          (legacy API)
    ├── in.mp4                              (input video, mock/simulated)
    └── out.mp4                             (processed output video, mock/simulated)
```

## SOLID Compliance Check

- **Dependency Inversion Principle (DIP)** — Violated.  
  `VideoPipelineFacade` depends directly on concrete classes (`Decoder`, `FilterEngine`, `Encoder`, `SharpenAdapter`).  
  It should instead depend on abstractions (e.g., `IDecoder`, `IFilter`, `IEncoder`) and receive implementations via constructor injection.  
  This would make the pipeline easier to extend (e.g., swap in different encoders) and test (e.g., with mocks).

- **Open/Closed Principle (OCP)** — Violated.  
  The facade’s `process` method has explicit `if` blocks for each filter (`grayscale`, `scale`, `sharpen`).  
  Adding a new filter (e.g., blur, brightness, watermark) requires modifying the `process` method.  
  With a common abstraction like `IFilter`, the facade could accept a list of filters and apply them dynamically,  
  allowing new filters to be added without changing the facade’s code.
