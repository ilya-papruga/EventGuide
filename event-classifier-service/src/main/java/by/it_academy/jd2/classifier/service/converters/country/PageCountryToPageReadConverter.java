package by.it_academy.jd2.classifier.service.converters.country;

import by.it_academy.jd2.classifier.core.dto.country.CountryRead;
import by.it_academy.jd2.classifier.core.dto.page.PageReadCountry;
import by.it_academy.jd2.classifier.core.entity.Country;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PageCountryToPageReadConverter implements Converter<Page<Country>, PageReadCountry> {

    private final CountryToCountryReadConverter countryToCountryReadConverter;

    public PageCountryToPageReadConverter(CountryToCountryReadConverter countryToCountryReadConverter) {
        this.countryToCountryReadConverter = countryToCountryReadConverter;
    }

    @Override
    public PageReadCountry convert(Page<Country> page) {

        PageReadCountry dtoPage = new PageReadCountry();

        List<CountryRead> dtoContent = new ArrayList<>();

        for (Country country : page.getContent()) {
            dtoContent.add(countryToCountryReadConverter.convert(country));
        }
        dtoPage.setNumber(page.getNumber()+1);
        dtoPage.setSize(page.getSize());
        dtoPage.setTotalPages(page.getTotalPages());
        dtoPage.setTotalElements(page.getTotalElements());
        dtoPage.setFirst(page.isFirst());
        dtoPage.setNumberOfElements(page.getNumberOfElements());
        dtoPage.setLast(page.isLast());
        dtoPage.setContent(dtoContent);

        return dtoPage;

    }
}
