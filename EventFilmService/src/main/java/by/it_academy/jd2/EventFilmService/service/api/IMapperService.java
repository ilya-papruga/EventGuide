package by.it_academy.jd2.EventFilmService.service.api;

import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmCreate;
import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmUpdate;
import by.it_academy.jd2.EventFilmService.core.dto.page.PageRead;
import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmRead;
import by.it_academy.jd2.EventFilmService.core.entity.Film;
import org.springframework.data.domain.Page;

public interface IMapperService {

    FilmRead mapRead(Film film);

    Film mapCreate(FilmCreate dto);

    Film mapUpdate(FilmUpdate dto, Film filmDB);

    PageRead<FilmRead> mapPage(Page<Film> page);

}
