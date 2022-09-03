package pk.imbilalbutt.bussiness.dto.request;

import lombok.*;
import pk.imbilalbutt.bussiness.dto.BaseDto;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
public class CreateAccountRequestDTO extends BaseDto implements Serializable {

    private String username;
    private String email;
    private String password;
    private String reconfirmedPassword;
    private String role;

    private String firstName;
    private String lastName;
    private String homeAddress;
    private String cellNumber;
    private String cnicNumber;

    public CreateAccountRequestDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReconfirmedPassword() {
        return reconfirmedPassword;
    }

    public void setReconfirmedPassword(String reconfirmedPassword) {
        this.reconfirmedPassword = reconfirmedPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getCnicNumber() {
        return cnicNumber;
    }

    public void setCnicNumber(String cnicNumber) {
        this.cnicNumber = cnicNumber;
    }
}
