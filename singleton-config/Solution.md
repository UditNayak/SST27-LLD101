# Singleton - App Configuration System

### File Structure:

```sh
singleton-config/
├── README.txt
└── src/
    └── com/
        └── example/
            └── config/
                ├── App.java
                ├── AppSettings.java
                └── SettingsLoader.java
```

### What does the current code snippet do?
This code is a simulation of a **real-world app configuration system**.  
When applications start, they often need configuration details such as:
- What’s the app name?
- What’s the database URL?
- What’s the API key?

Instead of hardcoding these values in the code, they are stored in an external config file (like `app.properties` in Java, `config.json` in Node.js, or `.env` in Python).  
The application then **loads these properties once** and exposes them globally so that different parts of the codebase can access them.

The **intention** is that `AppSettings` should act like a **singleton configuration registry**:
- Load configuration values once.
- Provide consistent values across the entire application.
- Prevent multiple copies of the configuration state.

**Benefit of Singleton Pattern**
---
1. **Global Access**: The configuration is accessible from anywhere in the application without passing around instances.
2. **Consistent State**: Since there's only one instance, all parts of the application see the same configuration values.
3. **Lazy Initialization**: The configuration is loaded only when it's needed, potentially improving startup time.
4. **Thread Safety**: A properly implemented Singleton can be made thread-safe, ensuring that configuration is loaded correctly even in multi-threaded environments.
5. **Reduced Memory Footprint**: By ensuring only one instance exists, the Singleton pattern can help reduce memory usage, especially if the configuration data is large.

---

### What is the issue with the current code?

The current implementation of `AppSettings` is **not a true Singleton**. This causes two main problems:

#### 1. Properties Loss
- When `AppSettings.getInstance().loadFromFile(...)` is called, the properties are loaded into one instance of `AppSettings`.
- But when you later call `AppSettings.getInstance().get("app.name")`, a **new instance** is created (with an empty `Properties` object).
- Since this fresh object never loaded the file, it returns `null` for all keys.

    ```shell
    // First call loads the properties into one instance
    AppSettings.getInstance().loadFromFile(Path.of(path));

    // But each subsequent call creates a fresh instance (losing the loaded data)
    System.out.println("app.name (1st call) = " + AppSettings.getInstance().get("app.name"));
    System.out.println("app.name (2nd call) = " + AppSettings.getInstance().get("app.name"));
    System.out.println("app.name (3rd call) = " + AppSettings.getInstance().get("app.name"));
    ```

    ```shell
    app.name (1st call) = null
    app.name (2nd call) = null
    app.name (3rd call) = null
    ```

**Reason**: `getInstance()` always does `return new AppSettings();` instead of reusing the same object.

#### 2. Multiple Instances
- Every call to `AppSettings.getInstance()` returns a different object.
- You can prove this by printing the identity hash codes:
    ```java
    // Show that identity hash codes differ (proving multiple instances exist)
    System.out.println("instance (1st call) = " + System.identityHashCode(AppSettings.getInstance()));
    System.out.println("instance (2nd call) = " + System.identityHashCode(AppSettings.getInstance()));
    System.out.println("instance (3rd call) = " + System.identityHashCode(AppSettings.getInstance()));
    ```

    ```shell
    instance (1st call) = 2133927002
    instance (2nd call) = 325040804
    instance (3rd call) = 1173230247
    ```
- This clearly shows that multiple independent AppSettings objects exist.

**Reason**: The constructor of `AppSettings` is `public` and the `getInstance()` method always creates a new object, violating the Singleton principle.

Because of these issues, the current design fails to guarantee a single global configuration registry. The application cannot reliably share configuration data across its different parts.

### How can we solve the issues?
We explored **two ways to correctly implement Singleton** for our App Configuration System:

1. **Classic Singleton (with double-checked locking)**  
   - Provides lazy initialization.  
   - Thread-safe and prevents multiple instances.  
   - Needs extra care to handle reflection, serialization, and concurrency.  

    ```shell
    uditnayak@Udits-MacBook src % javac com/example/config/*.java
    uditnayak@Udits-MacBook src % java com.example.config.App    
    app.name (1st call) = LLD-singleton-design-pattern
    app.name (2nd call) = LLD-singleton-design-pattern
    app.name (3rd call) = LLD-singleton-design-pattern
    instance (1st call) = 2133927002
    instance (2nd call) = 2133927002
    instance (3rd call) = 2133927002
    ```

2. **Enum-based Singleton**  
   - Simplest and safest approach in Java.  
   - Prevents issues with reflection and serialization automatically.  
   - Thread-safe by default, with less boilerplate code.

    ```shell
    uditnayak@Udits-MacBook src % javac com/example/config/*.java
    uditnayak@Udits-MacBook src % java com.example.config.AppEnumDemo
    app.name = LLD-singleton-design-pattern
    app.version = 1.0.0
    instance (1st call) = 2133927002
    instance (2nd call) = 2133927002
    instance (3rd call) = 2133927002
    uditnayak@Udits-MacBook src % 
    ```

### Why Enum is Better?
- No need for extra code (`volatile`, `synchronized`, `readResolve`).
- JVM guarantees single instance, making it more robust against bugs.
- Less code = fewer chances of mistakes.

### Drawbacks of Enum Singleton
1. **Eager Initialization** – The enum instance is created as soon as the class is loaded, even if it’s never used. 
2. **Limited flexibility** – If you need inheritance, polymorphism, or advanced lifecycle control, enums are restrictive compared to a class-based Singleton. 