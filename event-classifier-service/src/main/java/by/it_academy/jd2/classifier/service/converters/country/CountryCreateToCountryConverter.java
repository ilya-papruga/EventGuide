package by.it_academy.jd2.classifier.service.converters.country;

import by.it_academy.jd2.classifier.dto.country.CountryCreate;
import by.it_academy.jd2.classifier.entity.Country;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CountryCreateToCountryConverter implements Converter<CountryCreate, Country> {

    @Override
    public Country convert(CountryCreate dto) {

        Country entity = new Country();

        entity.setUuid(UUID.randomUUID());
        entity.setDtCreate(LocalDateTime.now());
        entity.setDtUpdate(entity.getDtCreate());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());

        return entity;

    }
}

