package pk.imbilalbutt.bussiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import pk.imbilalbutt.bussiness.configuration.SecurityConfig;

import javax.servlet.ServletContext;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = BaseRepository.class)
public class SpringXclusiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringXclusiveApplication.class, args);
    }

    public class AppInitializer implements WebApplicationInitializer {

        @Override
        public void onStartup(ServletContext sc) {

            AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
            root.register(SecurityConfig.class);

            sc.addListener(new ContextLoaderListener(root));

            sc.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"))
                    .addMappingForUrlPatterns(null, false, "/*");
        }
    }

}
