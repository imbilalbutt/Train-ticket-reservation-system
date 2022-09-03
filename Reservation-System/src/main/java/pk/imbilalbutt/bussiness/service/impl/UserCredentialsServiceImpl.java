package pk.imbilalbutt.bussiness.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import pk.imbilalbutt.bussiness.converter.UserCredentialsConverter;
import pk.imbilalbutt.bussiness.dto.UserCredentialsDto;
import pk.imbilalbutt.bussiness.model.User;
import pk.imbilalbutt.bussiness.repository.UserRepository;
import pk.imbilalbutt.bussiness.service.UserCredentialsService;

import java.util.List;
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

    @Autowired
    public UserCredentialsServiceImpl(@Qualifier("UserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserCredentialsDto getById(Long id) {

//        if (!ObjectUtils.isEmpty(id)) {
//            Optional<UserCredentials> userCredentials = UserRepository.findById(id);
//            if (userCredentials.isPresent()) {
//                return userCredentialsConverter.convertToDtoUsingLambda.apply(userCredentials.get());
//            }
//        }
        return null;
    }

    @Override
    public List<UserCredentialsDto> getAll() {
//        List<UserCredentials> userCredentials = new ArrayList<>();
//        try {
//            userCredentials = userRepository.findAll();
//        } finally {
//            return !ObjectUtils.isEmpty(userCredentials) ?
//                    userCredentialsConverter.convertToDtoList(userCredentials) : new ArrayList<>();
//        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {

//        if (!ObjectUtils.isEmpty(id)) {
//
//            UserCredentials userCredentials = UserRepository.getById(id);
//
//            UserRepository.delete(userCredentials);
//
//            return Boolean.TRUE;
//        }

        return Boolean.FALSE;
    }

    @Override
    public UserCredentialsDto saveOrUpdate(UserCredentialsDto dto) {

//        logger.log(Level.INFO, "UserCredentialsServiceImpl - saveOrUpdate() called in." + dto);
//
//        UserCredentials userCredentials = new UserCredentials();
//
//        if (!ObjectUtils.isEmpty(dto)) {
//
//            String encodePassword = null;
//
//            if (!ObjectUtils.isEmpty(dto.getId())) {
//
//                userCredentials = UserRepository.findById(dto.getId()).get();
//
//                if (!ObjectUtils.isEmpty(userCredentials) && !ObjectUtils.isEmpty(userCredentials.getUser().getId())) {
//                    userCredentials = userCredentialsConverter.convertToModelUsingLambda.apply(dto);
//
//                    // Convert raw string code into encoding.
//                    encodePassword = passwordEncoder.encode(dto.getPassword());
//                    userCredentials.setPassword(encodePassword);
//                    userCredentials.setReconfirmedPassword(encodePassword);
//
//                    userCredentials = UserRepository.save(userCredentials);
//
//                    logger.log(Level.INFO, "UserCredentialsServiceImpl - saveOrUpdate() - update(): " + userCredentials.getUser().getId());
//                }
//            } else {
//                userCredentials = userCredentialsConverter.convertToModelUsingLambda.apply(dto);
//
//                // Convert raw string code into encoding.
//                encodePassword = passwordEncoder.encode(dto.getPassword());
//                userCredentials.setPassword(encodePassword);
//                userCredentials.setReconfirmedPassword(encodePassword);
//
//                userCredentials = UserRepository.save(userCredentials);
//
//                if (!ObjectUtils.isEmpty(userCredentials) && !ObjectUtils.isEmpty(userCredentials.getUser().getId())) {
//                    dto.setId(userCredentials.getUser().getId());
//                }
//                logger.log(Level.INFO, "UserCredentialsServiceImpl - saveOrUpdate() - save(): " + userCredentials.getUser().getId());
//            }
//        }
//        return dto;
        return null;
    }

    @Override
    public Boolean createAccount(UserCredentialsDto userCredentialsDto) throws RuntimeException {

//        TODO : set all of these methods

        try {
            this.saveOrUpdate(userCredentialsDto);
            return Boolean.TRUE;
        } catch (RuntimeException re) {
            logger.log(Level.INFO, "UserServiceImpl - createAccount() - userCredentialsDto.getId() : " + userCredentialsDto.getUsername());
            throw new RuntimeException("UserServiceImpl exception : {0} ", re);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.log(Level.INFO, "UserCredentialsService - loadUserByUsername() - username : {0} ", username);

        Optional<User> user = userRepository.findByUsername(username);

        if (ObjectUtils.isEmpty(user) && !user.isPresent()) {

            logger.log(Level.SEVERE, "UserCredentialsService - loadUserByUsername() - UserCredentials not found by username : {0} ", username);

            throw new UsernameNotFoundException("Failed to find any username with : " + username);
        }

        return userCredentialsConverter.convertFromUserToUserCredentials(user.get());

        /** Copied from : JavaBrains
         *   @https://github.com/koushikkothagal/spring-security-jpa/blob/master/src/main/java/io/javabrains/springsecurityjpa/MyUserDetailsService.java
         */
//        Optional<User> user = userRepository.findByUsername(username);
//
//        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
//
//        return user.map(UserCredentials::new).get();

    }
}
