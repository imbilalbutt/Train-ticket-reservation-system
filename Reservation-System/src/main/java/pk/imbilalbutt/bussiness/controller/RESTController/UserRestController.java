package pk.imbilalbutt.bussiness.controller.RESTController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import pk.imbilalbutt.bussiness.dto.UserDto;
import pk.imbilalbutt.bussiness.service.UserService;

import java.util.List;

// http://localhost:9090/Reservation-System/rest/user/list
@RestController
@RequestMapping(value = "/rest/user")
public class UserRestController {

    private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    @Qualifier(value = "UserService")
    private UserService userService;

    @PostMapping(path = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> save(@RequestBody UserDto dto) {

        LOG.info("UserRestController - save() called in : {} ", dto);

        UserDto response = userService.saveOrUpdate(dto);

        if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getId())) {

            LOG.info("UserRestController - save() successful.");

            return ResponseEntity.ok(response);
        }

        LOG.error("UserRestController - save() failed.");

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/list")
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<UserDto>> getAll() {

        LOG.info("UserRestController - getAll() called in.");

        List<UserDto> response = userService.getAll();

        if (!response.isEmpty()) {
            LOG.info("UserRestController - getAll() - response.size() {} - success.", response.size());
            return ResponseEntity.ok(response);
        }

        LOG.error("UserRestController - getAll() failed.");
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/get/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> get(@PathVariable("id") Long id) {

        LOG.info("UserRestController - get() called in : {}", id);

        UserDto response = userService.getById(id);

        if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getId())) {

            LOG.info("UserRestController - get() successful.");

            return ResponseEntity.ok(response);
        }

        LOG.error("UserRestController - get() failed.");

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/delete/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {

        LOG.info("UserRestController - delete() called in : {} ", id);

        Boolean found = Boolean.FALSE;

        if (!ObjectUtils.isEmpty(id)) {

            found = userService.delete(id);
        }

        if (Boolean.TRUE.equals(found)) {
            LOG.info("UserRestController - delete() successful.");
            return ResponseEntity.ok("User removed!");
        }

        LOG.error("UserRestController - delete() failed.");
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/status/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeStatus(@PathVariable("id") Long id, @RequestBody UserDto userDto) {

        LOG.info("UserRestController - changeStatus() called in : {} ", id);

        if (!ObjectUtils.isEmpty(userService.getById(id))) {

            userService.changeStatus(id, userDto);
            LOG.info("UserRestController - changeStatus() successful.");
            return ResponseEntity.ok().body(userDto);
        }

        LOG.error("UserRestController - changeStatus() failed.");
        return ResponseEntity.notFound().build();
    }



}
