package by.it_academy.jd2.film;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
public class EventFilmServiceApplication {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("ru"));
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(EventFilmServiceApplication.class, args);
    }

}
