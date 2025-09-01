package com.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Enum-based Singleton for App Settings
 * -------------------------------------
 * - Easiest and safest Singleton pattern in Java
 * - Handles serialization and reflection automatically
 * - Instance is created once (when enum is loaded)
 */
public enum AppSettingsEnum {
    INSTANCE; // The single instance

    private final Properties properties = new Properties();

    // Load configuration from file (one-time, can be extended to allow reload if needed)
    public void loadFromFile(Path path) {
        try (InputStream input = Files.newInputStream(path)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get property by key
    public String get(String key) {
        return properties.getProperty(key);
    }
}
