package by.it_academy.jd2.ClassifierService.service.converters.category;

import by.it_academy.jd2.ClassifierService.core.dto.category.CategoryRead;
import by.it_academy.jd2.ClassifierService.core.dto.page.PageReadCategory;
import by.it_academy.jd2.ClassifierService.core.entity.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PageCategoryToPageReadConverter implements Converter<Page<Category>, PageReadCategory> {

    private final CategoryToCategoryReadConverter categoryToCategoryReadConverter;

    public PageCategoryToPageReadConverter(CategoryToCategoryReadConverter categoryToCategoryReadConverter) {
        this.categoryToCategoryReadConverter = categoryToCategoryReadConverter;
    }

    @Override
    public PageReadCategory convert(Page<Category> page) {

        PageReadCategory dtoPage = new PageReadCategory();

        List<CategoryRead> dtoContent = new ArrayList<>();

        for (Category category : page.getContent()) {
            dtoContent.add(categoryToCategoryReadConverter.convert(category));
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
