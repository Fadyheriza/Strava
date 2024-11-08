package strava.service;

import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class MockAuthService {

    private final Set<String> googleEmails = Set.of("user1@gmail.com", "user2@gmail.com");
    private final Set<String> facebookEmails = Set.of("user1@facebook.com", "user2@facebook.com");

    public boolean authenticate(String email, String provider) {
        if (provider.equalsIgnoreCase("Google")) {
            return googleEmails.contains(email);
        } else if (provider.equalsIgnoreCase("Facebook")) {
            return facebookEmails.contains(email);
        }
        return false; // Unknown provider
    }
}
