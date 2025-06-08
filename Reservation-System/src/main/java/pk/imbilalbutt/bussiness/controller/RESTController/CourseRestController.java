package pk.imbilalbutt.bussiness.controller.RESTController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import pk.imbilalbutt.bussiness.dto.CourseDto;
import pk.imbilalbutt.bussiness.service.CourseService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/course")
public class CourseRestController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseRestController.class);

    /**
     * Method 1: Autowired only, works fine
     */
//    @Autowired
//    private CourseService courseService;

    /**
     * Method 2: Autowired + Qualifier
     * Sorted out by myself, works fine
     */
//    @Autowired
//    @Qualifier(value="CourseService")
//    private CourseService courseService;


    /**
     * Method 3 : final + Constructor injection + Qualifer
     * Amigos code helped me in this, works fine
     */
    final private CourseService courseService;

    public CourseRestController(@Qualifier("CourseService") CourseService courseService) {
        this.courseService = courseService;
    }


    @PostMapping(path = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDto> save(@RequestBody CourseDto dto) {

        LOG.info("CourseRestController - save() called in : {} ", dto);

        CourseDto response = courseService.saveOrUpdate(dto);

        if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getId())) {

            LOG.info("CourseRestController - save() successful.");

            return ResponseEntity.ok(response);
        }

        LOG.error("CourseRestController - save() failed.");

        return ResponseEntity.badRequest().build();
    }


    @GetMapping(path = "/list")
    public ResponseEntity<List<CourseDto>> getAll() {

        LOG.info("CourseRestController - getAll() called in.");

        List<CourseDto> response = courseService.getAll();

        if (!response.isEmpty()) {
            LOG.info("CourseRestController - getAll() - response.size() {} - success.", response.size());
            return ResponseEntity.ok(response);
        }

        LOG.error("CourseRestController - getAll() failed.");
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/get/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDto> get(@PathVariable("id") Long id) {

        LOG.info("CourseRestController - get() called in : {} ", id);

        CourseDto response = courseService.getById(id);

        if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getId())) {

            LOG.info("CourseRestController - get() successful.");

            return ResponseEntity.ok(response);
        }

        LOG.error("CourseRestController - get() failed.");

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {

        LOG.info("CourseRestController - delete() called in : {} ", id);

        Boolean found = Boolean.FALSE;

        if (!ObjectUtils.isEmpty(id)) {

            found = courseService.delete(id);
        }

        if (found == Boolean.TRUE) {
            LOG.info("CourseRestController - delete() successful.");
            return ResponseEntity.ok("Course removed!");
        }

        LOG.error("CourseRestController - delete() failed.");
        return ResponseEntity.notFound().build();
    }

}