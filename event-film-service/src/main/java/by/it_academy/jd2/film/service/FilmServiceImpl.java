package by.it_academy.jd2.film.service;

import by.it_academy.jd2.film.repository.api.FilmRepository;
import by.it_academy.jd2.film.dto.flim.FilmCreate;
import by.it_academy.jd2.film.dto.flim.FilmUpdate;
import by.it_academy.jd2.film.entity.Film;
import by.it_academy.jd2.film.entity.enums.EventStatus;
import by.it_academy.jd2.film.service.api.FilmService;
import by.it_academy.jd2.film.validation.api.ClassifierClient;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final ClassifierClient client;
    private final UserHolder holder;
    private final ConversionService conversionService;

    public FilmServiceImpl(FilmRepository filmRepository, ClassifierClient client, UserHolder holder, ConversionService conversionService) {
        this.filmRepository = filmRepository;
        this.client = client;
        this.holder = holder;
        this.conversionService = conversionService;
    }

    @Override
    @Transactional
    public Film create(FilmCreate dto) {

        client.validCountry(dto.getCountry());
        return this.filmRepository.save(Objects.requireNonNull(conversionService.convert(dto, Film.class)));
    }

    @Override
    public Film readOne(UUID uuid) {

        return this.filmRepository.findById(uuid).orElseThrow(() -> {
            throw new IllegalArgumentException("фильм не найден");
        });
    }

    @Override
    public Page<Film> getPage(Pageable pageable) {

        if (holder.isAuthenticated()) {

            UserDetails user = holder.getUser();

            if (user.getAuthorities().size() > 1) {
                return this.filmRepository.findAll(pageable);
            } else {
                return this.filmRepository.findByEventStatusOrAuthor(EventStatus.PUBLISHED, user.getUsername(), pageable);
            }
        } else {
            return this.filmRepository.findByEventStatus(EventStatus.PUBLISHED, pageable);
        }
    }

    @Override
    @Transactional
    public Film update(UUID uuid, FilmUpdate dto, LocalDateTime dtUpdate) {

        client.validCountry(dto.getCountry());

        Film film = conversionService.convert(dto, Film.class);

        Film filmDB = readOne(uuid);

        film.setUuid(filmDB.getUuid());
        film.setDtCreate(filmDB.getDtCreate());
        film.setDtUpdate(filmDB.getDtUpdate());
        film.setAuthor(filmDB.getAuthor());

        if (!film.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("фильм уже был обновлен кем-то ранее");
        }
        return this.filmRepository.save(film);
    }
}
