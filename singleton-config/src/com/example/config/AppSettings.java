package com.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Singleton AppSettings
 * ----------------------
 * - private constructor with reflection prevention
 * - double-checked locking for thread-safe lazy initialization
 * - mutable global state (properties can be changed)
 * - reload allowed (loadFromFile can be called multiple times)
 * - serialization support (readResolve method)
 */
public class AppSettings implements Serializable {
    private static final long serialVersionUID = 1L;

    private static volatile AppSettings instance;

    // Configuration properties (kept final to avoid reassignment)
    private final Properties properties = new Properties();

    // Private constructor (prevents external instantiation)
    private AppSettings() {
        // Prevent reflection from creating another instance
        if (instance != null) {
            throw new IllegalStateException("Reflection is not allowed. Use getInstance().");
        }
    }

    // Double-checked locking for lazy initialization + thread-safety
    public static AppSettings getInstance() {
        if (instance == null) {
            synchronized (AppSettings.class) {
                if (instance == null) {
                    instance = new AppSettings();
                }
            }
        }
        return instance;
    }

    // Load configuration file into Properties 
    public synchronized void loadFromFile(Path path) {
        try (InputStream input = Files.newInputStream(path)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + path, e);
        }
    }

    // Get a property value by key
    public String get(String key) {
        return properties.getProperty(key);
    }

    // Ensure deserialization doesn't create a new instance
    protected Object readResolve() {
        return getInstance();
    }
}
