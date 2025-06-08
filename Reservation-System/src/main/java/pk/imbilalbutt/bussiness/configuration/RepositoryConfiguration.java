package pk.imbilalbutt.bussiness.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@EnableAspectJAutoProxy
// TODO: just for testing purpose added this line in main SpringApplication file.
@EnableJpaRepositories(basePackageClasses = pk.imbilalbutt.bussiness.repository.BaseRepository.class)
@Configuration
public class RepositoryConfiguration {
}
