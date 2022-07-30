package by.it_academy.jd2.EventFilmService.validation;

import by.it_academy.jd2.EventFilmService.validation.api.IClassifierClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Component
public class ClassifierClient implements IClassifierClient {

    private final RestTemplate template;

    public ClassifierClient(RestTemplate template) {
        this.template = template;
    }
    public boolean validCountry(UUID uuid) {

        HttpStatus statusCode;

        try {
            String url = "http://ClassifierService:80/classifier/country/" + uuid;
            ResponseEntity<String> res = template.getForEntity(url, String.class);
            statusCode = res.getStatusCode();
        } catch (ResourceAccessException e) {
            throw new RuntimeException("ошибка подключения к справочнику");
        }
        if (!statusCode.is2xxSuccessful()) {
            throw new EntityNotFoundException("выбранная страна отсутсвует в справочнике");
        }
        return true;
    }
}
