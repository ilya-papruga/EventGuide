package by.it_academy.jd2.EventFilmService.service.api;

import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmCreate;
import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmUpdate;
import by.it_academy.jd2.EventFilmService.core.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;


import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;
@Validated
public interface IFilmService {

    Film create (@Valid FilmCreate dto);
    Film readOne (UUID uuid);
    Page<Film> getPage(Pageable pageable);
    Film update(UUID uuid, @Valid FilmUpdate dto, LocalDateTime dtUpdate);

}
