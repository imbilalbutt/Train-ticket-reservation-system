package pk.imbilalbutt.bussiness.controller.RESTController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import pk.imbilalbutt.bussiness.dto.StationDto;
import pk.imbilalbutt.bussiness.service.StationService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/station")
public class StationRestController {

    private static final Logger LOG = LoggerFactory.getLogger(StationRestController.class);

    @Autowired
    private StationService stationService;

    @PostMapping(path = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StationDto> save(@RequestBody StationDto dto) {

        LOG.info("StationRestController - save() called in : {} ", dto);

        StationDto response = stationService.saveOrUpdate(dto);

        if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getId())) {

            LOG.info("StationRestController - save() successful.");

            return ResponseEntity.ok(response);
        }

        LOG.error("StationRestController - save() failed.");

        return ResponseEntity.badRequest().build();
    }


    @GetMapping(path = "/list")
    public ResponseEntity<List<StationDto>> getAll() {

        LOG.info("StationRestController - getAll() called in.");

        List<StationDto> response = stationService.getAll();

        if (!response.isEmpty()) {
            LOG.info("StationRestController - getAll() - response.size() {} - success.", response.size());
            return ResponseEntity.ok(response);
        }

        LOG.error("StationRestController - getAll() failed.");
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/get/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StationDto> get(@PathVariable("id") Long id) {

        LOG.info("StationRestController - get() called in : {} ", id);

        StationDto response = stationService.getById(id);

        if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getId())) {

            LOG.info("StationRestController - get() successful.");

            return ResponseEntity.ok(response);
        }

        LOG.error("StationRestController - get() failed.");

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/delete/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {

        LOG.info("StationRestController - delete() called in : {} ", id);

        Boolean found = Boolean.FALSE;

        if (!ObjectUtils.isEmpty(id)) {

            found = stationService.delete(id);
        }

        if (found == Boolean.TRUE) {
            LOG.info("StationRestController - delete() successful.");
            return ResponseEntity.ok("Station removed!");
        }

        LOG.error("StationRestController - delete() failed.");
        return ResponseEntity.notFound().build();
    }
}
