//package pk.imbilalbutt.bussiness.controller.RESTController;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.bind.annotation.*;
//import pk.imbilalbutt.bussiness.dto.UserCredentialsDto;
//import pk.imbilalbutt.bussiness.dto.request.LoginRequest;
//import pk.imbilalbutt.bussiness.service.UserCredentialsService;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
//@RestController
//@RequestMapping(value ="/rest/user")
//public class UserCredentialsRestController {
//
//    private static final Logger LOG = LoggerFactory.getLogger(UserCredentialsRestController.class);
//
//    @Autowired
////    @Qualifier(value="UserCredentialsService")
//    private UserCredentialsService userCredentialsService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    // ---------------- LOGIN ----------------
//    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
//            );
//
//            if (authentication.isAuthenticated()) {
//                // Create session if not exists
//                HttpSession session = httpRequest.getSession(true);
//                LOG.info("UserRestController - login() successful for user: {}", request.getUsername());
//                return ResponseEntity.ok("Login successful. Session ID: " + session.getId());
//            }
//        } catch (AuthenticationException e) {
//            LOG.error("UserRestController - login() failed: {}", e.getMessage());
//            return ResponseEntity.status(401).body("Invalid username or password.");
//        }
//
//        return ResponseEntity.status(500).body("Login failed due to unknown error.");
//    }
//
//    // ---------------- LOGOUT ----------------
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//            LOG.info("UserRestController - logout() successful.");
//            return ResponseEntity.ok("Logout successful.");
//        }
//        LOG.warn("UserRestController - logout() attempted with no active session.");
//        return ResponseEntity.badRequest().body("No active session found.");
//    }
//}
