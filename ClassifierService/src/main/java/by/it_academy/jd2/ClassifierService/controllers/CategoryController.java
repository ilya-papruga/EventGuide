package by.it_academy.jd2.ClassifierService.controllers;


import by.it_academy.jd2.ClassifierService.controllers.utils.PathVariableValidator;
import by.it_academy.jd2.ClassifierService.core.dto.category.CategoryCreate;
import by.it_academy.jd2.ClassifierService.core.dto.category.CategoryRead;
import by.it_academy.jd2.ClassifierService.core.dto.page.PageRead;
import by.it_academy.jd2.ClassifierService.service.api.ICategoryService;

import by.it_academy.jd2.ClassifierService.service.api.IMapperService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/classifier/concert/category")
public class CategoryController {

    private final ICategoryService categoryService;
    private final IMapperService mapperService;

    private final PathVariableValidator validator;

    public CategoryController(ICategoryService categoryService, IMapperService mapperService, PathVariableValidator validator) {
        this.categoryService = categoryService;
        this.mapperService = mapperService;
        this.validator = validator;
    }

    @PostMapping
    public ResponseEntity<CategoryRead> create(@RequestBody CategoryCreate dto) {
        return new ResponseEntity<>(mapperService.mapReadCategory(categoryService.create(dto)), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<PageRead<CategoryRead>> getFilmPage (@RequestParam(defaultValue = "0") Integer page,
                                                              @RequestParam(defaultValue = "20") Integer size)
    {
        PageRequest pageRequest = PageRequest.of(page,size);

        return ResponseEntity.ok(mapperService.mapPageCategory(categoryService.readPage(pageRequest)));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CategoryRead> read(@PathVariable String uuid) {

        UUID validUUID = validator.validUUID(uuid);

        return ResponseEntity.ok(mapperService.mapReadCategory(categoryService.readOne(validUUID)));
    }


}
