package by.it_academy.jd2.EventConcertService.service;

import by.it_academy.jd2.EventConcertService.core.dao.api.IConcertDao;
import by.it_academy.jd2.EventConcertService.core.dto.concert.ConcertCreateUpdate;
import by.it_academy.jd2.EventConcertService.core.entity.Concert;
import by.it_academy.jd2.EventConcertService.service.api.IConcertService;
import by.it_academy.jd2.EventConcertService.service.api.IMapperService;
import by.it_academy.jd2.EventConcertService.validation.api.IHttpValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ConcertService implements IConcertService {

    private final IConcertDao concertDao;
    private final IMapperService mapperService;
    private final IHttpValidator httpValidator;


    public ConcertService(IConcertDao concertDao, IMapperService mapperService, IHttpValidator httpValidator) {
        this.concertDao = concertDao;
        this.mapperService = mapperService;
        this.httpValidator = httpValidator;
    }

    @Override
    @Transactional
    public Concert create(ConcertCreateUpdate dto) {

        httpValidator.validCategory(dto.getCategory());

        return this.concertDao.save(this.mapperService.mapCreate(dto));
    }

    @Override
    public Concert readOne(UUID uuid) {

        return this.concertDao.findById(uuid).orElseThrow(() -> {
                    throw new IllegalArgumentException("концерт не найден");
                });
    }

    @Override
    public Page<Concert> getPage(Pageable pageable) {

        return this.concertDao.findAll(pageable);
    }

    @Override
    @Transactional
    public Concert update(UUID uuid, ConcertCreateUpdate dto, LocalDateTime dtUpdate) {

        httpValidator.validCategory(dto.getCategory());

        Concert concertDB = this.readOne(uuid);

        if (!concertDB.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("концерт уже был обновлен кем-то ранее");
        }

       return this.concertDao.save(mapperService.mapUpdate(dto, concertDB));
    }
}
