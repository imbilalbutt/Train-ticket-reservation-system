package pk.imbilalbutt.bussiness.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import pk.imbilalbutt.bussiness.converter.UserCredentialsConverter;
import pk.imbilalbutt.bussiness.model.User;
import pk.imbilalbutt.bussiness.repository.UserRepository;
import pk.imbilalbutt.bussiness.service.UserCredentialsService;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service(value = "UserCredentialsService")
public class UserCredentialsServiceImpl implements UserCredentialsService {

    private static final Logger logger = Logger.getLogger(UserCredentialsServiceImpl.class.getName());

    private final UserRepository userRepository;


    @Autowired
    @Qualifier("UserCredentialsConverter")
    UserCredentialsConverter userCredentialsConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserCredentialsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.log(Level.INFO, "UserCredentialsService - loadUserByUsername() - username : {0} ", username);

        Optional<User> user = userRepository.findByUsername(username);

        if (ObjectUtils.isEmpty(user) && !user.isPresent()) {

            logger.log(Level.SEVERE, "UserCredentialsService - loadUserByUsername() - UserCredentials not found by username : {0} ", username);

            throw new UsernameNotFoundException("Failed to find any username with : " + username);
        }

        // return user credential model
        return user.map(value -> userCredentialsConverter.convertFromUserToUserCredentials(value)).orElse(null);
    }


    @Override
    public UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException {

        logger.log(Level.INFO, "UserCredentialsService - loadUserByUsername() - username : {0} ", username);

        Optional<User> user = userRepository.findByUsernameAndPassword(username, passwordEncoder.encode(password));

        if (ObjectUtils.isEmpty(user) && !user.isPresent()) {

            logger.log(Level.SEVERE, "UserCredentialsService - loadUserByUsername() - UserCredentials not found by username : {0} ", username);

            throw new UsernameNotFoundException("Failed to find any username with : " + username);
        }

        // return user credential model
        return user.map(value -> userCredentialsConverter.convertFromUserToUserCredentials(value)).orElse(null);
    }
}
