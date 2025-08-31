package com.example.profiles;

import java.util.Objects;

public final class Validation {
    private Validation() {}

    public static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static void requireNonBlank(String val, String name) {
        if (isBlank(val)) throw new IllegalArgumentException(name + " must not be blank");
    }

    public static void requireEmail(String email) {
        Objects.requireNonNull(email, "email");
        if (!email.contains("@")) throw new IllegalArgumentException("invalid email");
    }

    /** Central, consistent rule for displayName. */
    public static String validateDisplayName(String displayName) {
        if (displayName == null) return null;
        String normalized = displayName.trim();
        if (normalized.length() > 100) {
            throw new IllegalArgumentException("displayName must be at most 100 characters");
        }
        return normalized;
    }
}
