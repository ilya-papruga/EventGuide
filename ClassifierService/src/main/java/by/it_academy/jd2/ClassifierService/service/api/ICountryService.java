package by.it_academy.jd2.ClassifierService.service.api;

import by.it_academy.jd2.ClassifierService.core.dto.country.CountryCreate;
import by.it_academy.jd2.ClassifierService.core.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ICountryService {

    Country create (CountryCreate dto);
    Country readOne (UUID uuid);
    Page<Country> readPage(Pageable pageable);

}
