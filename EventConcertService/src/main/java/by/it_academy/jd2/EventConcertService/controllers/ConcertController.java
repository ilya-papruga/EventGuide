package by.it_academy.jd2.EventConcertService.controllers;


import by.it_academy.jd2.EventConcertService.core.dto.concert.ConcertCreate;
import by.it_academy.jd2.EventConcertService.core.dto.concert.ConcertRead;
import by.it_academy.jd2.EventConcertService.core.dto.concert.ConcertUpdate;
import by.it_academy.jd2.EventConcertService.core.dto.page.PageRead;
import by.it_academy.jd2.EventConcertService.service.api.IConcertService;
import by.it_academy.jd2.EventConcertService.service.api.IMapperService;

import by.it_academy.jd2.EventConcertService.validation.PathVariableValidator;
import by.it_academy.jd2.EventConcertService.validation.api.IPathVariableValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/event-guide/event/concert")
public class ConcertController {

    private final IConcertService concertService;
    private final IMapperService mapperService;
    private final IPathVariableValidator validator;

    public ConcertController(IConcertService concertService, IMapperService mapperService, IPathVariableValidator validator) {
        this.concertService = concertService;
        this.mapperService = mapperService;
        this.validator = validator;
    }

    @PostMapping
    public ResponseEntity<ConcertRead> create(@RequestBody ConcertCreate dto) {
        return new ResponseEntity<>(mapperService.mapRead(concertService.create(dto)), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<PageRead<ConcertRead>> getFilmPage(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "20") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return ResponseEntity.ok(mapperService.mapPage(concertService.getPage(pageRequest)));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ConcertRead> read(@PathVariable String uuid) {

        UUID validUUID = validator.validUUID(uuid);

        return ResponseEntity.ok(mapperService.mapRead(concertService.readOne(validUUID)));
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<ConcertRead> update(@PathVariable String uuid, @RequestBody ConcertUpdate dto, @PathVariable Long dt_update) {

        UUID validUUID = validator.validUUID(uuid);
        validator.validUnixTime(dt_update);

        LocalDateTime lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dt_update), ZoneId.systemDefault());

        concertService.update(validUUID, dto, lastKnowDtUpdate);

        return read(uuid);
    }


}
