package by.it_academy.jd2.ClassifierService.service;

import by.it_academy.jd2.ClassifierService.core.dto.category.CategoryCreate;
import by.it_academy.jd2.ClassifierService.core.dto.category.CategoryRead;
import by.it_academy.jd2.ClassifierService.core.dto.country.CountryCreate;
import by.it_academy.jd2.ClassifierService.core.dto.page.PageRead;
import by.it_academy.jd2.ClassifierService.core.dto.country.CountryRead;
import by.it_academy.jd2.ClassifierService.core.entity.Category;
import by.it_academy.jd2.ClassifierService.core.entity.Country;
import by.it_academy.jd2.ClassifierService.service.api.IMapperService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MapperService implements IMapperService {

    @Override
    public CountryRead mapReadCountry(Country country) {

        CountryRead dto = new CountryRead();

        dto.setUuid(country.getUuid());
        dto.setDtCreate(country.getDtCreate());
        dto.setDtUpdate(country.getDtUpdate());
        dto.setTitle(country.getTitle());
        dto.setDescription(country.getDescription());

        return dto;
    }

    @Override
    public Country mapCreateCountry(CountryCreate dto) {

        Country country = new Country();

        country.setUuid(UUID.randomUUID());
        country.setDtCreate(LocalDateTime.now());
        country.setDtUpdate(country.getDtCreate());
        country.setTitle(dto.getTitle());
        country.setDescription(dto.getDescription());

        return country;
    }


    @Override
    public PageRead<CountryRead> mapPageCountry(Page<Country> page) {

        PageRead<CountryRead> dtoPage = new PageRead<>();

        List<CountryRead> dtoContent = new ArrayList<>();

        for (Country country : page.getContent()) {
            dtoContent.add(mapReadCountry(country));
        }
        dtoPage.setNumber(page.getNumber());
        dtoPage.setSize(page.getSize());
        dtoPage.setTotalPages(page.getTotalPages());
        dtoPage.setTotalElements(page.getTotalElements());
        dtoPage.setFirst(page.isFirst());
        dtoPage.setNumberOfElements(page.getNumberOfElements());
        dtoPage.setLast(page.isLast());
        dtoPage.setContent(dtoContent);

        return dtoPage;

    }

    @Override
    public CategoryRead mapReadCategory(Category category) {

        CategoryRead dto = new CategoryRead();

        dto.setUuid(category.getUuid());
        dto.setDtCreate(category.getDtCreate());
        dto.setDtUpdate(category.getDtUpdate());
        dto.setTitle(category.getTitle());

        return dto;
    }

    @Override
    public Category mapCreateCategory(CategoryCreate dto) {

        Category category = new Category();

        category.setUuid(UUID.randomUUID());
        category.setDtCreate(LocalDateTime.now());
        category.setDtUpdate(category.getDtCreate());
        category.setTitle(dto.getTitle());

        return category;

    }

    @Override
    public PageRead<CategoryRead> mapPageCategory(Page<Category> page) {

        PageRead<CategoryRead> dtoPage = new PageRead<>();

        List<CategoryRead> dtoContent = new ArrayList<>();

        for (Category category : page.getContent()) {
            dtoContent.add(mapReadCategory(category));
        }
        dtoPage.setNumber(page.getNumber());
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
