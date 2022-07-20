package by.it_academy.jd2.EventFilmService.service;

import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmCreateUpdate;
import by.it_academy.jd2.EventFilmService.core.dto.page.PageRead;
import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmRead;
import by.it_academy.jd2.EventFilmService.core.entity.Film;
import by.it_academy.jd2.EventFilmService.core.entity.enums.EventStatus;
import by.it_academy.jd2.EventFilmService.service.api.IMapperService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MapperService implements IMapperService {

    @Override
    public FilmRead mapRead(Film film) {

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

    @Override
    public Film mapCreate(FilmCreateUpdate dto) {

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

        return film;
    }

    @Override
    public Film mapUpdate(FilmCreateUpdate dto, Film filmDB) {

        Film film = new Film();

        film.setUuid(filmDB.getUuid());
        film.setDtCreate(filmDB.getDtCreate());
        film.setDtUpdate(filmDB.getDtUpdate());
        film.setTitle(dto.getTitle());
        film.setDescription(dto.getDescription());
        film.setDtEvent(dto.getDtEvent());
        film.setDtEndOfSale(dto.getDtEndOfSale());
        film.setType("film");
        film.setEventStatus(filmDB.getEventStatus());
        film.setCountry(dto.getCountry());
        film.setReleaseYear(dto.getReleaseYear());
        film.setReleaseDate(dto.getReleaseDate());
        film.setDuration(dto.getDuration());

        return film;
    }
    @Override
    public PageRead<FilmRead> mapPage(Page<Film> page) {

        PageRead<FilmRead> dtoPage = new PageRead<>();

        List<FilmRead> dtoContent = new ArrayList<>();

        for (Film film : page.getContent()) {
            dtoContent.add(mapRead(film));
        }
        dtoPage.setNumber(page.getNumber());
        dtoPage.setSize(page.getSize());
        dtoPage.setTotalPages(page.getTotalPages());
        dtoPage.setTotalElements(page.getTotalElements());
        dtoPage.setFirst(page.isFirst());
        dtoPage.setNumberOfElements(page.getNumberOfElements());
        dtoPage.setLast(page.isLast());
        dtoPage.setContent(dtoContent);

        return dtoPage;

    }
}
