//package pk.imbilalbutt.bussiness.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import pk.imbilalbutt.bussiness.common.AppConstants;
//import pk.imbilalbutt.bussiness.service.UserCredentialsService;
//import SessionFilter;
//
//import javax.servlet.http.HttpServletResponse;
//
//// TODO: uncomment these lines if you want to use this file.
////@Configuration
////@EnableWebSecurity
////@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
//
//    final private UserCredentialsService userCredentialsService;
//
//    @Autowired
//    private SessionFilter sessionFilter;
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public SecurityConfiguration(@Qualifier("UserCredentialsService") UserCredentialsService userCredentialsService, PasswordEncoder passwordEncoder) {
//        this.userCredentialsService = userCredentialsService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    /**
//     * Below two methods from lillium code
//     */
////    @Bean
////    AuthenticationProvider authenticationProvider() {
////
////        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
////
////        provider.setUserDetailsService(userCredentialsService);
////        provider.setPasswordEncoder(passwordEncoder);
////
////        return  provider;
////    }
////
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userCredentialsService).passwordEncoder(passwordEncoder);
////    }
//
//    /**
//     * Below two methods from Amigos code
//     */
//    @Bean
//    DaoAuthenticationProvider daoAuthenticationProvider() {
//
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//
//        provider.setUserDetailsService(userCredentialsService);
//        provider.setPasswordEncoder(passwordEncoder);
//
//        return provider;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        TODO: uncomment if you want to use this file.
////        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/rest/**")
//                .allowedOrigins("http://localhost:4200", "http://localhost:8080", "http://localhost:8081")
//                .allowedMethods("PUT", "DELETE", "GET", "POST");
//    }
//
//    /**
//     * Authorization
//     */
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http = http.cors().and()
//                .csrf().disable()
////                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//        ;
//
//        http = http.exceptionHandling().authenticationEntryPoint(
//                (request, response, ex) -> response.sendError(
//                        HttpServletResponse.SC_UNAUTHORIZED,
//                        ex.getMessage())
//        ).and();
//
//        http.authorizeRequests()
//                .antMatchers("/rest/home/**", "/view/home/**").permitAll()
////                .antMatchers("/rest/home/login", "/rest/home/create").permitAll()
//                .antMatchers("/rest/user/**").hasAuthority(AppConstants.Roles.User.getRole())
//                .antMatchers("/rest/admin/**").hasAuthority(AppConstants.Roles.Admin.getRole())
//                .antMatchers(HttpMethod.GET, "/rest/management/**").hasAnyRole(AppConstants.Roles.Admin.getRole(), AppConstants.Roles.Management.getRole())
//                .antMatchers(HttpMethod.PUT, "/rest/management/**").hasAuthority(AppConstants.Roles.Management.getRole())
//                .antMatchers(HttpMethod.POST, "/rest/management/**").hasAuthority(AppConstants.Roles.Management.getRole())
//                .antMatchers(HttpMethod.DELETE, "/rest/management/**").hasAuthority(AppConstants.Roles.Management.getRole())
//
//                .anyRequest()
//                .authenticated()
////                .and()
////                .sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .formLogin()
//                .loginPage("/view/home/login").permitAll()
//                //.defaultSuccessUrl("/view/home/dashboard", true)
//                .passwordParameter("password")
//                .usernameParameter("username")
////                .and()
////                .rememberMe()
////                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
////                .key("somethingversecured")
////                .rememberMeParameter("remember-me")
////                .httpBasic() // not required
//                .and()
//                .logout()
//                .logoutUrl("/view/home/logout")
//                // if csrf() is enabled then delete this single whole below line logoutRequestMatcher(), because you want that to be POST
//                .logoutRequestMatcher(new AntPathRequestMatcher("logoutUrl", "GET"))
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID", "SESSIONID", "Authorization")
//                .logoutSuccessUrl("/view/home/login")
//
//        ;
//
//        http.addFilterBefore(
//                sessionFilter,
//                UsernamePasswordAuthenticationFilter.class
//        );
//    }
//
//
//}