package by.it_academy.jd2.classifier.service.api;

import by.it_academy.jd2.classifier.dto.category.CategoryCreate;
import by.it_academy.jd2.classifier.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.UUID;
@Validated
public interface CategoryService {

    Category create (@Valid CategoryCreate dto);
    Category readOne (UUID uuid);
    Page<Category> readPage(Pageable pageable);

}
