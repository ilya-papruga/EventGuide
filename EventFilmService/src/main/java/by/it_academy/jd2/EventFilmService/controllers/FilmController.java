package by.it_academy.jd2.EventFilmService.controllers;


import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmCreateUpdate;
import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmRead;
import by.it_academy.jd2.EventFilmService.core.dto.page.PageRead;
import by.it_academy.jd2.EventFilmService.service.api.IFilmService;
import by.it_academy.jd2.EventFilmService.service.api.IMapperService;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/event-guide/event/film")
public class FilmController {

    private final IFilmService filmService;
    private final IMapperService mapperService;

    public FilmController(IFilmService filmService, IMapperService mapperService) {
        this.filmService = filmService;
        this.mapperService = mapperService;
    }

    @PostMapping
    public ResponseEntity<FilmRead> create(@RequestBody FilmCreateUpdate dto) {
        return new ResponseEntity<>(mapperService.mapRead(filmService.create(dto)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageRead<FilmRead>> getFilmPage (@RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "20") Integer size)
    {
        PageRequest pageRequest = PageRequest.of(page,size);
        return ResponseEntity.ok(mapperService.mapPage(filmService.getPage(pageRequest)));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<FilmRead> read(@PathVariable UUID uuid) {

        return ResponseEntity.ok(mapperService.mapRead(filmService.readOne(uuid)));
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity <FilmRead> update(@PathVariable UUID uuid, @RequestBody FilmCreateUpdate dto, @PathVariable Long dt_update) {
        LocalDateTime lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dt_update), ZoneId.systemDefault());

        filmService.update(uuid, dto, lastKnowDtUpdate);

        return read(uuid);
    }


}
