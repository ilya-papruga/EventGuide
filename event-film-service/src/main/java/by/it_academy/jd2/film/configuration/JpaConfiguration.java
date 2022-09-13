package by.it_academy.jd2.film.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"by.it_academy.jd2.film.repository.api"})
public class JpaConfiguration {
}
