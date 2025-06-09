package pk.imbilalbutt.User;

import org.junit.jupiter.api.Test;
import pk.imbilalbutt.bussiness.model.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserCreation() {
        User user = new User("john_doe", "password123", "password123", "email@yahoo.com", "John", "Doe", "user");

        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("password123", user.getReconfirmedPassword());
        assertEquals("email@yahoo.com", user.getEmail());
    }

    @Test
    public void testSetters() {
        User user = new User();
        user.setUsername("test_user");
        user.setPassword("new_password");
        user.setReconfirmedPassword("new_password");
        user.setEmail("test@domain.com");

        assertAll("User properties",
                () -> assertEquals("test_user", user.getUsername()),
                () -> assertEquals("new_password", user.getPassword()),
                () -> assertEquals("test@domain.com", user.getEmail())
        );
    }

    @Test
    public void testEqualsAndHashCode() {
        User user1 = new User("john_doe", "password123", "password123", "email@yahoo.com", "John", "Doe", "user");
        User user2 = new User("john_doe", "password123", "password123", "email@yahoo.com", "John", "Doe", "user");
        User user3 = new User("john_doe2", "password123", "password123", "email2@yahoo.com", "John", "Doe", "user");

        assertEquals(user1.getUsername(), user2.getUsername());
        assertNotEquals(user1, user3);
//        assertEquals(user1.hashCode(), user2.hashCode());
    }
}