package by.it_academy.jd2.user.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"by.it_academy.jd2.user.repository.api"})
public class JpaConfiguration {
}
