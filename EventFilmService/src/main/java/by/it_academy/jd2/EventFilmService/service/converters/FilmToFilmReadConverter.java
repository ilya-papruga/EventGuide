package by.it_academy.jd2.EventFilmService.service.converters;

import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmRead;
import by.it_academy.jd2.EventFilmService.core.entity.Film;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FilmToFilmReadConverter implements Converter<Film, FilmRead> {

    @Override
    public FilmRead convert(Film film) {

        FilmRead dto = new FilmRead();

        dto.setUuid(film.getUuid());
        dto.setDtCreate(film.getDtCreate());
        dto.setDtUpdate(film.getDtUpdate());
        dto.setTitle(film.getTitle());
        dto.setDescription(film.getDescription());
        dto.setDtEvent(film.getDtEvent());
        dto.setDtEndOfSale(film.getDtEndOfSale());
        dto.setType(film.getType());
        dto.setEventStatus(film.getEventStatus());
        dto.setCountry(film.getCountry());
        dto.setReleaseYear(film.getReleaseYear());
        dto.setReleaseDate(film.getReleaseDate());
        dto.setDuration(film.getDuration());

        return dto;
    }
}
