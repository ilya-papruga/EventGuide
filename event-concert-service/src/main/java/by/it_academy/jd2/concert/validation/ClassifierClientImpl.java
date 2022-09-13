package by.it_academy.jd2.concert.validation;

import by.it_academy.jd2.concert.validation.api.ClassifierClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Component
public class ClassifierClientImpl implements ClassifierClient {

    private final RestTemplate template;

    public ClassifierClientImpl(RestTemplate template) {
        this.template = template;
    }
    public boolean validCategory(UUID uuid) {

        HttpStatus statusCode;

        try {
            String url = "http://event-classifier-service:80/classifier/concert/category/" + uuid;
            ResponseEntity<String> res = template.getForEntity(url, String.class);
            statusCode = res.getStatusCode();
        } catch (ResourceAccessException e) {
            throw new RuntimeException("ошибка подключения к справочнику");
        }
        if (!statusCode.is2xxSuccessful()) {
            throw new EntityNotFoundException("выбранная категория отсутсвует в справочнике");
        }
        return true;
    }
}
