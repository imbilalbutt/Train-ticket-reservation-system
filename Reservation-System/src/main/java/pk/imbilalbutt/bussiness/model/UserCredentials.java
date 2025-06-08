package pk.imbilalbutt.bussiness.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//@Entity
//@Table(name = "USER_CREDENTIALS", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
//@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCredentials extends BaseEntity implements UserDetails {

    private String email;
    private String username;
    private String password;
    private String reconfirmedPassword;
    private List<GrantedAuthority> authorities;

    //    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    //    @JoinColumn(name = "FK_USER_ID", referencedColumnName = "USER_ID", nullable = false)
    private User user;

    public UserCredentials() {
    }

    public UserCredentials(User user) {
        this.user = user;
    }

//    public UserCredentials(User user) {
//        this.username = user.getUsername();
//        this.password = user.getPassword();
//        this.reconfirmedPassword = user.getReconfirmedPassword();
//        this.status = user.getStatus();
//        this.authorities = Arrays.stream(user.getRoles().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getStatus();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getIsNonlocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getStatus();
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

}
