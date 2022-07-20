package by.it_academy.jd2.EventConcertService.service.api;

import by.it_academy.jd2.EventConcertService.core.dto.concert.ConcertCreateUpdate;
import by.it_academy.jd2.EventConcertService.core.dto.page.PageRead;
import by.it_academy.jd2.EventConcertService.core.dto.concert.ConcertRead;
import by.it_academy.jd2.EventConcertService.core.entity.Concert;
import org.springframework.data.domain.Page;

public interface IMapperService {

    ConcertRead mapRead(Concert concert);

    Concert mapCreate(ConcertCreateUpdate dto);

    Concert mapUpdate(ConcertCreateUpdate dto, Concert concertDB);

    PageRead<ConcertRead> mapPage(Page<Concert> page);

}
