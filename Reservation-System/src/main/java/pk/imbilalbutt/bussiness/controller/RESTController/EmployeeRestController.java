package pk.imbilalbutt.bussiness.controller.RESTController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import pk.imbilalbutt.bussiness.dto.EmployeeDto;
import pk.imbilalbutt.bussiness.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/employee")
public class EmployeeRestController {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeRestController.class);

    @Autowired
    @Qualifier(value = "EmployeeService")
    private EmployeeService employeeService;

    @PostMapping(path = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto dto) {

        LOG.info("EmployeeRestController - save() called in : {} ", dto);

        EmployeeDto response = employeeService.saveOrUpdate(dto);

        if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getId())) {

            LOG.info("EmployeeRestController - save() successful.");

            return ResponseEntity.ok(response);
        }

        LOG.error("EmployeeRestController - save() failed.");

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<EmployeeDto>> getAll() {

        LOG.info("EmployeeRestController - getAll() called in.");

        List<EmployeeDto> response = employeeService.getAll();

        if (!response.isEmpty()) {
            LOG.info("EmployeeRestController - getAll() - response.size() {} - success.", response.size());
            return ResponseEntity.ok(response);
        }

        LOG.error("EmployeeRestController - getAll() failed.");
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/get/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> get(@PathVariable("id") Long id) {

        LOG.info("EmployeeRestController - get() called in : {} ", id);

        EmployeeDto response = employeeService.getById(id);

        if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getId())) {

            LOG.info("EmployeeRestController - get() successful.");

            return ResponseEntity.ok(response);
        }

        LOG.error("EmployeeRestController - get() failed.");

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/delete/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {

        LOG.info("EmployeeRestController - delete() called in : {} ", id);

        Boolean found = Boolean.FALSE;

        if (!ObjectUtils.isEmpty(id)) {

            found = employeeService.delete(id);
        }

        if (found == Boolean.TRUE) {
            LOG.info("EmployeeRestController - delete() successful.");
            return ResponseEntity.ok("Employee removed!");
        }

        LOG.error("EmployeeRestController - delete() failed.");
        return ResponseEntity.notFound().build();
    }
}
