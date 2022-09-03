package pk.imbilalbutt.bussiness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import pk.imbilalbutt.bussiness.converter.CourseConverter;
import pk.imbilalbutt.bussiness.dto.CourseDto;
import pk.imbilalbutt.bussiness.model.Course;
import pk.imbilalbutt.bussiness.repository.CourseRepository;
import pk.imbilalbutt.bussiness.service.CourseService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service(value="CourseService")
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = Logger.getLogger(CourseServiceImpl.class.getName());

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseConverter courseConverter;

    @Override
    public CourseDto getById(Long id) {
        
        logger.log(Level.INFO, "CourseServiceImpl - getById() called id : {0}.", id);

        if(!ObjectUtils.isEmpty(id)){

            Optional<Course> course = courseRepository.findById(id);

            if(course.isPresent()) {

                logger.log(Level.INFO, "CourseServiceImpl - getById() Found : {0}.", course.get().getId());

                //return courseConverter.convertToDtoUsingLambda.apply(course.get());
                return courseConverter.convertToDto(course.get());
            }
        }

        logger.log(Level.INFO, "CourseServiceImpl - getById() NOT found.");

        return null;
    }

    @Override
    public List<CourseDto> getAll() {

        logger.log(Level.INFO, "CourseServiceImpl - getAll() - called in.");

        List<Course> courses = new ArrayList<>();
        try{
            courses = courseRepository.findAll();

            logger.log(Level.INFO, "CourseServiceImpl - getAll() - courses.size() : {0}.", courses.size());

            return !ObjectUtils.isEmpty(courses) ? courseConverter.convertToDtoList(courses) : new ArrayList<>();

        } catch (NullPointerException npe) {

            logger.log(Level.WARNING, "CourseServiceImpl - getAll() - Not Found.", courses.size());

            throw new NullPointerException("Not Found Any Single One.");

        }
    }

    @Override
    public Boolean delete(Long id) throws EntityNotFoundException  {
        
        logger.log(Level.INFO, "CourseServiceImpl - delete() id : {0}.", id);

        try{

            Course course = courseRepository.getById(id);

            courseRepository.delete(course);

            logger.log(Level.INFO, "CourseServiceImpl - delete() successfully.");

            return Boolean.TRUE;

        } catch (EntityNotFoundException enf) {

            logger.log(Level.WARNING, "CourseServiceImpl - delete() failed.");

            return Boolean.FALSE;
        }

    }

    @Override
    public CourseDto saveOrUpdate(CourseDto dto) {
        
        logger.log(Level.INFO, "CourseServiceImpl - saveOrUpdate() called in." + dto);

        Course course = new Course();
        if (!ObjectUtils.isEmpty(dto)) {

            if (!ObjectUtils.isEmpty(dto.getId())) {

                course = courseRepository.findById(dto.getId()).get();

                if (!ObjectUtils.isEmpty(course) && !ObjectUtils.isEmpty(course.getId())) {
                    //course = courseConverter.convertToModelUsingLambda.apply(dto);
                    course = courseConverter.convertToModel(dto);
                    course = courseRepository.save(course);

                    logger.log(Level.INFO, "CourseServiceImpl - saveOrUpdate() - update(): {0} success.", course.getId());
                }
            } else {
//                course = courseConverter.convertToModelUsingLambda.apply(dto);
                course = courseConverter.convertToModel(dto);
                course = courseRepository.save(course);

                if (!ObjectUtils.isEmpty(course) && !ObjectUtils.isEmpty(course.getId())) {
                    dto.setId(course.getId());
                }
                logger.log(Level.INFO, "CourseServiceImpl - saveOrUpdate() - save(): {0} success." , course.getId());
            }
        }
        return dto;
    }
}
