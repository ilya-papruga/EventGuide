package by.it_academy.jd2.EventConcertService.service;

import by.it_academy.jd2.EventConcertService.core.dao.api.IConcertDao;
import by.it_academy.jd2.EventConcertService.core.dto.flim.ConcertCreateUpdate;
import by.it_academy.jd2.EventConcertService.core.entity.Concert;
import by.it_academy.jd2.EventConcertService.service.api.IConcertService;
import by.it_academy.jd2.EventConcertService.service.api.IMapperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ConcertService implements IConcertService {

    private final IConcertDao concertDao;
    private final IMapperService mapperService;


    public ConcertService(IConcertDao concertDao, IMapperService mapperService) {
        this.concertDao = concertDao;
        this.mapperService = mapperService;
    }

    @Override
    public Concert create(ConcertCreateUpdate dto) {

        if (dto.getTitle() == null || dto.getDescription() == null || dto.getDtEvent() == null ||
                dto.getDtEndOfSale() == null || dto.getType() == null || dto.getEventStatus() == null ||
                dto.getCategory() == null) {
            throw new IllegalArgumentException("Для создания необходимо заполнить все поля");
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/api/v1/classifier/concert/category/" + dto.getCategory();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            throw new IllegalArgumentException("Выбранная категория отсутсвует в справочнике");
        }

        return this.concertDao.save(this.mapperService.mapCreate(dto));
    }

    @Override
    public Concert readOne(UUID uuid) {

        if (uuid == null || uuid.toString().isEmpty()) {
            throw new IllegalArgumentException("Поле uuid не может быть пустым");
        }

        return this.concertDao
                .findById(uuid)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("Концерт не найден");
                });
    }

    @Override
    public List<Concert> readAll() {
        return this.concertDao.findAll();
    }

    @Override
    public Page<Concert> getPage(Pageable pageable) {

        return this.concertDao.findAll(pageable);
    }

    @Override
    public Concert update(UUID uuid, ConcertCreateUpdate dto, LocalDateTime dtUpdate) {

        if (uuid == null || uuid.toString().isEmpty()) {
            throw new IllegalArgumentException("Поле uuid не может быть пустым");
        }

        if (dto.getTitle() == null || dto.getDescription() == null || dto.getDtEvent() == null ||
                dto.getDtEndOfSale() == null || dto.getType() == null || dto.getEventStatus() == null ||
                dto.getCategory() == null) {
            throw new IllegalArgumentException("Для обновления необходимо заполнить все поля");
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/api/v1/classifier/concert/category/" + dto.getCategory();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException(e.getMessage());
        }

        Concert concertDB = this.readOne(uuid);

        if (!concertDB.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("Концерт уже был обновлен кем-то ранее");
        }

        Concert concert = mapperService.mapUpdate(dto, concertDB);

       return this.concertDao.save(concert);
    }
}
