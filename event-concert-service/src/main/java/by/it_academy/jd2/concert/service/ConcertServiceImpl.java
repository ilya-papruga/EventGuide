package by.it_academy.jd2.concert.service;

import by.it_academy.jd2.concert.repository.api.ConcertRepository;
import by.it_academy.jd2.concert.dto.concert.ConcertCreate;
import by.it_academy.jd2.concert.dto.concert.ConcertUpdate;
import by.it_academy.jd2.concert.entity.Concert;
import by.it_academy.jd2.concert.entity.enums.EventStatus;
import by.it_academy.jd2.concert.service.api.ConcertService;
import by.it_academy.jd2.concert.validation.api.ClassifierClient;
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
public class ConcertServiceImpl implements ConcertService {

    private final ConcertRepository concertRepository;
    private final ClassifierClient client;
    private final UserHolder holder;
    private final ConversionService conversionService;


    public ConcertServiceImpl(ConcertRepository concertRepository, ClassifierClient client, UserHolder holder, ConversionService conversionService) {
        this.concertRepository = concertRepository;
        this.client = client;
        this.holder = holder;
        this.conversionService = conversionService;
    }

    @Override
    @Transactional
    public Concert create(ConcertCreate dto) {

        client.validCategory(dto.getCategory());

        return this.concertRepository.save(Objects.requireNonNull(conversionService.convert(dto, Concert.class)));
    }

    @Override
    public Concert readOne(UUID uuid) {

        return this.concertRepository.findById(uuid).orElseThrow(() -> {
                    throw new IllegalArgumentException("концерт не найден");
                });
    }

    @Override
    public Page<Concert> getPage(Pageable pageable) {

        if (holder.isAuthenticated()) {

            UserDetails user = holder.getUser();

            if (user.getAuthorities().size() > 1) {
                return this.concertRepository.findAll(pageable);
            } else {
                return this.concertRepository.findByEventStatusOrAuthor(EventStatus.PUBLISHED, user.getUsername(), pageable);
            }
        } else {
            return this.concertRepository.findByEventStatus(EventStatus.PUBLISHED, pageable);
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

       return this.concertRepository.save(concert);
    }
}
