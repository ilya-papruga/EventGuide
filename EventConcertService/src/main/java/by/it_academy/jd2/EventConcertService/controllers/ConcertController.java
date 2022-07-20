package by.it_academy.jd2.EventConcertService.controllers;


import by.it_academy.jd2.EventConcertService.core.dto.concert.ConcertCreateUpdate;
import by.it_academy.jd2.EventConcertService.core.dto.concert.ConcertRead;
import by.it_academy.jd2.EventConcertService.core.dto.page.PageRead;
import by.it_academy.jd2.EventConcertService.service.api.IConcertService;
import by.it_academy.jd2.EventConcertService.service.api.IMapperService;

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

    public ConcertController(IConcertService concertService, IMapperService mapperService) {
        this.concertService = concertService;
        this.mapperService = mapperService;
    }

    @PostMapping
    public ResponseEntity<ConcertRead> create(@RequestBody ConcertCreateUpdate dto) {
        return new ResponseEntity<>(mapperService.mapRead(concertService.create(dto)), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<PageRead<ConcertRead>> getFilmPage (@RequestParam(defaultValue = "0") Integer page,
                                                              @RequestParam(defaultValue = "20") Integer size)
    {
        PageRequest pageRequest = PageRequest.of(page,size);

        return ResponseEntity.ok(mapperService.mapPage(concertService.getPage(pageRequest)));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ConcertRead> read(@PathVariable UUID uuid) {

        return ResponseEntity.ok(mapperService.mapRead(concertService.readOne(uuid)));
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity <ConcertRead> update(@PathVariable UUID uuid, @RequestBody ConcertCreateUpdate dto, @PathVariable Long dt_update) {
        LocalDateTime lastKnowDtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dt_update), ZoneId.systemDefault());

        concertService.update(uuid, dto, lastKnowDtUpdate);

        return read(uuid);
    }


}
