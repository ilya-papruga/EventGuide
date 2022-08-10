package by.it_academy.jd2.concert.service.converters;

import by.it_academy.jd2.concert.core.dto.concert.ConcertCreate;
import by.it_academy.jd2.concert.core.entity.Concert;
import by.it_academy.jd2.concert.core.entity.enums.EventStatus;
import by.it_academy.jd2.concert.service.UserHolder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ConcertCreateToConcertConverter implements Converter <ConcertCreate, Concert> {

    private final UserHolder holder;

    public ConcertCreateToConcertConverter(UserHolder holder) {
        this.holder = holder;
    }

    @Override
    public Concert convert(ConcertCreate dto) {

        Concert entity = new Concert();

        entity.setUuid(UUID.randomUUID());
        entity.setDtCreate(LocalDateTime.now());
        entity.setDtUpdate(entity.getDtCreate());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDtEvent(dto.getDtEvent());
        entity.setDtEndOfSale(dto.getDtEndOfSale());
        entity.setType("concert");
        entity.setEventStatus(EventStatus.DRAFT);
        entity.setCategory(dto.getCategory());
        entity.setAuthor(holder.getUser().getUsername());

        return entity;

    }
}
