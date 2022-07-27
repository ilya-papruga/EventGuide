package by.it_academy.jd2.ClassifierService.service;

import by.it_academy.jd2.ClassifierService.core.dao.api.ICategoryDao;
import by.it_academy.jd2.ClassifierService.core.dto.category.CategoryCreate;
import by.it_academy.jd2.ClassifierService.core.entity.Category;
import by.it_academy.jd2.ClassifierService.service.api.ICategoryService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CategoryService implements ICategoryService {

    private final ICategoryDao categoryDao;
    private final ConversionService conversionService;

    public CategoryService(ICategoryDao categoryDao, ConversionService conversionService) {
        this.categoryDao = categoryDao;
        this.conversionService = conversionService;
    }

    @Transactional
    @Override
    public Category create(CategoryCreate dto) {

        return this.categoryDao.save(Objects.requireNonNull(conversionService.convert(dto, Category.class)));

    }

    @Override
    public Category readOne(UUID uuid) {

        return this.categoryDao
                .findById(uuid).orElseThrow(() -> {
                    throw new IllegalArgumentException("Категория не найдена");
                });
    }

    @Override
    public Page<Category> readPage(Pageable pageable) {

        return this.categoryDao.findAll(pageable);
    }

}
