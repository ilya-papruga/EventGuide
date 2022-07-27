package by.it_academy.jd2.EventConcertService.service;

import by.it_academy.jd2.EventConcertService.core.dao.api.IConcertDao;
import by.it_academy.jd2.EventConcertService.core.dto.concert.ConcertCreate;
import by.it_academy.jd2.EventConcertService.core.dto.concert.ConcertUpdate;
import by.it_academy.jd2.EventConcertService.core.entity.Concert;
import by.it_academy.jd2.EventConcertService.core.entity.enums.EventStatus;
import by.it_academy.jd2.EventConcertService.service.api.IConcertService;
import by.it_academy.jd2.EventConcertService.validation.api.IClassifierClient;
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
public class ConcertService implements IConcertService {

    private final IConcertDao concertDao;
    private final IClassifierClient client;
    private final UserHolder holder;
    private final ConversionService conversionService;


    public ConcertService(IConcertDao concertDao, IClassifierClient client, UserHolder holder, ConversionService conversionService) {
        this.concertDao = concertDao;
        this.client = client;
        this.holder = holder;
        this.conversionService = conversionService;
    }

    @Override
    @Transactional
    public Concert create(ConcertCreate dto) {

        client.validCategory(dto.getCategory());

        return this.concertDao.save(Objects.requireNonNull(conversionService.convert(dto, Concert.class)));
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

        client.validCategory(dto.getCategory());

        Concert concert = conversionService.convert(dto, Concert.class);

        Concert concertDB = this.readOne(uuid);

        concert.setUuid(concertDB.getUuid());
        concert.setDtCreate(concertDB.getDtCreate());
        concert.setDtUpdate(concertDB.getDtUpdate());
        concert.setAuthor(concertDB.getAuthor());

        if (!concertDB.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("концерт уже был обновлен кем-то ранее");
        }

       return this.concertDao.save(concert);
    }
}
