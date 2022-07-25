package by.it_academy.jd2.EventConcertService.service;

import by.it_academy.jd2.EventConcertService.core.dao.api.IConcertDao;
import by.it_academy.jd2.EventConcertService.core.dto.concert.ConcertCreate;
import by.it_academy.jd2.EventConcertService.core.dto.concert.ConcertUpdate;
import by.it_academy.jd2.EventConcertService.core.entity.Concert;
import by.it_academy.jd2.EventConcertService.core.entity.enums.EventStatus;
import by.it_academy.jd2.EventConcertService.service.api.IConcertService;
import by.it_academy.jd2.EventConcertService.service.api.IMapperService;
import by.it_academy.jd2.EventConcertService.validation.api.IHttpValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final UserHolder holder;


    public ConcertService(IConcertDao concertDao, IMapperService mapperService, IHttpValidator httpValidator, UserHolder holder) {
        this.concertDao = concertDao;
        this.mapperService = mapperService;
        this.httpValidator = httpValidator;
        this.holder = holder;
    }

    @Override
    @Transactional
    public Concert create(ConcertCreate dto) {

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

        if (holder.isAuthenticated()) {

            UserDetails user = holder.getUser();

            if (user.getAuthorities().size() > 1) {
                return this.concertDao.findAll(pageable);
            } else {
                return this.concertDao.findByEventStatusOrAuthor(EventStatus.PUBLISHED, user.getUsername(), pageable);
            }
        } else {
            return this.concertDao.findByEventStatus(EventStatus.PUBLISHED, pageable);
        }
    }

    @Override
    @Transactional
    public Concert update(UUID uuid, ConcertUpdate dto, LocalDateTime dtUpdate) {

        httpValidator.validCategory(dto.getCategory());

        Concert concertDB = this.readOne(uuid);

        if (!concertDB.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("концерт уже был обновлен кем-то ранее");
        }

       return this.concertDao.save(mapperService.mapUpdate(dto, concertDB));
    }
}
