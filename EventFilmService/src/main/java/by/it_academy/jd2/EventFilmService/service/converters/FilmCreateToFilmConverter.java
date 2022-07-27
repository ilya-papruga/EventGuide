package by.it_academy.jd2.EventFilmService.service.converters;

import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmCreate;
import by.it_academy.jd2.EventFilmService.core.entity.Film;
import by.it_academy.jd2.EventFilmService.core.entity.enums.EventStatus;
import by.it_academy.jd2.EventFilmService.service.UserHolder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class FilmCreateToFilmConverter implements Converter<FilmCreate, Film> {

    private final UserHolder holder;

    public FilmCreateToFilmConverter(UserHolder holder) {
        this.holder = holder;
    }

    @Override
    public Film convert(FilmCreate dto) {

        Film film = new Film();

        film.setUuid(UUID.randomUUID());
        film.setDtCreate(LocalDateTime.now());
        film.setDtUpdate(film.getDtCreate());
        film.setTitle(dto.getTitle());
        film.setDescription(dto.getDescription());
        film.setDtEvent(dto.getDtEvent());
        film.setDtEndOfSale(dto.getDtEndOfSale());
        film.setType("film");
        film.setEventStatus(EventStatus.DRAFT);
        film.setCountry(dto.getCountry());
        film.setReleaseYear(dto.getReleaseYear());
        film.setReleaseDate(dto.getReleaseDate());
        film.setDuration(dto.getDuration());
        film.setAuthor(holder.getUser().getUsername());

        return film;

    }
}
