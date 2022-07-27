package by.it_academy.jd2.EventConcertService.controllers;


import by.it_academy.jd2.EventConcertService.core.dto.concert.ConcertCreate;
import by.it_academy.jd2.EventConcertService.core.dto.concert.ConcertRead;
import by.it_academy.jd2.EventConcertService.core.dto.concert.ConcertUpdate;
import by.it_academy.jd2.EventConcertService.core.dto.page.PageRead;
import by.it_academy.jd2.EventConcertService.service.api.IConcertService;

import by.it_academy.jd2.EventConcertService.validation.api.IPathVariableValidator;
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
@RequestMapping("/api/v1/event-guide/event/concert")
public class ConcertController {

    private final IConcertService concertService;
    private final IPathVariableValidator validator;
    private final ConversionService conversionService;

    public ConcertController(IConcertService concertService, IPathVariableValidator validator, ConversionService conversionService) {
        this.concertService = concertService;
        this.validator = validator;
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity<ConcertRead> create(@RequestBody ConcertCreate dto) {
        return new ResponseEntity<>(conversionService.convert((concertService.create(dto)),ConcertRead.class), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<PageRead> getFilmPage(@RequestParam(defaultValue = "1") Integer page,
                                                @RequestParam(defaultValue = "20") Integer size) {
        PageRequest pageRequest = PageRequest.of(page-1, size);

        return ResponseEntity.ok(conversionService.convert(concertService.getPage(pageRequest), PageRead.class));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ConcertRead> read(@PathVariable String uuid) {

        UUID validUUID = validator.validUUID(uuid);

        return ResponseEntity.ok(conversionService.convert((concertService.readOne(validUUID)), ConcertRead.class));
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
