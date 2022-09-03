package pk.imbilalbutt.bussiness.controller.RESTController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pk.imbilalbutt.bussiness.dto.UserDto;
import pk.imbilalbutt.bussiness.dto.request.CreateAccountRequestDTO;
import pk.imbilalbutt.bussiness.dto.request.LoginRequest;
import pk.imbilalbutt.bussiness.dto.response.ResponseDTO;
import pk.imbilalbutt.bussiness.transformer.request.CreateAccountRequestTransformer;


@RestController
@RequestMapping(path = "/rest/home")
public class AuthenticationRestController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationRestController.class);

    @Autowired
    public AuthenticationManager manager;

//    @Autowired
//    public InMemorySessionRegistry sessionRegistry;

//    @Autowired
//    public SessionRegistry sessionRegistry;

    @Autowired
    public CreateAccountRequestTransformer transformer;

    /*

    @Copied https://stackoverflow.com/questions/58688848/why-spring-security-hasrole-function-does-not-authenticate-any-apis
     */

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(@RequestParam(value = "email") String email,
//                        @RequestParam(value = "password") String password, HttpSession session) {
//        Result result = userService.login(email, password);
//        session.setAttribute("user", result.getData());
//
//        if(result.getStatus() == 200){
//            return  "redirect:/profile";
//        } else {
//            return "redirect:/login?error";
//        }
//    }
//
//    @RequestMapping(value = "/profile", method = RequestMethod.GET)
//    public String profile(HttpSession httpSession, Model model) {
//        if(httpSession.getAttribute("user") != null) {
//            UserResponse user = (UserResponse) httpSession.getAttribute("user");
//            model.addAttribute("user", user);
//            return "profile";
//        } else {
//            return "redirect:/home";
//        }
//    }
    //////////////////


    @PostMapping(path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody LoginRequest user) {

        LOG.info("AuthenticationRestController - login() invoked - user : {} ", user);

        manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

//        final String sessionId = sessionRegistry.registerSession(user.getUsername());
        ResponseDTO response = new ResponseDTO();
//        response.setSessionId(sessionId);

        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> create_account(@RequestBody @Validated final CreateAccountRequestDTO request) {

        LOG.info("AuthenticationRestController - create_account() invoked.");

//        final String sessionId = sessionRegistry.registerSession(request.getUsername());

        try {
            RestTemplate restTemplate = new RestTemplate();

            final String USER_SAVE_URL = "http://localhost:8081/rest/user/save";

            final UserDto userDto = transformer.transformToUserDto(request);

            HttpHeaders headers = new HttpHeaders();
//            headers.set(HttpHeaders.AUTHORIZATION, sessionId);
            headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
            headers.setConnection("keep-alive");

            HttpEntity<UserDto> httpUserDtoRequest = new HttpEntity<>(userDto, headers);

//            ResponseEntity<UserDto> response = restTemplate.postForEntity(USER_SAVE_URL, httpUserDtoRequest, UserDto.class);
            ResponseEntity<UserDto> response = new RestTemplate().exchange(USER_SAVE_URL, HttpMethod.POST, httpUserDtoRequest, UserDto.class);

            // get JSON response
            UserDto json = response.getBody();

            LOG.info("AuthenticationRestController - create_account() - Successfully created user : {} ", json);

        } catch (Exception e) {
            LOG.error("AuthenticationRestController - create_account() failed in user : {} ", e.getMessage());
            return ResponseEntity.internalServerError().body("Duh !! Failed to create User.");
        }

        return ResponseEntity.ok("Account Successfully Created.");
    }

}
