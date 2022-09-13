package by.it_academy.jd2.concert.service.api;

import by.it_academy.jd2.concert.dto.concert.ConcertCreate;
import by.it_academy.jd2.concert.dto.concert.ConcertUpdate;
import by.it_academy.jd2.concert.entity.Concert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;


import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;
@Validated
public interface ConcertService {

    Concert create (@Valid ConcertCreate dto);
    Concert readOne (UUID uuid);
    Page<Concert> getPage(Pageable pageable);
    Concert update(UUID uuid, @Valid ConcertUpdate dto, LocalDateTime dtUpdate);

}
