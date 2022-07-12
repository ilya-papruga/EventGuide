package by.it_academy.jd2.EventFilmService.service.api;

import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmCreateUpdate;
import by.it_academy.jd2.EventFilmService.core.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Validated
public interface IFilmService {

    Film create (@Valid FilmCreateUpdate dto);
    Film readOne (UUID uuid);
    Page<Film> getPage(Pageable pageable);
    Film update(UUID uuid, @Valid FilmCreateUpdate dto, LocalDateTime dtUpdate);

}
