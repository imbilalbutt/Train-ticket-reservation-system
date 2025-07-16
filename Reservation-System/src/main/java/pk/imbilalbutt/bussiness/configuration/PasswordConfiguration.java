package pk.imbilalbutt.bussiness.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfiguration {

    // Use BCrypt for secure password encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


// @Configuration
//public class PasswordConfiguration {
//
//    BCryptPasswordEncoder passwordEncoder;
//
//    // Use BCrypt for secure password encoding
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        this.passwordEncoder = new BCryptPasswordEncoder();
//        return this.passwordEncoder;
//    }
//
//
//    public String bcryptEncryptor(String plainText) {
//        return this.passwordEncoder.encode(plainText);
//    }
//
//    public Boolean doPasswordsMatch(String rawPassword,String encodedPassword) {
//        return this.passwordEncoder.matches(rawPassword, encodedPassword);
//    }
//
//}