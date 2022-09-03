//package pk.imbilalbutt.bussiness.configuration.session;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//import pk.imbilalbutt.bussiness.model.UserCredentials;
//import pk.imbilalbutt.bussiness.service.UserCredentialsService;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@Component
//public class SessionFilter extends OncePerRequestFilter {
//
//    private static final Logger logger = Logger.getLogger(SessionFilter.class.getName());
//
//    final private UserCredentialsService userCredentialsService;
//
//    final private SessionRegistry sessionRegistry;
//
//    @Autowired
//    public SessionFilter(final @Qualifier("UserCredentialsService") UserCredentialsService userCredentialsService,
//                         final SessionRegistry sessionRegistry) {
//        this.userCredentialsService = userCredentialsService;
//        this.sessionRegistry = sessionRegistry;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain chain) throws ServletException, IOException {
//
//        logger.log(Level.INFO, "SessionFilter - doFilterInternal()");
//
//        final String sessionId = request.getHeader(HttpHeaders.AUTHORIZATION);
//
//        if (ObjectUtils.isEmpty(sessionId) || sessionId.length() == 0) {
//            // No authorization in this case, you go back
//            chain.doFilter(request, response);
//            return;
//        }
//
//        // If you have your sessionId
//        String username = sessionRegistry.getUsernameForSession(sessionId);
//        if (ObjectUtils.isEmpty(username)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        try {
//            // TODO: fix - Why it is casting?
//            UserCredentials userCredentials = (UserCredentials) userCredentialsService.loadUserByUsername(username);
//
//            final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userCredentials, null, userCredentials.getAuthorities());
//
//            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(auth);
//
////            SecurityContextHolder.getContext().setAuthentication(auth);
//
//            logger.log(Level.INFO, "SessionFilter - doFilterInternal() - SecurityContextHolder.getContext() : {} ", SecurityContextHolder.getContext());
//
//            logger.log(Level.INFO, "SessionFilter - doFilterInternal() - SecurityContextHolder.getContext().getAuthentication : {} ", SecurityContextHolder.getContext().getAuthentication());
//
//            logger.log(Level.INFO, "SessionFilter - doFilterInternal() - SecurityContextHolder.getContext().getAuthentication().getPrincipal() : {} ", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//
//        } catch (UsernameNotFoundException ex) {
//
//        }
//        // check if user exist
//
//        chain.doFilter(request, response);
//    }
//
//}
