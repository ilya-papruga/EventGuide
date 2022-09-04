package by.it_academy.jd2.classifier.controllers;


import by.it_academy.jd2.classifier.controllers.utils.PathVariableValidator;
import by.it_academy.jd2.classifier.core.dto.country.CountryCreate;
import by.it_academy.jd2.classifier.core.dto.country.CountryRead;
import by.it_academy.jd2.classifier.core.dto.page.PageReadCountry;
import by.it_academy.jd2.classifier.service.api.ICountryService;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/classifier/country")
public class CountryController {

    private final ICountryService countryService;
    private final PathVariableValidator validator;
    private final ConversionService conversionService;

    public CountryController(ICountryService countryService, PathVariableValidator validator, ConversionService conversionService) {
        this.countryService = countryService;
        this.validator = validator;
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity<CountryRead> create(@RequestBody CountryCreate dto) {
        return new ResponseEntity<>(conversionService.convert((countryService.create(dto)), CountryRead.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageReadCountry> getFilmPage(@RequestParam(defaultValue = "1") Integer page,
                                                       @RequestParam(defaultValue = "20") Integer size) {
        PageRequest pageRequest = PageRequest.of(page-1, size);

        return ResponseEntity.ok(conversionService.convert((countryService.readPage(pageRequest)), PageReadCountry.class));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CountryRead> read(@PathVariable String uuid) {

        UUID validUUID = validator.validUUID(uuid);

        return ResponseEntity.ok(conversionService.convert((countryService.readOne(validUUID)), CountryRead.class));
    }

}