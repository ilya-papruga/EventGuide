package by.it_academy.jd2.classifier.controller;


import by.it_academy.jd2.classifier.controller.utils.PathVariableValidator;
import by.it_academy.jd2.classifier.dto.category.CategoryCreate;
import by.it_academy.jd2.classifier.dto.category.CategoryRead;
import by.it_academy.jd2.classifier.dto.page.PageReadCategory;
import by.it_academy.jd2.classifier.service.api.CategoryService;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/classifier/concert/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final PathVariableValidator validator;
    private final ConversionService conversionService;

    public CategoryController(CategoryService categoryService, PathVariableValidator validator, ConversionService conversionService) {
        this.categoryService = categoryService;
        this.validator = validator;
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity<CategoryRead> create(@RequestBody CategoryCreate dto) {
        return new ResponseEntity<>(conversionService.convert((categoryService.create(dto)), CategoryRead.class), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<PageReadCategory> getFilmPage (@RequestParam(defaultValue = "1") Integer page,
                                                         @RequestParam(defaultValue = "20") Integer size)
    {
        PageRequest pageRequest = PageRequest.of(page-1,size);

        return ResponseEntity.ok(conversionService.convert((categoryService.readPage(pageRequest)), PageReadCategory.class));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CategoryRead> read(@PathVariable String uuid) {

        UUID validUUID = validator.validUUID(uuid);

        return ResponseEntity.ok(conversionService.convert((categoryService.readOne(validUUID)), CategoryRead.class));
    }


}
