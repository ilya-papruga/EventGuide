package by.it_academy.jd2.EventFilmService.service.converters;

import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmUpdate;
import by.it_academy.jd2.EventFilmService.core.entity.Film;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FilmUpdateToFilmConverter implements Converter <FilmUpdate, Film> {

    @Override
    public Film convert(FilmUpdate dto) {

        Film entity = new Film();

        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDtEvent(dto.getDtEvent());
        entity.setDtEndOfSale(dto.getDtEndOfSale());
        entity.setType("film");
        entity.setEventStatus(dto.getEventStatus());
        entity.setCountry(dto.getCountry());
        entity.setReleaseYear(dto.getReleaseYear());
        entity.setReleaseDate(dto.getReleaseDate());
        entity.setDuration(dto.getDuration());

        return entity;
    }
}
