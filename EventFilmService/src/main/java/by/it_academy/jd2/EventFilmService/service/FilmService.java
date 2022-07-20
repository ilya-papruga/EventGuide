package by.it_academy.jd2.EventFilmService.service;

import by.it_academy.jd2.EventFilmService.core.dao.api.IFilmDao;
import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmCreateUpdate;
import by.it_academy.jd2.EventFilmService.core.entity.Film;
import by.it_academy.jd2.EventFilmService.service.api.IFilmService;
import by.it_academy.jd2.EventFilmService.service.api.IMapperService;
import by.it_academy.jd2.EventFilmService.validation.api.IHttpValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class FilmService implements IFilmService {

    private final IFilmDao filmDao;
    private final IMapperService mapperService;
    private final IHttpValidator httpValidator;

    public FilmService(IFilmDao filmDao, IMapperService mapperService, IHttpValidator httpValidator) {
        this.filmDao = filmDao;
        this.mapperService = mapperService;
        this.httpValidator = httpValidator;
    }

    @Override
    @Transactional
    public Film create(FilmCreateUpdate dto) {

        httpValidator.validCountry(dto.getCountry());
        return this.filmDao.save(this.mapperService.mapCreate(dto));
    }

    @Override
    public Film readOne(UUID uuid) {

        return this.filmDao.findById(uuid).orElseThrow(() -> {
                    throw new IllegalArgumentException("фильм не найден");
                });
    }

    @Override
    public Page<Film> getPage(Pageable pageable) {

        return this.filmDao.findAll(pageable);
    }

    @Override
    @Transactional
    public Film update(UUID uuid, FilmCreateUpdate dto, LocalDateTime dtUpdate) {

        httpValidator.validCountry(dto.getCountry());
        Film filmDB = readOne(uuid);
        if (!filmDB.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("фильм уже был обновлен кем-то ранее");
        }
        return this.filmDao.save(mapperService.mapUpdate(dto, filmDB));
    }
}
