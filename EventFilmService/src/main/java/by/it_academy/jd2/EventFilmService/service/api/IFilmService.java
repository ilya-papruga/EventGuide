package by.it_academy.jd2.EventFilmService.service.api;

import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmCreateUpdate;
import by.it_academy.jd2.EventFilmService.core.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IFilmService {

    Film create (FilmCreateUpdate dto);
    Film readOne (UUID uuid);
    List<Film> readAll();
    Page<Film> getPage(Pageable pageable);
    Film update(UUID uuid, FilmCreateUpdate dto, LocalDateTime dtUpdate);

}
