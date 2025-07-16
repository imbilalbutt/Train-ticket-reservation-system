package pk.imbilalbutt.bussiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@SpringBootApplication
public class SpringXclusiveApplication extends AbstractSecurityWebApplicationInitializer {
    // No additional code is needed; the superclass handles the initialization.
    public static void main(String[] args) {
        // Application logic
        SpringApplication.run(SpringXclusiveApplication.class, args);

    }
}


//@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = BaseRepository.class)
//public class SpringXclusiveApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(SpringXclusiveApplication.class, args);
//    }
//
////    public class AppInitializer implements WebApplicationInitializer {
////
////        @Override
////        public void onStartup(ServletContext sc) {
////
////            AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
////            root.register(SecurityConfig.class);
////
////            sc.addListener(new ContextLoaderListener(root));
////
////            sc.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"))
////                    .addMappingForUrlPatterns(null, false, "/*");
////        }
////    }
//
//}
