package pk.imbilalbutt.bussiness.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pk.imbilalbutt.bussiness.dto.UserCredentialsDto;
import pk.imbilalbutt.bussiness.model.User;

public interface UserCredentialsService extends UserDetailsService  {

    UserDetails loadUserByUsername(String username) throws RuntimeException;

    UserDetails loadUserByUsername(String username, String password) throws RuntimeException;
}

