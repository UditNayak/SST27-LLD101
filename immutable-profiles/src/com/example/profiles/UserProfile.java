package com.example.profiles;

/*
 Immutable Class
 - private final fields
 - Constructor Injection (with deep copy + validation)
 - No setters
 - Return deepcopy in getters (for mutable types)
 - Mark the class as final to prevent subclassing
*/

public final class UserProfile{
    private final String id;        // required
    private final String email;     // required
    private final String phone;
    private final String displayName;
    private final String address;
    private final boolean marketingOptIn;
    private final String twitter;
    private final String github;

    // Private constructor accepts Builder
    private UserProfile(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.phone = builder.phone;
        this.displayName = builder.displayName;
        this.address = builder.address;
        this.marketingOptIn = builder.marketingOptIn;
        this.twitter = builder.twitter;
        this.github = builder.github;
    }

    // Getters (no setters)
    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getDisplayName() { return displayName; }
    public String getAddress() { return address; }
    public boolean isMarketingOptIn() { return marketingOptIn; }
    public String getTwitter() { return twitter; }
    public String getGithub() { return github; }

    /*
     Builder class
     - inner static Builder class with same attributes as of parent (all mutable)
     - private constructor that accept the Builder class
     - All setters in Builder class will return the Builder Object
     - build() method in Builder class will validate and return the parent object
    */
    public static class Builder {
        private String id;
        private String email;
        private String phone;
        private String displayName;
        private String address;
        private boolean marketingOptIn;
        private String twitter;
        private String github;

        // Setters (all return Builder)
        public Builder id(String id){ this.id = id; return this; }
        public Builder email(String email){ this.email = email; return this; }
        public Builder phone(String phone){ this.phone = phone; return this; }
        public Builder displayName(String displayName){ this.displayName = displayName; return this; }
        public Builder address(String address){ this.address = address; return this; }
        public Builder marketingOptIn(boolean marketingOptIn){ this.marketingOptIn = marketingOptIn; return this; }
        public Builder twitter(String twitter){ this.twitter = twitter; return this; }
        public Builder github(String github){ this.github = github; return this; }

        public UserProfile build(){
            Validation.requireNonBlank(this.id, "id");
            Validation.requireEmail(this.email);
            return new UserProfile(this);
        }
    }
}