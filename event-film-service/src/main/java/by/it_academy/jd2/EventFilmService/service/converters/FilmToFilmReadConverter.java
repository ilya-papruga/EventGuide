package by.it_academy.jd2.EventFilmService.service.converters;

import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmRead;
import by.it_academy.jd2.EventFilmService.core.entity.Film;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FilmToFilmReadConverter implements Converter<Film, FilmRead> {

    @Override
    public FilmRead convert(Film entity) {

        FilmRead dto = new FilmRead();

        dto.setUuid(entity.getUuid());
        dto.setDtCreate(entity.getDtCreate());
        dto.setDtUpdate(entity.getDtUpdate());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setDtEvent(entity.getDtEvent());
        dto.setDtEndOfSale(entity.getDtEndOfSale());
        dto.setType(entity.getType());
        dto.setEventStatus(entity.getEventStatus());
        dto.setCountry(entity.getCountry());
        dto.setReleaseYear(entity.getReleaseYear());
        dto.setReleaseDate(entity.getReleaseDate());
        dto.setDuration(entity.getDuration());

        return dto;
    }
}
