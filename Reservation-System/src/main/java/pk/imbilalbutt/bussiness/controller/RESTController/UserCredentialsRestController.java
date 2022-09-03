//package pk.imbilalbutt.bussiness.controller.RESTController;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.bind.annotation.*;
//import pk.imbilalbutt.bussiness.dto.UserCredentialsDto;
//import pk.imbilalbutt.bussiness.service.UserCredentialsService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(value ="/rest/user/credentials")
//public class UserCredentialsRestController {
//
//    private static final Logger LOG = LoggerFactory.getLogger(UserCredentialsRestController.class);
//
//    @Autowired
////    @Qualifier(value="UserCredentialsService")
//    private UserCredentialsService userCredentialsService;
//
//    @PostMapping(path = "/save",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<UserCredentialsDto> save(@RequestBody UserCredentialsDto dto){
//
//        LOG.info("UserCredentialsRestController - save() called in : {} " , dto);
//
//        UserCredentialsDto response = userCredentialsService.saveOrUpdate(dto);
//
//        if(!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getId())){
//
//            LOG.info("UserCredentialsRestController - save() successful.");
//
//            return ResponseEntity.ok(response);
//        }
//
//        LOG.error("UserCredentialsRestController - save() failed.");
//
//        return ResponseEntity.badRequest().build();
//    }
//
//
//    @GetMapping(path = "/list")
//    public ResponseEntity<List<UserCredentialsDto>> getAll(){
//
//        LOG.info("UserCredentialsRestController - getAll() called in.");
//
//        List<UserCredentialsDto> response = userCredentialsService.getAll();
//
//        if(!response.isEmpty()){
//            LOG.info("UserCredentialsRestController - getAll() - response.size() {} - success.", response.size());
//            return ResponseEntity.ok(response);
//        }
//
//        LOG.error("UserCredentialsRestController - getAll() failed.");
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping(path = "/get/{id}",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<UserCredentialsDto> get(@PathVariable("id") Long id){
//
//        LOG.info("UserCredentialsRestController - get() called in : {} " , id);
//
//        UserCredentialsDto response = userCredentialsService.getById(id);
//
//        if(!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getId())){
//
//            LOG.info("UserCredentialsRestController - get() successful.");
//
//            return ResponseEntity.ok(response);
//        }
//
//        LOG.error("UserCredentialsRestController - get() failed.");
//
//        return ResponseEntity.notFound().build();
//    }
//
//    @DeleteMapping(path = "/delete/{id}",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> delete(@PathVariable("id") Long id){
//
//        LOG.info("UserCredentialsRestController - delete() called in : {} " , id);
//
//        Boolean found = Boolean.FALSE;
//
//        if(!ObjectUtils.isEmpty(id)){
//
//            found = userCredentialsService.delete(id);
//        }
//
//        if(found == Boolean.TRUE){
//            LOG.info("UserCredentialsRestController - delete() successful.");
//            return ResponseEntity.ok("UserCredentials removed!");
//        }
//
//        LOG.error("UserCredentialsRestController - delete() failed.");
//        return ResponseEntity.notFound().build();
//    }
//}
