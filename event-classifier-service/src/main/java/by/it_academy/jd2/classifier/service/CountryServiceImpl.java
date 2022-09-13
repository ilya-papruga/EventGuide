package by.it_academy.jd2.classifier.service;

import by.it_academy.jd2.classifier.repository.api.CountryRepository;
import by.it_academy.jd2.classifier.dto.country.CountryCreate;
import by.it_academy.jd2.classifier.entity.Country;
import by.it_academy.jd2.classifier.service.api.CountryService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final ConversionService conversionService;

    public CountryServiceImpl(CountryRepository countryRepository, ConversionService conversionService) {
        this.countryRepository = countryRepository;
        this.conversionService = conversionService;
    }

    @Transactional
    @Override
    public Country create(CountryCreate dto) {

        return this.countryRepository.save(Objects.requireNonNull(conversionService.convert(dto, Country.class)));
    }

    @Override
    public Country readOne(UUID uuid) {

        return this.countryRepository
                .findById(uuid).orElseThrow(() -> {
                    throw new IllegalArgumentException("Страна не найдена");
                });
    }

    @Override
    public Page<Country> readPage(Pageable pageable) {

        return this.countryRepository.findAll(pageable);
    }
}
