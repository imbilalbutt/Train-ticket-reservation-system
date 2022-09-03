package pk.imbilalbutt.bussiness.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

//    @Autowired
//    private SessionFilter sessionFilter;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
//        StrictHttpFirewall firewall = new StrictHttpFirewall();
//        /*firewall.setAllowUrlEncodedSlash(true);
//        firewall.setAllowSemicolon(true);*/
//        firewall.setAllowUrlEncodedDoubleSlash(true);
//        return firewall;
//    }
//
//    @Override
//    public void configure(WebSecurity web) {
//        web.httpFirewall(allowUrlEncodedSlashHttpFirewall())
//                .ignoring()
//                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/templates/**");
//
//    }

    /**
     * souce: javabrain
     * In this method
     */

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/").permitAll()
//                .and().formLogin();
//    }


    /**
     * In this method
     * COMPLEX
     */

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http = http
////                .cors().and()
//                .csrf().disable()
////                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//        ;
//
////        http = http.exceptionHandling().authenticationEntryPoint(
////                (request, response, ex) -> response.sendError(
////                        HttpServletResponse.SC_UNAUTHORIZED,
////                        ex.getMessage())
////        ).and();
//
//        http.authorizeRequests()
//                .antMatchers("/rest/user/**").hasAuthority(AppConstants.Roles.User.getRole())
//                .antMatchers("/rest/admin/**").hasAuthority(AppConstants.Roles.Admin.getRole())
//                .antMatchers(HttpMethod.GET, "/rest/management/**").hasAnyRole(AppConstants.Roles.Admin.getRole(), AppConstants.Roles.Management.getRole())
//                .antMatchers(HttpMethod.PUT, "/rest/management/**").hasAuthority(AppConstants.Roles.Management.getRole())
//                .antMatchers(HttpMethod.POST, "/rest/management/**").hasAuthority(AppConstants.Roles.Management.getRole())
//                .antMatchers(HttpMethod.DELETE, "/rest/management/**").hasAuthority(AppConstants.Roles.Management.getRole())
//
//                .antMatchers("/rest/home/**", "/view/home/**").permitAll()
//                .antMatchers("/rest/home/login", "/rest/home/create").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/create").permitAll()
//                .antMatchers("/").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login.html")
//                .loginProcessingUrl("/view/home/login")
//                .defaultSuccessUrl("/homepage.html", true)
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

    /**
     * Below are Simple Hardcoded Methods
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // Set your configuration on the auth object
//        auth.inMemoryAuthentication()
//                .withUser("blah")
//                .password("blah")
//                .roles("USER")
//                .and()
//                .withUser("foo")
//                .password("foo")
//                .roles("ADMIN");
//    }


    /**
     * In this method
     * loginPage("login.html")
     */

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/view/home/login").permitAll()
//                .antMatchers("/view/home/perform_login").permitAll()
//                .antMatchers("/view/home/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/rest/home/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/rest/**").hasAnyRole("ADMIN", "USER")
//                .and()
//                .formLogin()
//                .loginPage("login.html")
//                .loginProcessingUrl("/view/home/perform_login")
//                .defaultSuccessUrl("/view/home/dashboard", true)
//                .and()
//                .logout()
//                .logoutUrl("/view/home/logout")
//
//        ;
//    }

    /**
     * Source: lillium code
     * In this method (working 1)
     * Initial   .loginPage("/view/home/login")
     * .loginProcessingUrl("/view/home/perform_login")
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        try {

//        http = http.cors().and().csrf().disable();
//
//        http = http.exceptionHandling().authenticationEntryPoint(
//                (request, response, ex) -> response.sendError(
//                        HttpServletResponse.SC_UNAUTHORIZED,
//                        ex.getMessage())
//        ).and();

            http.authorizeRequests()
                    //.antMatchers("/view/home/login", "/view/home/perform_login", "/login*").permitAll()
                    .antMatchers("/view/home/**", "/rest/home/**").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/admin/**").hasRole("ADMIN")
////                .anyRequest().authenticated()
//                    .anyRequest().hasRole("ADMIN")////.permitAll()
//                    .and()
//                    .authorizeRequests().anyRequest().authenticated()
                    .and()
                    .formLogin()

                    .loginPage("/view/home/login").permitAll().usernameParameter("username").passwordParameter("password")
                    .loginProcessingUrl("/view/home/perform_login")
                    .defaultSuccessUrl("/view/home/dashboard", true)

                    // 1
                    // Invalid username and password.
                    // is se controller ki taraf nai ja raha direct userCredentialsService ki taraf ja raha ha
//                .loginPage("/view/home/login")
//                .loginProcessingUrl("/view/home/perform_login")

                    // 2
                    // perform_login = (type=Unauthorized, status=401).
//                .loginPage("/view/home/login")
//                .loginProcessingUrl("/view/home/login")

                    // 3
                    // BUILD ERROR
//                .loginPage("login.html")
//                .loginProcessingUrl("/view/home/login")

                    // 4
                    // BUILD ERROR
//                .loginPage("login.html")
//                .loginProcessingUrl("/view/home/perform_login")

                    // 5
                    // /perform_login page or url called ; unauthorized
//                .loginPage("/view/home/login")

                    // 6
                    // BUILD ERROR
//                .loginPage("login.html")

                    // 7
                    // /perform_login page or url called ; unauthorized
//                .loginProcessingUrl("/view/home/login")

                    // 8
                    // ERROR : this calls the url http://localhost:8081/login?error ; & unauthorized & 404
//                .loginProcessingUrl("/view/home/perform_login")

                    .and()
                    .logout()
                    .logoutUrl("/view/home/logout")

            ;

//        http.addFilterBefore(
//                sessionFilter,
//                UsernamePasswordAuthenticationFilter.class
//        );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @source: https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html
     * formLogin k ander loginPage dala ha
     * Invalid username & password thou username & password is found from db.
     */

//    @Bean
//    AuthenticationProvider authenticationProvider() {
//
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(getPasswordEncoder());
//
//        return provider;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http = http.cors().and().csrf().disable();
//
//        http = http.exceptionHandling().authenticationEntryPoint(
//                (request, response, ex) -> response.sendError(
//                        HttpServletResponse.SC_UNAUTHORIZED,
//                        ex.getMessage())
//        ).and();
//
////        http.authenticationProvider(authenticationProvider());
//
//        http.authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/view/home/login").permitAll()
//                .antMatchers("/view/home/perform_login").permitAll()
//                .antMatchers("/view/home/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/rest/home/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/rest/**").hasAnyRole("ADMIN", "USER")
////                .anyRequest().authenticated()
//                .and()
//                .formLogin(form -> form
////                        .loginPage("/login.html")
//                        .loginPage("/view/home/login")
//                        .loginProcessingUrl("/view/home/perform_login")
//                        .defaultSuccessUrl("/view/home/dashboard", false)
//                        .permitAll())
//                .logout()
//                .logoutUrl("/view/home/logout")
//
//        ;
//
//
////        http.addFilterBefore(
////                sessionFilter,
////                UsernamePasswordAuthenticationFilter.class
////        );
//    }

    /**
     * @source: https://github.com/lokeshgupta1981/Spring-security/blob/84a688331de81e075163318788b557393365a65f/Spring-security-with-mvc/src/main/java/com/howtodoinjava/app/security/SecurityConfig.java
     * In this method
     */

//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//
//        //@formatter:off
//        http.authorizeRequests()
//                .antMatchers("/view/home/login").permitAll()
//                .antMatchers("/**").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/admin/**").hasAnyRole("ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/view/home/login")
//                .loginProcessingUrl("/view/home/process-login")
//                .defaultSuccessUrl("/view/home/dashboard")
//                .failureUrl("/login?error=true")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login?logout=true")
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .permitAll()
//                .and()
//                .csrf()
//                .disable();
//        //@formatter:on
//    }
}