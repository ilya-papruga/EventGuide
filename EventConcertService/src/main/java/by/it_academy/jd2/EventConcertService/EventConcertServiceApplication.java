package by.it_academy.jd2.EventConcertService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories("by.it_academy.jd2.EventConcertService.core.dao.api")
public class EventConcertServiceApplication {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("ru"));
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(EventConcertServiceApplication.class, args);
    }

}
