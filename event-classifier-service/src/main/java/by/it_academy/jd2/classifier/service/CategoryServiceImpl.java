package by.it_academy.jd2.classifier.service;

import by.it_academy.jd2.classifier.repository.api.CategoryRepository;
import by.it_academy.jd2.classifier.dto.category.CategoryCreate;
import by.it_academy.jd2.classifier.entity.Category;
import by.it_academy.jd2.classifier.service.api.CategoryService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ConversionService conversionService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ConversionService conversionService) {
        this.categoryRepository = categoryRepository;
        this.conversionService = conversionService;
    }

    @Transactional
    @Override
    public Category create(CategoryCreate dto) {

        return this.categoryRepository.save(Objects.requireNonNull(conversionService.convert(dto, Category.class)));

    }

    @Override
    public Category readOne(UUID uuid) {

        return this.categoryRepository
                .findById(uuid).orElseThrow(() -> {
                    throw new IllegalArgumentException("Категория не найдена");
                });
    }

    @Override
    public Page<Category> readPage(Pageable pageable) {

        return this.categoryRepository.findAll(pageable);
    }

}
