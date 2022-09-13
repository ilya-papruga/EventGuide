package by.it_academy.jd2.film.service.api;

import by.it_academy.jd2.film.dto.flim.FilmCreate;
import by.it_academy.jd2.film.dto.flim.FilmUpdate;
import by.it_academy.jd2.film.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;


import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;
@Validated
public interface FilmService {

    Film create (@Valid FilmCreate dto);
    Film readOne (UUID uuid);
    Page<Film> getPage(Pageable pageable);
    Film update(UUID uuid, @Valid FilmUpdate dto, LocalDateTime dtUpdate);

}
