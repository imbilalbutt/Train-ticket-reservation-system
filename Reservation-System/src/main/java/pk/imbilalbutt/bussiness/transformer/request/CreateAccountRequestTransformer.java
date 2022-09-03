package pk.imbilalbutt.bussiness.transformer.request;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pk.imbilalbutt.bussiness.converter.BaseConverter;
import pk.imbilalbutt.bussiness.dto.UserCredentialsDto;
import pk.imbilalbutt.bussiness.dto.UserDto;
import pk.imbilalbutt.bussiness.dto.request.CreateAccountRequestDTO;
import pk.imbilalbutt.bussiness.model.User;
import pk.imbilalbutt.bussiness.model.UserCredentials;

import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 * This class converts the incoming request of type CreateAccountRequestDTO
 * into UserCredentials, UserCredentialsDto, User and UserDto.
 * After converting respective services are called() using resultant objects.
 */

@Component(value = "CreateAccountRequestTransformer")
public class CreateAccountRequestTransformer extends BaseConverter {

    final private Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    private static final Logger logger = Logger.getLogger(CreateAccountRequestTransformer.class.getName());

    public UserCredentials transformToUserCredentialsModel(CreateAccountRequestDTO dto) {

        UserCredentials userCredentials = new UserCredentials();

        if (!ObjectUtils.isEmpty(dto)) {

            userCredentials.setUsername(dto.getUsername());
            userCredentials.setEmail(dto.getEmail());
            userCredentials.setPassword(dto.getPassword());
            userCredentials.setReconfirmedPassword(dto.getPassword());

            super.convertToModel(userCredentials, dto);

//            userCredentials.setCreatedBy(dto.getCreatedBy());
//            userCredentials.setCreatedDate(dto.getCreatedDate());
//            userCredentials.setStatus(dto.getStatus());
//            userCredentials.setModifiedBy(dto.getModifiedBy());
//            userCredentials.setModifiedDate(dto.getModifiedDate());
//            userCredentials.setLocked(dto.getLocked());
        }
        return userCredentials;
    }

    ;

    public UserCredentialsDto transformToUserCredentialsDto(CreateAccountRequestDTO dto) {

        UserCredentialsDto userCredentialsDto = new UserCredentialsDto();

        if (!ObjectUtils.isEmpty(dto)) {

            userCredentialsDto.setUsername(dto.getUsername());
            userCredentialsDto.setEmail(dto.getEmail());
            userCredentialsDto.setPassword(dto.getPassword());
            userCredentialsDto.setReconfirmedPassword(dto.getPassword());

            userCredentialsDto.setCreatedBy(dto.getCreatedBy() == null ? authentication.getName() : dto.getCreatedBy());
            userCredentialsDto.setCreatedDate(dto.getCreatedDate() == null ? LocalDateTime.now() : dto.getCreatedDate());
            userCredentialsDto.setStatus(dto.getStatus() == null || dto.getStatus());
            userCredentialsDto.setModifiedBy(dto.getModifiedBy() == null ? authentication.getName() : dto.getModifiedBy());
            userCredentialsDto.setModifiedDate(dto.getModifiedDate() == null ? LocalDateTime.now() : dto.getModifiedDate());
            userCredentialsDto.setIsNonlocked(dto.getIsNonlocked() == null || dto.getIsNonlocked());

        }
        return userCredentialsDto;
    }

    public User transformToUserModel(CreateAccountRequestDTO dto) {

        User user = new User();

        if (!ObjectUtils.isEmpty(dto)) {

            user.setId(null);
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setHomeAddress(dto.getHomeAddress());
            user.setCellNumber(dto.getCellNumber());
            user.setCnicNumber(dto.getCnicNumber());

            super.convertToModel(user, dto);

//            user.setCreatedBy(dto.getCreatedBy());
//            user.setCreatedDate(dto.getCreatedDate());
//            user.setStatus(dto.getStatus());
//            user.setModifiedBy(dto.getModifiedBy());
//            user.setModifiedDate(dto.getModifiedDate());
//            user.setLocked(dto.getLocked());
        }
        return user;
    }

    ;

    public UserDto transformToUserDto(CreateAccountRequestDTO dto) {

        UserDto userDto = new UserDto();

        if (!ObjectUtils.isEmpty(dto)) {

            userDto.setFirstName(dto.getFirstName());
            userDto.setLastName(dto.getLastName());
            userDto.setHomeAddress(dto.getHomeAddress());
            userDto.setCellNumber(dto.getCellNumber());
            userDto.setCnicNumber(dto.getCnicNumber());

            userDto.setCreatedBy(dto.getCreatedBy());
            userDto.setCreatedDate(dto.getCreatedDate() == null ? LocalDateTime.now() : dto.getCreatedDate());
            userDto.setStatus(dto.getStatus());
            userDto.setModifiedBy(dto.getModifiedBy());
            userDto.setModifiedDate(dto.getModifiedDate() == null ? LocalDateTime.now() : dto.getModifiedDate());
            userDto.setIsNonlocked(dto.getIsNonlocked());
        }
        return userDto;
    }

}
