package pk.imbilalbutt.bussiness.converter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pk.imbilalbutt.bussiness.dto.UserDto;
import pk.imbilalbutt.bussiness.model.User;
import pk.imbilalbutt.bussiness.model.UserCredentials;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component(value = "UserConverter")
public class UserConverter extends BaseConverter<User, UserDto> {

    private static final Logger logger = Logger.getLogger(UserConverter.class.getName());

    /**
     * Model converters
    */
    public User convertToModel(UserDto userDto){

        logger.log(Level.INFO, "UserConverter - convertToModel() called in." + userDto);

        User user = new User();

        if(!ObjectUtils.isEmpty(userDto)){
            user.setId(userDto.getId() != null ? userDto.getId() : null);
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setHomeAddress(userDto.getHomeAddress());

            user.setCellNumber(userDto.getCellNumber());
            user.setCnicNumber(userDto.getCnicNumber());

            super.convertToModel(user, userDto);
        }
        return user;
    }

    public Function<UserDto, User> convertToModelUsingLambda = (dto) -> {

        logger.log(Level.INFO, "UserConverter - convertToModelUsingLambda() called in : " + dto);

        User entity = new User();
        if(!ObjectUtils.isEmpty(dto)) {
            entity.setId(dto.getId() != null ? dto.getId() : null);
            entity.setFirstName(dto.getFirstName());
            entity.setLastName(dto.getLastName());
            entity.setHomeAddress(dto.getHomeAddress());

            entity.setCellNumber(dto.getCellNumber());
            entity.setCnicNumber(dto.getCnicNumber());

            super.convertToModel(entity, dto);
        }
        return entity;
    };

    public List<User> convertToModelList(final Collection<UserDto> dtos) {

        logger.log(Level.INFO, "UserConverter - convertToModelList() called in dtos.size() : {0}", dtos.size());

        return dtos.stream()
                .map(convertToModelUsingLambda)
                .collect(Collectors.toList());
    }

    /**
     * DTO converters
     */

    public UserDto convertToDto(User user){

        logger.log(Level.INFO, "UserConverter - convertToDto() called in : " + user);

        UserDto userDto = new UserDto();

        if(!ObjectUtils.isEmpty(user) && !ObjectUtils.isEmpty(user.getId())){
            userDto.setId(user.getId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setHomeAddress(user.getHomeAddress());

            userDto.setCellNumber(user.getCellNumber());
            userDto.setCnicNumber(user.getCnicNumber());

            super.convertToDTO(user, userDto);
        }
        return userDto;
    }

    public Function<User, UserDto> convertToDtoUsingLambda = (entity) -> {

        logger.log(Level.INFO, "UserConverter - convertToDto() called in : " + entity);

        UserDto dto = new UserDto();
        if (!ObjectUtils.isEmpty(entity) && !org.springframework.util.ObjectUtils.isEmpty(entity.getId())) {
            dto.setId(entity.getId());
            dto.setFirstName(entity.getFirstName());
            dto.setLastName(entity.getLastName());
            dto.setHomeAddress(entity.getHomeAddress());

            dto.setCellNumber(entity.getCellNumber());
            dto.setCnicNumber(entity.getCnicNumber());

            super.convertToDTO(entity, dto);
        }
        return dto;
    };

    public List<UserDto> convertToDtoList(final Collection<User> modelList) {
        logger.log(Level.INFO, "UserConverter - convertToModelList() called in modelList.size() : {0}", modelList.size());
        return modelList.stream()
                .map(convertToDtoUsingLambda)
                .collect(Collectors.toList());
    }

    public UserCredentials convertFromUserToUserCredentials(User user) {
        UserCredentials entity = new UserCredentials();

//       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!ObjectUtils.isEmpty(user) && !ObjectUtils.isEmpty(user.getId())) {
            entity.setUsername(user.getUsername());
            entity.setEmail(user.getEmail());
            entity.setPassword(user.getPassword());
            entity.setReconfirmedPassword(user.getReconfirmedPassword());

            // TODO: set other attributes i.e: activateStatus etc
            entity.setCreatedBy(user.getCreatedBy() == null ? authentication.getName() : user.getCreatedBy());
            entity.setCreatedDate(user.getCreatedDate() == null ? LocalDateTime.now() : user.getCreatedDate());
            entity.setStatus(user.getStatus() == null || user.getStatus());
            entity.setModifiedBy(user.getModifiedBy() == null ? authentication.getName() : user.getModifiedBy());
            entity.setModifiedDate(user.getModifiedDate() == null ? LocalDateTime.now() : user.getModifiedDate());
            entity.setIsNonlocked(user.getIsNonlocked() == null || user.getIsNonlocked());

            entity.setAuthorities(Arrays.stream(user.getRoles().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList()));

        }
        return entity;
    }
}
