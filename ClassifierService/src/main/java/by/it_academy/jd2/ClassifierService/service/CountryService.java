package by.it_academy.jd2.ClassifierService.service;

import by.it_academy.jd2.ClassifierService.core.dao.api.ICountryDao;
import by.it_academy.jd2.ClassifierService.core.dto.country.CountryCreate;
import by.it_academy.jd2.ClassifierService.core.entity.Country;
import by.it_academy.jd2.ClassifierService.service.api.ICountryService;
import by.it_academy.jd2.ClassifierService.service.api.IMapperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CountryService implements ICountryService {

    private final ICountryDao countryDao;
    private final IMapperService mapperService;


    public CountryService(ICountryDao countryDao, IMapperService mapperService) {
        this.countryDao = countryDao;
        this.mapperService = mapperService;
    }

    @Transactional
    @Override
    public Country create(CountryCreate dto) {

        return this.countryDao.save(this.mapperService.mapCreateCountry(dto));
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
