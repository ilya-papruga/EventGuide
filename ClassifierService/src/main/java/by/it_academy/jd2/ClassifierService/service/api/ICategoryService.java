package by.it_academy.jd2.ClassifierService.service.api;

import by.it_academy.jd2.ClassifierService.core.dto.category.CategoryCreate;
import by.it_academy.jd2.ClassifierService.core.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ICategoryService {

    Category create (CategoryCreate dto);
    Category readOne (UUID uuid);
    Page<Category> readPage(Pageable pageable);

}
