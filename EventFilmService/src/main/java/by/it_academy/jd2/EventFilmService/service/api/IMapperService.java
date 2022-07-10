package by.it_academy.jd2.EventFilmService.service.api;

import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmCreateUpdate;
import by.it_academy.jd2.EventFilmService.core.dto.page.PageRead;
import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmRead;
import by.it_academy.jd2.EventFilmService.core.entity.Film;
import org.springframework.data.domain.Page;

public interface IMapperService {

    FilmRead mapRead(Film film);

    Film mapCreate(FilmCreateUpdate dto);

    Film mapUpdate(FilmCreateUpdate dto, Film filmDB);

    PageRead<FilmRead> mapPage(Page<Film> page);

}
