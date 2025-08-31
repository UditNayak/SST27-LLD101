import com.example.profiles.*;

public class TryIt {
    public static void main(String[] args) {
        ProfileService svc = new ProfileService();
        UserProfile p = svc.createMinimal("u1", "a@b.com");

        System.out.println("Before: " + p.getEmail());

        // The following line would now cause a compile-time error because setters are removed:
        // p.setEmail("evil@example.com"); // <-- IMMUTABILITY ENFORCED

        System.out.println("After (unchanged): " + p.getEmail());
        System.out.println("=> Object is immutable. No setters exist.");
    }
}