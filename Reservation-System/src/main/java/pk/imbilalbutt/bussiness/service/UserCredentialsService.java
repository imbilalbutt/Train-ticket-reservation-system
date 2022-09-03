package pk.imbilalbutt.bussiness.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pk.imbilalbutt.bussiness.dto.UserCredentialsDto;
import pk.imbilalbutt.bussiness.model.UserCredentials;

public interface UserCredentialsService extends UserDetailsService, BaseService<UserCredentials, UserCredentialsDto> {

    @Transactional(propagation = Propagation.REQUIRED)
    Boolean createAccount(UserCredentialsDto userCredentialsDto) throws RuntimeException;
}

