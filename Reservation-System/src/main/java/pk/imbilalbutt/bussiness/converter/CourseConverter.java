package pk.imbilalbutt.bussiness.converter;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pk.imbilalbutt.bussiness.dto.CourseDto;
import pk.imbilalbutt.bussiness.model.Course;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component(value = "CourseConverter")
public class CourseConverter extends BaseConverter<Course, CourseDto> {

    private static final Logger logger = Logger.getLogger(CourseConverter.class.getName());

    /**
     * To Model Convert
    */

    public Course convertToModel(CourseDto dto) {
        return this.convertToModelUsingLambda.apply(dto);
    }

    public Function<CourseDto, Course > convertToModelUsingLambda = (dto) -> {
        Course entity = new Course();
        if(!ObjectUtils.isEmpty(dto)) {
            entity.setId(dto.getId() != null ? dto.getId() : null);
            entity.setCourseName(dto.getCourseName());
            entity.setCourseCode(dto.getCourseCode());
            entity.setCreditHours(dto.getCreditHours());

            super.convertToModel(entity, dto);

        }
        return entity;
    };

    public List<Course> convertToModelList(final Collection<CourseDto> dtos) {
        return dtos.stream()
                .map(convertToModelUsingLambda)
                .collect(Collectors.toList());
    }

    /**
     * To DTO Convert
     */

    public CourseDto convertToDto(Course entity){
        return this.convertToDtoUsingLambda.apply(entity);
    }

    public Function<Course, CourseDto> convertToDtoUsingLambda = (entity) -> {
        CourseDto dto = new CourseDto();
        if (!ObjectUtils.isEmpty(entity) && !ObjectUtils.isEmpty(entity.getId())) {
            dto.setId(entity.getId());
            dto.setCourseName(entity.getCourseName());
            dto.setCourseCode(entity.getCourseCode());
            dto.setCreditHours(entity.getCreditHours());

            super.convertToDTO(entity, dto);
        }
        return dto;
    };

    public List<CourseDto> convertToDtoList(final Collection<Course> modelList) {
        return modelList.stream()
                .map(convertToDtoUsingLambda)
                .collect(Collectors.toList());
    }
}
