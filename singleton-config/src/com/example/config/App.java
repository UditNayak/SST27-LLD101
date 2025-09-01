package com.example.config;

import java.nio.file.Path;

public class App {
    public static void main(String[] args) throws Exception {
        String path = args.length > 0 ? args[0] : "app.properties";

        // First call loads the properties into one instance
        AppSettings.getInstance().loadFromFile(Path.of(path));

        // But each subsequent call creates a fresh instance (losing the loaded data)
        System.out.println("app.name (1st call) = " + AppSettings.getInstance().get("app.name"));
        System.out.println("app.name (2nd call) = " + AppSettings.getInstance().get("app.name"));
        System.out.println("app.name (3rd call) = " + AppSettings.getInstance().get("app.name"));

        // Show that identity hash codes differ (proving multiple instances exist)
        System.out.println("instance (1st call) = " + System.identityHashCode(AppSettings.getInstance()));
        System.out.println("instance (2nd call) = " + System.identityHashCode(AppSettings.getInstance()));
        System.out.println("instance (3rd call) = " + System.identityHashCode(AppSettings.getInstance()));
    }
}
