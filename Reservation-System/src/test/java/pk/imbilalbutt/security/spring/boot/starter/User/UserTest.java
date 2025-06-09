package pk.imbilalbutt.bussiness.test;

import org.junit.jupiter.api.Test;
import pk.imbilalbutt.bussiness.model.User;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserCreation() {
        User user = new User("john_doe", "password123", "john@example.com");
        
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    public void testSetters() {
        User user = new User();
        user.setUsername("test_user");
        user.setPassword("new_password");
        user.setEmail("test@domain.com");
        
        assertAll("User properties",
            () -> assertEquals("test_user", user.getUsername()),
            () -> assertEquals("new_password", user.getPassword()),
            () -> assertEquals("test@domain.com", user.getEmail())
        );
    }

    @Test
    public void testEqualsAndHashCode() {
        User user1 = new User("user1", "pass1", "user1@test.com");
        User user2 = new User("user1", "pass1", "user1@test.com");
        User user3 = new User("user2", "pass2", "user2@test.com");
        
        assertEquals(user1, user2);
        assertNotEquals(user1, user3);
        assertEquals(user1.hashCode(), user2.hashCode());
    }
}
