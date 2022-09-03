package pk.imbilalbutt.bussiness.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserCredentialsDto extends BaseDto implements Serializable {

    private String email;
    private String username;
    private String password;
    private String reconfirmedPassword;
    private List<GrantedAuthority> authorities;

    private UserDto user;
}
