package by.it_academy.jd2.EventFilmService.service.converters;

import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmUpdate;
import by.it_academy.jd2.EventFilmService.core.entity.Film;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FilmUpdateToFilmConverter implements Converter <FilmUpdate, Film> {

    @Override
    public Film convert(FilmUpdate dto) {

        Film film = new Film();

        film.setTitle(dto.getTitle());
        film.setDescription(dto.getDescription());
        film.setDtEvent(dto.getDtEvent());
        film.setDtEndOfSale(dto.getDtEndOfSale());
        film.setType("film");
        film.setEventStatus(dto.getEventStatus());
        film.setCountry(dto.getCountry());
        film.setReleaseYear(dto.getReleaseYear());
        film.setReleaseDate(dto.getReleaseDate());
        film.setDuration(dto.getDuration());

        return film;
    }
}
