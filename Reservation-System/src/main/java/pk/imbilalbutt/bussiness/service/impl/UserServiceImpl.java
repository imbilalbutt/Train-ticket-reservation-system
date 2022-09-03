package pk.imbilalbutt.bussiness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import pk.imbilalbutt.bussiness.converter.UserConverter;
import pk.imbilalbutt.bussiness.dto.UserDto;
import pk.imbilalbutt.bussiness.model.User;
import pk.imbilalbutt.bussiness.repository.UserRepository;
import pk.imbilalbutt.bussiness.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component(value= "UserService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    @Qualifier("UserRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("UserConverter")
    private UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter){
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    /**
     * GET by ID Methods
     * @param id or dto
     * @return <UserDto> dto </UserDto>
     */
    @Override
    public UserDto getById(Long id) {

        logger.log(Level.INFO, "UserServiceImpl - getById() called id : {0}.", id);

        if(!ObjectUtils.isEmpty(id)){

            Optional<User> user = userRepository.findById(id);

            if(user.isPresent()) {

                logger.log(Level.INFO, "UserServiceImpl - getById() Found : {0}.", user.get().getId());

                return userConverter.convertToDtoUsingLambda.apply(user.get());
            }
        }

        logger.log(Level.INFO, "UserServiceImpl - getById() NOT found.");

        return null;
    }

    @Override
    public UserDto getUserByIdUsingLambdaConverter(UserDto dto){
        if(!ObjectUtils.isEmpty(dto) && !ObjectUtils.isEmpty(dto.getId())) {
            Optional<User> user = userRepository.findById(dto.getId());
            if(user.isPresent()){
                final UserDto result = userConverter.convertToDtoUsingLambda.apply(user.get());
                return result;
            }
        }
        return null;
    }

    @Override
    public UserDto getUserById(UserDto dto) {
        if(!ObjectUtils.isEmpty(dto) && !ObjectUtils.isEmpty(dto.getId())){
            Optional<User> user = userRepository.findById(dto.getId());
            if(user.isPresent()){
                return userConverter.convertToDto(user.get());
            }
        }
        return null;
    }

    @Override
    public List<UserDto> getAll() {

        logger.log(Level.INFO, "UserServiceImpl - getAll() - called in.");

        List<User> users = new ArrayList<>();
        try{
            users = userRepository.findAll();

            logger.log(Level.INFO, "UserServiceImpl - getAll() - users.size() : {0}.", users.size());

            return !ObjectUtils.isEmpty(users) ? userConverter.convertToDtoList(users) : new ArrayList<>();

        } catch (NullPointerException npe) {

            logger.log(Level.WARNING, "UserServiceImpl - getAll() - Not Found.", users.size());

            throw new NullPointerException("Not Found Any Single One.");

        }
    }

    /**
     * SAVE Methods
     * @param  dto
     * @return <UserDto> dto </UserDto>
     */

    //
    @Override
    public UserDto saveUserUsingLambdaConverter(UserDto dto) throws RuntimeException {

        logger.log(Level.INFO, "UserServiceImpl - saveUserUsingLambdaConverter() called in." + dto);

        try {
            //Optional<User> user = userRepository.findById(dto.getId());
            final User entity = userConverter.convertToModelUsingLambda.apply(dto);
            userRepository.save(entity);

            logger.log(Level.INFO, "UserServiceImpl - saveUserUsingLambdaConverter() successfull.");

            return dto;
        } catch (RuntimeException re) {

            logger.log(Level.WARNING, "UserServiceImpl - saveUserUsingLambdaConverter() failed : {0} ", re.getMessage());
            return null;
        }

    }

    // > Method 0 : without User being Optional
    // LambdaConverter + Not Optional
    @Override
    public UserDto saveOrUpdate(UserDto dto) throws RuntimeException{

        logger.log(Level.INFO, "UserServiceImpl - saveOrUpdate() called in." + dto);

        User user = new User();
        if (!ObjectUtils.isEmpty(dto)) {

            if (!ObjectUtils.isEmpty(dto.getId())) {

                user = userRepository.findById(dto.getId()).get();

                if (!ObjectUtils.isEmpty(user) && !ObjectUtils.isEmpty(user.getId())) {
                    user = userConverter.convertToModelUsingLambda.apply(dto);
                    user = userRepository.save(user);

                    logger.log(Level.INFO, "UserServiceImpl - saveOrUpdate() - update(): {0} success.", user.getId());
                }
            } else {
                user = userConverter.convertToModelUsingLambda.apply(dto);
                user = userRepository.save(user);

                if (!ObjectUtils.isEmpty(user) && !ObjectUtils.isEmpty(user.getId())) {
                    dto.setId(user.getId());
                }
                logger.log(Level.INFO, "UserServiceImpl - saveOrUpdate() - save(): {0} success." , user.getId());
            }
        }
        return dto;
    }

    // > Method 1 : with user being Optional and Converter Strategy
    @Override
    public UserDto saveOrUpdateUsingSpringJpaWithConverterStrategy(UserDto dto) {

        logger.log(Level.INFO, "UserServiceImpl - saveOrUpdateUsingSpringJpaWithConverterStrategy() called in : " + dto);

        if (!ObjectUtils.isEmpty(dto)) {

            if (!ObjectUtils.isEmpty(dto.getId())) {

                Optional<User> user = userRepository.findById(dto.getId());
                if(user.isPresent() && !ObjectUtils.isEmpty(user.get().getId())) {
                    User bk = userConverter.convertToModelUsingLambda.apply(dto);
                    userRepository.save(bk);

                    logger.log(Level.INFO, "UserServiceImpl - saveOrUpdateUsingSpringJpaWithConverterStrategy() - update(): {0} success.", bk.getId());

                    return dto;
                }
            } else {
                User user = new User();
                user = userConverter.convertToModelUsingLambda.apply(dto);
                user = userRepository.save(user);

                if (!ObjectUtils.isEmpty(user) && !ObjectUtils.isEmpty(user.getId())) {
                    dto.setId(user.getId());
                }
                logger.log(Level.INFO, "UserServiceImpl - saveOrUpdateUsingSpringJpaWithConverterStrategy() - save(): {0} success.", user.getId());
            }
        }
        return dto;
    }

    // > Method 2 : with Custom Update Method
    // Jpa.repository.save + Optional
    @Override
    public UserDto saveOrUpdateUsingSpringJpaWithCustomUpdateMethod(UserDto dto) {

        logger.log(Level.INFO, "UserServiceImpl - saveOrUpdateUsingSpringJpaWithCustomUpdateMethod() called in." + dto);

        if (!ObjectUtils.isEmpty(dto)) {

            if (!ObjectUtils.isEmpty(dto.getId())) {

                Optional<User> user = userRepository.findById(dto.getId());
                if (user.isPresent() && !ObjectUtils.isEmpty(user.get().getId())) {
                    User response = userRepository.save(userConverter.convertToModelUsingLambda.apply(dto));
                    dto.setId(response.getId());

                    logger.log(Level.INFO, "UserServiceImpl - saveOrUpdateUsingSpringJpaWithCustomUpdateMethod() - update(): {0} success.", user.get().getId());

                    return dto;
                }
            }
        } else {
            User user = new User();
            user = userConverter.convertToModelUsingLambda.apply(dto);
            user = userRepository.save(user);

            if (!ObjectUtils.isEmpty(user) && !ObjectUtils.isEmpty(user.getId())) {
                dto.setId(user.getId());
            }
            logger.log(Level.INFO, "UserServiceImpl - saveOrUpdateUsingSpringJpaWithCustomUpdateMethod() - save(): {0} success.", user.getId());
        }
        return dto;
    }

    // > Method 3 : with Custom Update Method and orElseThrow()
    @Override
    public UserDto saveOrUpdateUsingSpringJpaWithCustomUpdateMethodAndorElseThrow(UserDto dto) {

        logger.log(Level.INFO, "UserServiceImpl - saveOrUpdateUsingSpringJpaWithCustomUpdateMethodAndorElseThrow() called in." + dto);

        if (!ObjectUtils.isEmpty(dto)) {

            if (!ObjectUtils.isEmpty(dto.getId())) {

                // Method 3 : with Custom Update Method and orElseThrow()
                User user = userRepository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + dto.getId()));
                if (!ObjectUtils.isEmpty(user.getId())) {
                    User response = userRepository.save(userConverter.convertToModelUsingLambda.apply(dto));
                    dto.setId(response.getId());

                    logger.log(Level.INFO, "UserServiceImpl - saveOrUpdateUsingSpringJpaWithCustomUpdateMethodAndorElseThrow() - update(): {0} success. ", user.getId());

                    return dto;
                }
            } else {
                User user = new User();
                user = userConverter.convertToModelUsingLambda.apply(dto);
                user = userRepository.save(user);

                if (!ObjectUtils.isEmpty(user) && !ObjectUtils.isEmpty(user.getId())) {
                    dto.setId(user.getId());
                }
                logger.log(Level.INFO, "UserServiceImpl - saveOrUpdateUsingSpringJpaWithCustomUpdateMethodAndorElseThrow() - save(): {0} success.", user.getId());
            }
        }
        return dto;
    }

    /**
     * DELETE Methods
     * @param  id
     * @return Boolean
     */

    @Transactional
    @Override
    public Boolean delete(Long id) {

        logger.log(Level.INFO, "UserServiceImpl - delete() id : {0}.", id);

        try {

            userRepository.deleteById(id);

            logger.log(Level.INFO, "UserServiceImpl - delete() successfully.");

            return Boolean.TRUE;

        } catch (EmptyResultDataAccessException | EntityNotFoundException e) {

            logger.log(Level.INFO, "UserServiceImpl - delete() - Failed : " + e.getMessage());

            //throw new EntityNotFoundException("Invalid ID. System does not recognise {0} " + id + " ID.");

            return Boolean.FALSE;
        }

    }

    @Override
    public Boolean delete(UserDto dto) throws EntityNotFoundException {

//        if(!ObjectUtils.isEmpty(dto) && !ObjectUtils.isEmpty(dto.getId())){
        try{
            // Method 1
            User user = userConverter.convertToModelUsingLambda.apply(dto);
            userRepository.delete(user);

            logger.log(Level.INFO, "UserServiceImpl - delete() successfully.");

            return Boolean.TRUE;
            //**
            // Method 2 : with orElseThrow()

//            User user = userRepository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + dto.getId()));;
//            if (!ObjectUtils.isEmpty(user.getId())) {
//                userRepository.delete(user);
//            }
        } catch (EntityNotFoundException enf) {

            logger.log(Level.WARNING, "UserServiceImpl - delete() " + enf.getMessage());
        }

        return Boolean.FALSE;
    }

    @Override
    public UserDto changeStatus(Long id, UserDto userDto){

        logger.log(Level.INFO, "UserServiceImpl - changeStatus() called in id = {0}, userDto.id = {1}.", List.of(id, userDto.getId()));

        if(!ObjectUtils.isEmpty(id) && !ObjectUtils.isEmpty(userDto)){
            User user = userRepository.getById(id);
            user.setStatus(userDto.getStatus());
            userRepository.save(user);
        }
        return userDto;
    }
}
