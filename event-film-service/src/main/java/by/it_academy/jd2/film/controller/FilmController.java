package by.it_academy.jd2.film.controller;


import by.it_academy.jd2.film.dto.flim.FilmCreate;
import by.it_academy.jd2.film.dto.flim.FilmRead;
import by.it_academy.jd2.film.dto.flim.FilmUpdate;
import by.it_academy.jd2.film.dto.page.PageRead;
import by.it_academy.jd2.film.service.api.FilmService;

import by.it_academy.jd2.film.validation.api.PathVariableValidator;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/event-guide/event/film")
public class FilmController {

    private final FilmService filmService;
    private final PathVariableValidator validator;
    private final ConversionService conversionService;

    public FilmController(FilmService filmService, PathVariableValidator validator, ConversionService conversionService) {
        this.filmService = filmService;
        this.validator = validator;
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity<FilmRead> create(@RequestBody FilmCreate dto) {
        return new ResponseEntity<>(conversionService.convert((filmService.create(dto)), FilmRead.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageRead> getFilmPage (@RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(defaultValue = "20") Integer size)
    {
        PageRequest pageRequest = PageRequest.of(page-1,size);

        return ResponseEntity.ok(conversionService.convert((filmService.getPage(pageRequest)), PageRead.class));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<FilmRead> read(@PathVariable String uuid) {

        UUID validUUID = validator.validUUID(uuid);

        return ResponseEntity.ok(conversionService.convert(filmService.readOne(validUUID), FilmRead.class));
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity <FilmRead> update(@PathVariable String uuid, @RequestBody FilmUpdate dto, @PathVariable Long dt_update) {

        UUID validUUID = validator.validUUID(uuid);
        validator.validUnixTime(dt_update);

        LocalDateTime lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dt_update), ZoneId.systemDefault());

        filmService.update(validUUID, dto, lastKnowDtUpdate);

        return read(uuid);
    }


}
