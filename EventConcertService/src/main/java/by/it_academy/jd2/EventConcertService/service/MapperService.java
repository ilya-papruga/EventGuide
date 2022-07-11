package by.it_academy.jd2.EventConcertService.service;

import by.it_academy.jd2.EventConcertService.core.dto.flim.ConcertCreateUpdate;
import by.it_academy.jd2.EventConcertService.core.dto.page.PageRead;
import by.it_academy.jd2.EventConcertService.core.dto.flim.ConcertRead;
import by.it_academy.jd2.EventConcertService.core.entity.Concert;
import by.it_academy.jd2.EventConcertService.service.api.IMapperService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MapperService implements IMapperService {

    @Override
    public ConcertRead mapRead(Concert concert) {

        ConcertRead dto = new ConcertRead();

        dto.setUuid(concert.getUuid());
        dto.setDtCreate(concert.getDtCreate());
        dto.setDtUpdate(concert.getDtUpdate());
        dto.setTitle(concert.getTitle());
        dto.setDescription(concert.getDescription());
        dto.setDtEvent(concert.getDtEvent());
        dto.setDtEndOfSale(concert.getDtEndOfSale());
        dto.setType(concert.getType());
        dto.setEventStatus(concert.getEventStatus());
        dto.setCategory(concert.getCategory());

        return dto;
    }

    @Override
    public Concert mapCreate(ConcertCreateUpdate dto) {

        Concert concert = new Concert();

        concert.setUuid(UUID.randomUUID());
        concert.setDtCreate(LocalDateTime.now());
        concert.setDtUpdate(concert.getDtCreate());
        concert.setTitle(dto.getTitle());
        concert.setDescription(dto.getDescription());
        concert.setDtEvent(dto.getDtEvent());
        concert.setDtEndOfSale(dto.getDtEndOfSale());
        concert.setType(dto.getType());
        concert.setEventStatus(dto.getEventStatus());
        concert.setCategory(dto.getCategory());

        return concert;
    }

    @Override
    public Concert mapUpdate(ConcertCreateUpdate dto, Concert concertDB) {

        Concert concert = new Concert();

        concert.setUuid(concertDB.getUuid());
        concert.setDtCreate(concertDB.getDtCreate());
        concert.setDtUpdate(concertDB.getDtUpdate());
        concert.setTitle(dto.getTitle());
        concert.setDescription(dto.getDescription());
        concert.setDtEvent(dto.getDtEvent());
        concert.setDtEndOfSale(dto.getDtEndOfSale());
        concert.setType(dto.getType());
        concert.setEventStatus(dto.getEventStatus());
        concert.setCategory(dto.getCategory());

        return concert;
    }

    @Override
    public PageRead<ConcertRead> mapPage(Page<Concert> page) {

        PageRead<ConcertRead> dtoPage = new PageRead<>();

        List<ConcertRead> dtoContent = new ArrayList<>();

        for (Concert concert : page.getContent()) {
            dtoContent.add(mapRead(concert));
        }
        dtoPage.setNumber(page.getNumber());
        dtoPage.setSize(page.getSize());
        dtoPage.setTotalPages(page.getTotalPages());
        dtoPage.setTotalElements(page.getTotalElements());
        dtoPage.setFirst(page.isFirst());
        dtoPage.setNumberOfElements(page.getNumberOfElements());
        dtoPage.setLast(page.isLast());
        dtoPage.setContent(dtoContent);

        return dtoPage;

    }
}
