package by.it_academy.jd2.ClassifierService.controllers;


import by.it_academy.jd2.ClassifierService.core.dto.country.CountryCreate;
import by.it_academy.jd2.ClassifierService.core.dto.country.CountryRead;
import by.it_academy.jd2.ClassifierService.core.dto.page.PageRead;
import by.it_academy.jd2.ClassifierService.service.api.ICountryService;
import by.it_academy.jd2.ClassifierService.service.api.IMapperService;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/classifier/country")
public class CountryController {

    private final ICountryService countryService;
    private final IMapperService mapperService;

    public CountryController(ICountryService countryService, IMapperService mapperService) {
        this.countryService = countryService;
        this.mapperService = mapperService;
    }

    @PostMapping
    public ResponseEntity<CountryRead> create(@RequestBody CountryCreate dto) {
        return new ResponseEntity<>(mapperService.mapReadCountry(countryService.create(dto)), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<PageRead<CountryRead>> getFilmPage (@RequestParam(defaultValue = "0") Integer page,
                                                              @RequestParam(defaultValue = "20") Integer size)
    {
        PageRequest pageRequest = PageRequest.of(page,size);

        return ResponseEntity.ok(mapperService.mapPageCountry(countryService.readPage(pageRequest)));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CountryRead> read(@PathVariable UUID uuid) {

        return ResponseEntity.ok(mapperService.mapReadCountry(countryService.readOne(uuid)));
    }


}
