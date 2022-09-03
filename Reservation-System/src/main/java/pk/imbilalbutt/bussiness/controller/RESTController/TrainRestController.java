package pk.imbilalbutt.bussiness.controller.RESTController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import pk.imbilalbutt.bussiness.dto.TrainDto;
import pk.imbilalbutt.bussiness.service.TrainService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/train")
public class TrainRestController {

    private static final Logger LOG = LoggerFactory.getLogger(TrainRestController.class);

    @Autowired
    @Qualifier(value = "TrainService")
    private TrainService trainService;

    @PostMapping(path = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrainDto> save(@RequestBody TrainDto dto) {

        LOG.info("TrainRestController - save() called in : {} ", dto);

        TrainDto response = trainService.saveOrUpdate(dto);

        if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getId())) {

            LOG.info("TrainRestController - save() successful.");

            return ResponseEntity.ok(response);
        }

        LOG.error("TrainRestController - save() failed.");

        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/list")
    public ResponseEntity<List<TrainDto>> getAll() {

        LOG.info("TrainRestController - getAll() called in.");

        List<TrainDto> response = trainService.getAll();

        if (!response.isEmpty()) {
            LOG.info("TrainRestController - getAll() - response.size() {} - success.", response.size());
            return ResponseEntity.ok(response);
        }

        LOG.error("TrainRestController - getAll() failed.");
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/get/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrainDto> get(@PathVariable("id") Long id) {

        LOG.info("TrainRestController - get() called in : {} ", id);

        TrainDto response = trainService.getById(id);

        if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getId())) {

            LOG.info("TrainRestController - get() successful.");

            return ResponseEntity.ok(response);
        }

        LOG.error("TrainRestController - get() failed.");

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/delete/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {

        LOG.info("TrainRestController - delete() called in : {} ", id);

        Boolean found = Boolean.FALSE;

        if (!ObjectUtils.isEmpty(id)) {

            found = trainService.delete(id);
        }

        if (found == Boolean.TRUE) {
            LOG.info("TrainRestController - delete() successful.");
            return ResponseEntity.ok("Train removed!");
        }

        LOG.error("TrainRestController - delete() failed.");
        return ResponseEntity.notFound().build();
    }
}
