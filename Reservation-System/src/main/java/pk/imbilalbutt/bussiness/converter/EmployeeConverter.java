package pk.imbilalbutt.bussiness.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pk.imbilalbutt.bussiness.dto.EmployeeDto;
import pk.imbilalbutt.bussiness.model.Employee;
import pk.imbilalbutt.bussiness.model.User;
import pk.imbilalbutt.bussiness.repository.CourseRepository;
import pk.imbilalbutt.bussiness.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component(value = "EmployeeConverter")
public class EmployeeConverter extends BaseConverter<Employee, EmployeeDto> {

    private static final Logger logger = Logger.getLogger(EmployeeConverter.class.getName());

    // region Dependencies
    @Autowired
    @Qualifier(value="UserRepository")
    UserRepository userRepository;

    @Autowired
    @Qualifier(value="UserConverter")
    UserConverter userConverter;

    @Autowired
    @Qualifier(value="CourseRepository")
    CourseRepository courseRepository;

    @Autowired
    @Qualifier(value="CourseConverter")
    CourseConverter courseConverter;
    // endregion

    /**
     * To Model Converter
     */

    public Employee convertToModel(EmployeeDto dto) {
        return this.convertToModelUsingLambda.apply(dto);
    }

    public Function<EmployeeDto,Employee > convertToModelUsingLambda = (dto) -> {
        Employee entity = new Employee();
        if(!ObjectUtils.isEmpty(dto)) {
            entity.setId(dto.getId() != null ? dto.getId() : null);
            entity.setOfficeAddress(dto.getOfficeAddress());
            entity.setOfficeNumber(dto.getOfficeNumber());
            entity.setOfficeStartDate(dto.getOfficeStartDate());

            if(!ObjectUtils.isEmpty(dto.getUser()) && !ObjectUtils.isEmpty(dto.getUser().getId())){
                User user = userRepository.getById(dto.getUser().getId());
                entity.setUser(user);
            }

            if(!ObjectUtils.isEmpty(dto.getCourses()) && !ObjectUtils.isEmpty(dto.getCourses().get(0).getId())){
                entity.setCourses(courseConverter.convertToModelList(dto.getCourses()));
            }

            super.convertToModel(entity, dto);

        }
        return entity;
    };

    public List<Employee> convertToModelList(final Collection<EmployeeDto> dtos) {
        return dtos.stream()
                .map(convertToModelUsingLambda)
                .collect(Collectors.toList());
    }


    /**
     * To Model Converter
     */

    public EmployeeDto convertToDto(Employee entity){
        return this.convertToDtoUsingLambda.apply(entity);
    }

    public Function<Employee, EmployeeDto> convertToDtoUsingLambda = (entity) -> {
        EmployeeDto dto = new EmployeeDto();
        if (!ObjectUtils.isEmpty(entity) && !ObjectUtils.isEmpty(entity.getId())) {
            dto.setId(entity.getId());
            dto.setOfficeAddress(entity.getOfficeAddress());
            dto.setOfficeNumber(entity.getOfficeNumber());
            dto.setOfficeStartDate(entity.getOfficeStartDate());

            if(!ObjectUtils.isEmpty(entity.getUser()) && !ObjectUtils.isEmpty(entity.getUser().getId())){
                dto.setUser(userConverter.convertToDto(entity.getUser()));
            }

            if(!ObjectUtils.isEmpty(entity.getCourses()) && !ObjectUtils.isEmpty(entity.getCourses().get(0).getId())){
                dto.setCourses(courseConverter.convertToDtoList(entity.getCourses()));
            }

            super.convertToDTO(entity, dto);
        }
        return dto;
    };

    public List<EmployeeDto> convertToDtoList(final Collection<Employee> modelList) {
        return modelList.stream()
                .map(convertToDtoUsingLambda)
                .collect(Collectors.toList());
    }
}
