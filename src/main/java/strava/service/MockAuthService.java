package strava.service;

import org.springframework.stereotype.Service;

@Service
public class MockAuthService {

    public boolean authenticate(String email, String provider) {
        if ("Google".equalsIgnoreCase(provider) && email.endsWith("@gmail.com")) {
            return true;  // Accepts any email ending with @gmail.com for Google
        } else if ("Facebook".equalsIgnoreCase(provider) && email.endsWith("@facebook.com")) {
            return true;  // Accepts any email ending with @facebook.com for Facebook
        }
        return false;  // Reject if provider or email domain does not match
    }
}
