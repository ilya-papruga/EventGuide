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

        Film entity = new Film();

        entity.setUuid(UUID.randomUUID());
        entity.setDtCreate(LocalDateTime.now());
        entity.setDtUpdate(entity.getDtCreate());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDtEvent(dto.getDtEvent());
        entity.setDtEndOfSale(dto.getDtEndOfSale());
        entity.setType("film");
        entity.setEventStatus(EventStatus.DRAFT);
        entity.setCountry(dto.getCountry());
        entity.setReleaseYear(dto.getReleaseYear());
        entity.setReleaseDate(dto.getReleaseDate());
        entity.setDuration(dto.getDuration());
        entity.setAuthor(holder.getUser().getUsername());

        return entity;

    }
}
