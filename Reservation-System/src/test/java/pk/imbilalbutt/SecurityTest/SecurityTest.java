package pk.imbilalbutt.SecurityTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pk.imbilalbutt.bussiness.configuration.PasswordConfiguration;
import pk.imbilalbutt.bussiness.model.User;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SecurityTest {

    PasswordConfiguration passwordConfiguration;

    @Test
    public void testPasswordEncoding() {
        User user = new User("john_doe", "password123", "password123", "email@yahoo.com", "John", "Doe", "user");
        String encoded = passwordConfiguration.passwordEncoder().encode(user.getPassword());
        assertTrue(passwordConfiguration.passwordEncoder().matches("password123", encoded));
    }
}