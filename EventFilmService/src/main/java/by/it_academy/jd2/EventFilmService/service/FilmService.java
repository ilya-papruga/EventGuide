package by.it_academy.jd2.EventFilmService.service;

import by.it_academy.jd2.EventFilmService.core.dao.api.IFilmDao;
import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmCreate;
import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmUpdate;
import by.it_academy.jd2.EventFilmService.core.entity.Film;
import by.it_academy.jd2.EventFilmService.core.entity.enums.EventStatus;
import by.it_academy.jd2.EventFilmService.service.api.IFilmService;
import by.it_academy.jd2.EventFilmService.service.api.IMapperService;
import by.it_academy.jd2.EventFilmService.validation.api.IHttpValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final UserHolder holder;

    public FilmService(IFilmDao filmDao, IMapperService mapperService, IHttpValidator httpValidator, UserHolder holder) {
        this.filmDao = filmDao;
        this.mapperService = mapperService;
        this.httpValidator = httpValidator;
        this.holder = holder;
    }

    @Override
    @Transactional
    public Film create(FilmCreate dto) {

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

        if (holder.isAuthenticated()) {

            UserDetails user = holder.getUser();

            if (user.getAuthorities().size() > 1) {
                return this.filmDao.findAll(pageable);
            } else {
                return this.filmDao.findByEventStatusOrAuthor(EventStatus.PUBLISHED, user.getUsername(), pageable);
            }
        } else {
            return this.filmDao.findByEventStatus(EventStatus.PUBLISHED, pageable);
        }

    }

    @Override
    @Transactional
    public Film update(UUID uuid, FilmUpdate dto, LocalDateTime dtUpdate) {

        httpValidator.validCountry(dto.getCountry());
        Film filmDB = readOne(uuid);
        if (!filmDB.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("фильм уже был обновлен кем-то ранее");
        }
        return this.filmDao.save(mapperService.mapUpdate(dto, filmDB));
    }
}
