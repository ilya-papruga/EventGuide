package by.it_academy.jd2.EventConcertService.service.api;

import by.it_academy.jd2.EventConcertService.core.dto.concert.ConcertCreateUpdate;
import by.it_academy.jd2.EventConcertService.core.entity.Concert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;


import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;
@Validated
public interface IConcertService {

    Concert create (@Valid ConcertCreateUpdate dto);
    Concert readOne (UUID uuid);
    Page<Concert> getPage(Pageable pageable);
    Concert update(UUID uuid,@Valid ConcertCreateUpdate dto, LocalDateTime dtUpdate);

}
