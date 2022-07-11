package by.it_academy.jd2.EventConcertService.service.api;

import by.it_academy.jd2.EventConcertService.core.dto.flim.ConcertCreateUpdate;
import by.it_academy.jd2.EventConcertService.core.entity.Concert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IConcertService {

    Concert create (ConcertCreateUpdate dto);
    Concert readOne (UUID uuid);
    List<Concert> readAll();
    Page<Concert> getPage(Pageable pageable);
    Concert update(UUID uuid, ConcertCreateUpdate dto, LocalDateTime dtUpdate);

}
