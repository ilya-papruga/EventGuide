package by.it_academy.jd2.classifier.service.api;

import by.it_academy.jd2.classifier.dto.country.CountryCreate;
import by.it_academy.jd2.classifier.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.UUID;
@Validated
public interface CountryService {

    Country create (@Valid CountryCreate dto);
    Country readOne (UUID uuid);
    Page<Country> readPage(Pageable pageable);

}
