package by.it_academy.jd2.classifier.service.converters.category;

import by.it_academy.jd2.classifier.dto.category.CategoryRead;
import by.it_academy.jd2.classifier.entity.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryReadConverter implements Converter <Category, CategoryRead> {

    @Override
    public CategoryRead convert(Category entity) {

        CategoryRead dto = new CategoryRead();

        dto.setUuid(entity.getUuid());
        dto.setDtCreate(entity.getDtCreate());
        dto.setDtUpdate(entity.getDtUpdate());
        dto.setTitle(entity.getTitle());

        return dto;

    }
}
