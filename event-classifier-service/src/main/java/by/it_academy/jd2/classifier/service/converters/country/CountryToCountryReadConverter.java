package by.it_academy.jd2.classifier.service.converters.country;

import by.it_academy.jd2.classifier.core.dto.country.CountryRead;
import by.it_academy.jd2.classifier.core.entity.Country;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CountryToCountryReadConverter implements Converter <Country, CountryRead> {

    @Override
    public CountryRead convert(Country entity) {

        CountryRead dto = new CountryRead();

        dto.setUuid(entity.getUuid());
        dto.setDtCreate(entity.getDtCreate());
        dto.setDtUpdate(entity.getDtUpdate());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());

        return dto;

    }
}
