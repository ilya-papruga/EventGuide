package by.it_academy.jd2.classifier.service;

import by.it_academy.jd2.classifier.core.dao.api.ICountryDao;
import by.it_academy.jd2.classifier.core.dto.country.CountryCreate;
import by.it_academy.jd2.classifier.core.entity.Country;
import by.it_academy.jd2.classifier.service.api.ICountryService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CountryService implements ICountryService {

    private final ICountryDao countryDao;
    private final ConversionService conversionService;

    public CountryService(ICountryDao countryDao, ConversionService conversionService) {
        this.countryDao = countryDao;
        this.conversionService = conversionService;
    }

    @Transactional
    @Override
    public Country create(CountryCreate dto) {

        return this.countryDao.save(Objects.requireNonNull(conversionService.convert(dto, Country.class)));
    }

    @Override
    public Country readOne(UUID uuid) {

        return this.countryDao
                .findById(uuid).orElseThrow(() -> {
                    throw new IllegalArgumentException("Страна не найдена");
                });
    }

    @Override
    public Page<Country> readPage(Pageable pageable) {

        return this.countryDao.findAll(pageable);
    }
}
