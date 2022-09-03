package pk.imbilalbutt.bussiness.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserDto extends BaseDto implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String homeAddress;
    private String cellNumber;
    private String cnicNumber;
    private String roles;
    private String username;
    private String password;
    private String reconfirmedPassword;
    private String email;

//    UserCredentialsDto userCredentials;
//    EmployeeDto employee;
}
