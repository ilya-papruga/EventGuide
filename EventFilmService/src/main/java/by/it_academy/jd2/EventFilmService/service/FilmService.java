package by.it_academy.jd2.EventFilmService.service;

import by.it_academy.jd2.EventFilmService.core.dao.api.IFilmDao;
import by.it_academy.jd2.EventFilmService.core.dto.flim.FilmCreateUpdate;
import by.it_academy.jd2.EventFilmService.core.entity.Film;
import by.it_academy.jd2.EventFilmService.service.api.IFilmService;
import by.it_academy.jd2.EventFilmService.service.api.IMapperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FilmService implements IFilmService {

    private final IFilmDao filmDao;
    private final IMapperService mapperService;


    public FilmService(IFilmDao filmDao, IMapperService mapperService) {
        this.filmDao = filmDao;
        this.mapperService = mapperService;
    }

    @Override
    public Film create(FilmCreateUpdate dto) {

        if (dto.getTitle() == null || dto.getDescription() == null || dto.getDtEvent() == null ||
                dto.getDtEndOfSale() == null || dto.getType() == null || dto.getEventStatus() == null ||
                dto.getCountry() == null || dto.getReleaseYear() == null || dto.getReleaseDate() == null ||
                dto.getDuration() == null
        ) {
            throw new IllegalArgumentException("Для создания необходимо заполнить все поля");
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/api/v1/classifier/country/" + dto.getCountry();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("Выбранная страна отсутсвует в справочнике");
        }

        return this.filmDao.save(this.mapperService.mapCreate(dto));
    }

    @Override
    public Film readOne(UUID uuid) {

        if (uuid == null || uuid.toString().isEmpty()) {
            throw new IllegalArgumentException("Поле uuid не может быть пустым");
        }

        return this.filmDao
                .findById(uuid)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Не нашли такой фильм");
                });
    }

    @Override
    public List<Film> readAll() {
        return this.filmDao.findAll();
    }

    @Override
    public Page<Film> getPage(Pageable pageable) {

        return this.filmDao.findAll(pageable);
    }

    @Override
    public Film update(UUID uuid, FilmCreateUpdate dto, LocalDateTime dtUpdate) {

        if (uuid == null || uuid.toString().isEmpty()) {
            throw new IllegalArgumentException("Поле uuid не может быть пустым");
        }

        if (dto.getTitle() == null || dto.getDescription() == null || dto.getDtEvent() == null ||
                dto.getDtEndOfSale() == null || dto.getType() == null || dto.getEventStatus() == null ||
                dto.getCountry() == null || dto.getReleaseYear() == null || dto.getReleaseDate() == null ||
                dto.getDuration() == null
        ) {
            throw new IllegalArgumentException("Для обновления необходимо заполнить все поля");
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/api/v1/classifier/country/" + dto.getCountry();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("Выбранная страна отсутсвует в справочнике");
        }

        Film filmDB = this.readOne(uuid);

        if (!filmDB.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("Фильм уже был обновлен кем-то ранее");
        }

        Film film = mapperService.mapUpdate(dto, filmDB);

       return this.filmDao.save(film);
    }
}
