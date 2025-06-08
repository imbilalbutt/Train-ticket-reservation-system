//package pk.imbilalbutt.bussiness.controller.ViewController;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import pk.imbilalbutt.bussiness.controller.RESTController.AuthenticationRestController;
//import pk.imbilalbutt.bussiness.dto.UserCredentialsDto;
//import pk.imbilalbutt.bussiness.dto.UserDto;
//import pk.imbilalbutt.bussiness.dto.request.CreateAccountRequestDTO;
//import pk.imbilalbutt.bussiness.dto.request.LoginRequest;
////import pk.imbilalbutt.bussiness.service.UserCredentialsService;
//import pk.imbilalbutt.bussiness.service.UserService;
//import pk.imbilalbutt.bussiness.transformer.request.CreateAccountRequestTransformer;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@RequestMapping(path = "/view/home")
//public class Authentication {
//
//    private static final Logger LOG = LoggerFactory.getLogger(Authentication.class);
//
//    @Autowired
//    public AuthenticationManager manager;
//
//    @Autowired
//    public CreateAccountRequestTransformer transformer;
//
////    @Autowired
////    public SessionRegistry sessionRegistry;
//
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping(path = "/dashboard")
//    public String dashboard() {
//
//        LOG.info("AuthenticationViewController - dashboard() invoked.");
//
//        return "dashboard";
//    }
//
//    /**
//     * @param loginRequest
//     * @param model
//     * @return Steps:
//     * 1) Include an attribute in a page by adding it in addAttribute
//     * 2) return the html file name.
//     */
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//    @GetMapping("/login")
//    public String showLoginPage(LoginRequest loginRequest, Model model) {
//
//        LOG.info("AuthenticationViewController - GET showLoginPage()");
//
//        model.addAttribute("loginRequest", loginRequest);
//
////        Return the name of HTML login page
//        return "login";
//    }
//
//    /**
//     * @param loginRequest
//     * @param model
//     * @return Steps:
//     * 1) Fetch the ModelAttribute there are two methods to do so (see below)
//     * 2) Do the processing and return the first landing page
//     */
//
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//    @PostMapping(path = "/perform_login")
//    public String loginSubmit(@ModelAttribute("loginRequest") LoginRequest loginRequest, Model model) {
//
//        LOG.info("AuthenticationViewController - POST loginSubmit()");
//
//        // Method 1
////        model.addAttribute("loginRequest", loginRequest);
////
////        String username = (String) model.getAttribute("username");
//
//        // writing the value to the properties by fetching from the URL
//        //LoginRequest gey = (LoginRequest) model.getAttribute("loginRequest");
//
//        LOG.info("AuthenticationViewController - login() - username : {}  ", loginRequest.getUsername());
//
//        manager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
//        );
//
////        final String sessionId = sessionRegistry.registerSession(loginRequest.getUsername());
//
//        return "dashboard";
//    }
//
//    @GetMapping(path = "/create")
//    public String showRegistrationPage(Model model) {
//
//        List<String> options = new ArrayList<String>();
//
//        LOG.info("AuthenticationViewController - GET showRegistrationPage()");
//
//        model.addAttribute("registrationDto", new CreateAccountRequestDTO());
//        model.addAttribute("options", options);
//
//        return "create-account";
//    }
//
//    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public String registerUserAccount(@ModelAttribute("registrationDto") CreateAccountRequestDTO registrationDto) {
//
//        LOG.info("AuthenticationViewController - POST registerUserAccount()");
//
//        UserDto userDto = transformer.transformToUserDto(registrationDto);
//
//        userService.saveOrUpdate(userDto);
//
//        return "redirect:/result";
//
////        return "redirect:/create-account?success";
//    }
//
//
//
//    @GetMapping(path = "/logout")
//    public String showLogoutPage(Model model) {
//
//        List<String> options = new ArrayList<String>();
//
//        LOG.info("AuthenticationViewController - GET showLogoutPage()");
//
//        return "logout";
//    }
//
//}
//
