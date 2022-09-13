package by.it_academy.jd2.concert.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"by.it_academy.jd2.concert.repository.api"})
public class JpaConfiguration {
}
