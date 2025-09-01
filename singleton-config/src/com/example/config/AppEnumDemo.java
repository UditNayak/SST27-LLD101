package com.example.config;

import java.nio.file.Path;

/**
 * Demo class for testing Enum-based Singleton
 */
public class AppEnumDemo {
    public static void main(String[] args) throws Exception {
        String path = args.length > 0 ? args[0] : "app.properties";

        // Load config once
        AppSettingsEnum.INSTANCE.loadFromFile(Path.of(path));

        // Fetch values
        System.out.println("app.name = " + AppSettingsEnum.INSTANCE.get("app.name"));
        System.out.println("app.version = " + AppSettingsEnum.INSTANCE.get("app.version"));

        // Enum guarantees only one instance
        System.out.println("instance (1st call) = " + System.identityHashCode(AppSettingsEnum.INSTANCE));
        System.out.println("instance (2nd call) = " + System.identityHashCode(AppSettingsEnum.INSTANCE));
        System.out.println("instance (3rd call) = " + System.identityHashCode(AppSettingsEnum.INSTANCE));
    }
}