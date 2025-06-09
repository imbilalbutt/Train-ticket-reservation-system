package pk.imbilalbutt.bussiness.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pk.imbilalbutt.bussiness.dto.UserDto;
import pk.imbilalbutt.bussiness.model.User;

public interface UserService extends BaseService<User, UserDto> {

    UserDto getUserByIdUsingLambdaConverter(UserDto dto);

    UserDto getUserById(UserDto dto);

    UserDto getUserByEmail(UserDto dto);

    UserDto getUserByUsername(UserDto dto);

    UserDto getUserByUsernameAndPassword(UserDto dto);

    UserDto getUserByEmailAndPassword(UserDto dto);

    @Transactional(propagation = Propagation.REQUIRED)
    UserDto saveUserUsingLambdaConverter(UserDto dto) throws RuntimeException;

    @Transactional(propagation = Propagation.REQUIRED)
    UserDto saveOrUpdateUsingSpringJpaWithConverterStrategy(UserDto dto);

    @Transactional(propagation = Propagation.REQUIRED)
    UserDto saveOrUpdateUsingSpringJpaWithCustomUpdateMethod(UserDto dto);

    @Transactional(propagation = Propagation.REQUIRED)
    UserDto saveOrUpdateUsingSpringJpaWithCustomUpdateMethodAndorElseThrow(UserDto dto);

    @Transactional(propagation = Propagation.REQUIRED)
    Boolean delete(UserDto dto);

    @Transactional(propagation = Propagation.REQUIRED)
    UserDto changeStatus(Long id, UserDto DTO);

}
