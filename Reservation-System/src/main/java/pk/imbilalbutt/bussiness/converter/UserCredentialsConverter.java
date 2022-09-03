package pk.imbilalbutt.bussiness.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pk.imbilalbutt.bussiness.dto.UserCredentialsDto;
import pk.imbilalbutt.bussiness.dto.UserDto;
import pk.imbilalbutt.bussiness.model.User;
import pk.imbilalbutt.bussiness.model.UserCredentials;
import pk.imbilalbutt.bussiness.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component(value = "UserCredentialsConverter")
public class UserCredentialsConverter extends BaseConverter<UserCredentials, UserCredentialsDto> {

    @Autowired
    @Qualifier(value = "UserRepository")
    UserRepository userRepository;

    @Autowired
    @Qualifier(value = "UserConverter")
    UserConverter userConverter;

    /**
     * Convert To Model
     *
     * @param dto <UserCredentialsDto>
     * @return entity <UserCredentials>
     */

    public UserCredentials convertToModel(UserCredentialsDto dto) {
        return this.convertToModelUsingLambda.apply(dto);
    }

    public Function<UserCredentialsDto, UserCredentials> convertToModelUsingLambda = (dto) -> {
        UserCredentials entity = new UserCredentials();
        if (!ObjectUtils.isEmpty(dto)) {
            entity.setUsername(dto.getUsername());
            entity.setEmail(dto.getEmail());
            entity.setPassword(dto.getPassword());
            entity.setReconfirmedPassword(dto.getReconfirmedPassword());
            entity.setAuthorities(dto.getAuthorities());

            if (!ObjectUtils.isEmpty(dto.getUser()) && !ObjectUtils.isEmpty(dto.getUser().getId())) {
                User user = userRepository.getById(dto.getUser().getId());
                entity.setUser(user);
            }

            super.convertToModel(entity, dto);

        }
        return entity;
    };

    public List<UserCredentials> convertToModelList(final Collection<UserCredentialsDto> dtos) {
        return dtos.stream()
                .map(convertToModelUsingLambda)
                .collect(Collectors.toList());
    }

    /**
     * Convert To DTO
     *
     * @param entity <UserCredentials>
     * @return dto <UserCredentialsDto>
     */

    public UserCredentialsDto convertToDto(UserCredentials entity) {
        return this.convertToDtoUsingLambda.apply(entity);
    }

    public Function<UserCredentials, UserCredentialsDto> convertToDtoUsingLambda = (entity) -> {
        UserCredentialsDto dto = new UserCredentialsDto();
        if (!ObjectUtils.isEmpty(entity)) {
            dto.setUsername(entity.getUsername());
            dto.setEmail(entity.getEmail());
            dto.setPassword(entity.getPassword());
            dto.setReconfirmedPassword(entity.getReconfirmedPassword());
            dto.setAuthorities((List<GrantedAuthority>) entity.getAuthorities());

            if (!ObjectUtils.isEmpty(entity.getUser()) && !ObjectUtils.isEmpty(entity.getUser().getId())) {
                dto.setUser(userConverter.convertToDto(entity.getUser()));
            }

            super.convertToDTO(entity, dto);
        }
        return dto;
    };

    public List<UserCredentialsDto> convertToDtoList(final Collection<UserCredentials> modelList) {
        return modelList.stream()
                .map(convertToDtoUsingLambda)
                .collect(Collectors.toList());
    }

    /**
     * Convert from User to UserCredentials and UserCredentialsDto
     */

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

    public UserCredentialsDto convertFromUserToUserCredentialsDto(User user) {
        UserCredentialsDto dto = new UserCredentialsDto();
        if (!ObjectUtils.isEmpty(user) && !ObjectUtils.isEmpty(user.getId())) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());
            dto.setPassword(user.getPassword());
            dto.setReconfirmedPassword(user.getReconfirmedPassword());

            // TODO: set other attributes i.e: activateStatus etc
            dto.setCreatedBy(user.getCreatedBy() == null ? authentication.getName() : user.getCreatedBy());
            dto.setCreatedDate(user.getCreatedDate() == null ? LocalDateTime.now() : user.getCreatedDate());
            dto.setStatus(user.getStatus() == null || user.getStatus());
            dto.setModifiedBy(user.getModifiedBy() == null ? authentication.getName() : user.getModifiedBy());
            dto.setModifiedDate(user.getModifiedDate() == null ? LocalDateTime.now() : user.getModifiedDate());
            dto.setIsNonlocked(user.getIsNonlocked() == null || user.getIsNonlocked());

            dto.setAuthorities(Arrays.stream(user.getRoles().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList()));


        }
        return dto;
    }

    /**
     * Convert from UserDto to UserCredentials and UserCredentialsDto
     */

    public UserCredentials convertFromUserDtoToUserCredentials(UserDto userDto) {
        UserCredentials entity = new UserCredentials();

        if (!ObjectUtils.isEmpty(userDto) && !ObjectUtils.isEmpty(userDto.getId())) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            entity.setUsername(userDto.getUsername());
            entity.setEmail(userDto.getEmail());
            entity.setPassword(userDto.getPassword());
            entity.setReconfirmedPassword(userDto.getReconfirmedPassword());

            // TODO: set other attributes i.e: activateStatus etc
            entity.setCreatedBy(userDto.getCreatedBy() == null ? authentication.getName() : userDto.getCreatedBy());
            entity.setCreatedDate(userDto.getCreatedDate() == null ? LocalDateTime.now() : userDto.getCreatedDate());
            entity.setStatus(userDto.getStatus() == null || userDto.getStatus());
            entity.setModifiedBy(userDto.getModifiedBy() == null ? authentication.getName() : userDto.getModifiedBy());
            entity.setModifiedDate(userDto.getModifiedDate() == null ? LocalDateTime.now() : userDto.getModifiedDate());
            entity.setIsNonlocked(userDto.getIsNonlocked() == null || userDto.getIsNonlocked());

            entity.setAuthorities(Arrays.stream(userDto.getRoles().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList()));

        }
        return entity;
    }

    public UserCredentialsDto convertFromUserDtoToUserCredentialsDto(UserDto userDto) {
        UserCredentialsDto dto = new UserCredentialsDto();
        if (!ObjectUtils.isEmpty(userDto) && !ObjectUtils.isEmpty(userDto.getId())) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            dto.setUsername(userDto.getUsername());
            dto.setEmail(userDto.getEmail());
            dto.setPassword(userDto.getPassword());
            dto.setReconfirmedPassword(userDto.getReconfirmedPassword());

            // TODO: set other attributes i.e: activateStatus etc
            dto.setCreatedBy(userDto.getCreatedBy() == null ? authentication.getName() : userDto.getCreatedBy());
            dto.setCreatedDate(userDto.getCreatedDate() == null ? LocalDateTime.now() : userDto.getCreatedDate());
            dto.setStatus(userDto.getStatus() == null || userDto.getStatus());
            dto.setModifiedBy(userDto.getModifiedBy() == null ? authentication.getName() : userDto.getModifiedBy());
            dto.setModifiedDate(userDto.getModifiedDate() == null ? LocalDateTime.now() : userDto.getModifiedDate());
            dto.setIsNonlocked(userDto.getIsNonlocked() == null || userDto.getIsNonlocked());

            dto.setAuthorities(Arrays.stream(userDto.getRoles().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList()));

        }
        return dto;
    }
}
