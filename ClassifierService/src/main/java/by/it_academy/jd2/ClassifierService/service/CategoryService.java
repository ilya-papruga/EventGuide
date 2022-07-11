package by.it_academy.jd2.ClassifierService.service;

import by.it_academy.jd2.ClassifierService.core.dao.api.ICategoryDao;
import by.it_academy.jd2.ClassifierService.core.dto.category.CategoryCreate;
import by.it_academy.jd2.ClassifierService.core.entity.Category;
import by.it_academy.jd2.ClassifierService.service.api.ICategoryService;
import by.it_academy.jd2.ClassifierService.service.api.IMapperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryService implements ICategoryService {

    private final ICategoryDao categoryDao;
    private final IMapperService mapperService;


    public CategoryService(ICategoryDao categoryDao, IMapperService mapperService) {
        this.categoryDao = categoryDao;
        this.mapperService = mapperService;
    }

    @Override
    public Category create(CategoryCreate dto) {

        if (dto.getTitle() == null) {
            throw new IllegalArgumentException("Поле title не может быть пустым");
        }

        return this.categoryDao.save(this.mapperService.mapCreateCategory(dto));

    }

    @Override
    public Category readOne(UUID uuid) {

        if (uuid == null || uuid.toString().isEmpty()) {
            throw new IllegalArgumentException("Поле uuid не может быть пустым");
        }

        return this.categoryDao
                .findById(uuid)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Категория не найдена");
                });
    }

    @Override
    public Page<Category> readPage(Pageable pageable) {

        return this.categoryDao.findAll(pageable);
    }

}
