package com.example.profiles;

/*
 * Assembles profiles with consistent validation via Builder.
 */

public class ProfileService {

    // Creates minimal immutable profile
    public UserProfile createMinimal(String id, String email) {
        return new UserProfile.Builder()
                .id(id)
                .email(email)
                .build();
    }

    // Creates a profile with displayName (instead of updating later)
    public UserProfile createWithDisplayName(String id, String email, String displayName) {
        displayName = Validation.validateDisplayName(displayName);
        return new UserProfile.Builder()
                .id(id)
                .email(email)
                .displayName(displayName)
                .build();
    }

    // Note: no mutating updateDisplayName() — immutability preserved.
}
