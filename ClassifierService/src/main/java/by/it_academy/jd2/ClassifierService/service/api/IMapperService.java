package by.it_academy.jd2.ClassifierService.service.api;

import by.it_academy.jd2.ClassifierService.core.dto.category.CategoryCreate;
import by.it_academy.jd2.ClassifierService.core.dto.category.CategoryRead;
import by.it_academy.jd2.ClassifierService.core.dto.country.CountryCreate;
import by.it_academy.jd2.ClassifierService.core.dto.page.PageRead;
import by.it_academy.jd2.ClassifierService.core.dto.country.CountryRead;
import by.it_academy.jd2.ClassifierService.core.entity.Category;
import by.it_academy.jd2.ClassifierService.core.entity.Country;
import org.springframework.data.domain.Page;

public interface IMapperService {

    CountryRead mapReadCountry(Country country);
    Country mapCreateCountry(CountryCreate dto);
    PageRead<CountryRead> mapPageCountry(Page<Country> page);

    CategoryRead mapReadCategory(Category category);

    Category mapCreateCategory(CategoryCreate dto);

    PageRead<CategoryRead> mapPageCategory(Page<Category> page);

}
