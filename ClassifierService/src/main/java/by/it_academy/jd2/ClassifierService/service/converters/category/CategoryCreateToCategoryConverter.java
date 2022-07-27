package by.it_academy.jd2.ClassifierService.service.converters.category;

import by.it_academy.jd2.ClassifierService.core.dto.category.CategoryCreate;
import by.it_academy.jd2.ClassifierService.core.entity.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CategoryCreateToCategoryConverter implements Converter <CategoryCreate, Category> {

    @Override
    public Category convert(CategoryCreate dto) {

        Category entity = new Category();

        entity.setUuid(UUID.randomUUID());
        entity.setDtCreate(LocalDateTime.now());
        entity.setDtUpdate(entity.getDtCreate());
        entity.setTitle(dto.getTitle());

        return entity;
    }
}
