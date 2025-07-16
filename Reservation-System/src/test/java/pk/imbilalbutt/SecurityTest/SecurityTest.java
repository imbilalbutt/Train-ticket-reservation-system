//package pk.imbilalbutt.SecurityTest;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.ContextConfiguration;
//import pk.imbilalbutt.bussiness.configuration.PasswordConfiguration;
//import pk.imbilalbutt.bussiness.model.User;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
////@ExtendWith(MockitoExtension.class)
////public class SecurityTest {
////
////    @Mock
////    PasswordConfiguration passwordConfiguration;
////
////    @Test
////    public void testPasswordEncoding() {
////        User user = new User("john_doe", "password123", "password123", "email@yahoo.com", "John", "Doe", "user");
////        String encoded = passwordConfiguration.passwordEncoder().encode(user.getPassword());
////        assertTrue(passwordConfiguration.passwordEncoder().matches("password123", encoded));
////    }
////}
//
//@ContextConfiguration
//@SpringBootTest
//public class SecurityTest {
//
//    @MockBean
//    private PasswordEncoder passwordEncoder; // Mock the encoder directly
//
//    @MockBean
//    private PasswordConfiguration passwordConfiguration;
//
//    @Test
//    public void testPasswordEncoding() {
//        User user = new User("john_doe", "password123", "password123",
//                "email@yahoo.com", "John", "Doe", "user");
//        String encoded = passwordConfiguration.passwordEncoder().encode(user.getPassword());
//        assertTrue(passwordConfiguration.passwordEncoder().matches("password123", encoded));
//    }
//}