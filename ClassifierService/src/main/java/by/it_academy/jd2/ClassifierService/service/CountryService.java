package by.it_academy.jd2.ClassifierService.service;

import by.it_academy.jd2.ClassifierService.core.dao.api.ICountryDao;
import by.it_academy.jd2.ClassifierService.core.dto.country.CountryCreate;
import by.it_academy.jd2.ClassifierService.core.entity.Country;
import by.it_academy.jd2.ClassifierService.service.api.ICountryService;
import by.it_academy.jd2.ClassifierService.service.api.IMapperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CountryService implements ICountryService {

    private final ICountryDao countryDao;
    private final IMapperService mapperService;


    public CountryService(ICountryDao countryDao, IMapperService mapperService) {
        this.countryDao = countryDao;
        this.mapperService = mapperService;
    }

    @Override
    public Country create(CountryCreate dto) {

        if (dto.getTitle() == null || dto.getDescription() == null) {
            throw new IllegalArgumentException("Для создания необходимо заполнить все поля");
        }

        return this.countryDao.save(this.mapperService.mapCreateCountry(dto));
    }

    @Override
    public Country readOne(UUID uuid) {

        if (uuid == null || uuid.toString().isEmpty()) {
            throw new IllegalArgumentException("Поле uuid не может быть пустым");
        }

        return this.countryDao
                .findById(uuid)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Не нашли такой фильм");
                });
    }

    @Override
    public Page<Country> readPage(Pageable pageable) {

        return this.countryDao.findAll(pageable);
    }
}
